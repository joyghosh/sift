package com.sift.classifiers;

//import java.util.HashMap;
//import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sift.persistence.DbAdapter;
import com.sift.utilities.Util;

/**
 * A base class to model a classifier.
 * Direct instantiation is avoided of this class.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class BaseClassifier implements Classifier {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseClassifier.class);
//	private Map<String, Map<String, Double>> feature_category;
//	private Map<String, Integer> category_count;
	protected String type;
//	private boolean persist;
	private DbAdapter db;
	
	protected BaseClassifier(String type, boolean persist) {
//		this.feature_category = new HashMap<String, Map<String,Double>>();
//		this.category_count = new HashMap<String, Integer>();
		this.type = type;
		this.db = DbAdapter.getAdapter();
//		this.persist = persist;
	}
	
	/**
	 * Increment the feature/category pair.
	 * @param feature
	 * @param category
	 */
	@Override
	public void incrementFeature(String feature, String category) {		
//		double count = feature_category.getCount(feature, category);
//		feature_category.setCount(feature, category, ++count);
//		logger.debug("initializing database."+db);
		db.incrFeature(feature, category);
	}

	/**
	 * Increase count of a category.
	 * @param category
	 */
	@Override
	public void incrementCategory(String category) {
		
//		int count = category_count.getCount(category);
//		category_count.setCount(category, ++count);
		db.incrCategory(category);
	}

	/**
	 * Number of times a feature has appeared in a category.
	 * @param feature
	 * @param category
	 * @return
	 */
	@Override
	public double featureCount(String feature, String category) {
//		return feature_category.getCount(feature, category);
		return db.featureCount(feature, category);
	}

	/**
	 * Number of items in a category.
	 * @param category
	 * @return
	 */
	@Override
	public double categoryCount(String category) {
//		return category_count.getCount(category);
		return db.categoryCount(category);
	}

	/**
	 * Total number of items.
	 * @return
	 */
	@Override
	public int totalCount() {
//		int sum = 0;
//		for(int val:category_count.getCc().values()){
//			sum += val;
//		}
//		return sum;
		return db.totalCount();
	}

	/**
	 * List of all categories.
	 */
	@Override
	public Set<String> categories() {
//		return category_count.getCc().keySet();
		return db.categories();
	}

	@Override
	public void train(Object item, String category) {
		synchronized (this) {
			String[] features = (String[]) Util.getFeatures(item, type);
			for(String feature: features){
				//Increment the count for every feature with this category
				incrementFeature(feature, category);
			}
			//Increment the count for this category
			incrementCategory(category);
		}	
	}

	@Override
	public double featureProbability(String feature, String category) {
		if(categoryCount(category) != 0.0){
			return (featureCount(feature, category)/categoryCount(category));
		}
		return 0.0;
	}

	@Override
	public double weightedProbability(String feature, String category, 
			double weight, double ap) {
		
		double basic_probability = featureProbability(feature, category);
		double total = 0.0;
		for(String c:categories()){
			total += featureCount(feature, c);
		}
		return (((weight*ap)+(total*basic_probability))/(weight+total));
	}

	@Override
	public String classify(Object item, String _default) {
		return null;
	}

	@Override
	public void reset() {
		db.reset();
	}

}
