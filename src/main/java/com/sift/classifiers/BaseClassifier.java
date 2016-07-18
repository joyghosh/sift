package com.sift.classifiers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sift.utilities.Util;

/**
 * A base class to model a classifier.
 * Direct instantiation is avoided of this class.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class BaseClassifier implements Classifier {

	private Map<String, Map<String, Double>> feature_catefory;
	private Map<String, Integer> category_count;
	private File document;
	String type;
	
	protected BaseClassifier(String type) {
		// Avoid instantiating this abstract classifier.
		this.feature_catefory = new HashMap<String, Map<String, Double>>();
		this.category_count = new HashMap<String, Integer>();
		this.type = type;
	}
	
	protected BaseClassifier(String type, File document) {
		// Avoid instantiating this abstract classifier.
		this.feature_catefory = new HashMap<String, Map<String, Double>>();
		this.category_count = new HashMap<String, Integer>();
		this.type = type;
		this.document = document;
	}
	
	/**
	 * Increment the feature/category pair.
	 * @param feature
	 * @param category
	 */
	@Override
	public void incrementFeature(String feature, String category) {
		
		double count = 0.0;
		if(feature_catefory.containsKey(feature) && feature_catefory.get(feature).containsKey(category)){
			count = feature_catefory.get(feature).get(category);
		}else{
			feature_catefory.put(feature, new HashMap<String, Double>());
			feature_catefory.get(feature).put(category, count);
		}
		feature_catefory.get(feature).put(category, ++count);
	}

	/**
	 * Increase count of a category.
	 * @param category
	 */
	@Override
	public void incrementCategory(String category) {
		
		int count = 0;
		if(category_count.containsKey(category)){
			count = category_count.get(category);
		}else{
			category_count.put(category, count);
		}
		category_count.put(category, ++count);
	}

	/**
	 * Number of times a feature has appeared in a category.
	 * @param feature
	 * @param category
	 * @return
	 */
	@Override
	public double featureCount(String feature, String category) {
		if(feature_catefory.containsKey(feature) && 
				feature_catefory.get(feature).containsKey(category)){
			return feature_catefory.get(feature)
					.get(category);
		}
		return 0.0;
	}

	/**
	 * Number of items in a category.
	 * @param category
	 * @return
	 */
	@Override
	public double categoryCount(String category) {
		if(category_count.containsKey(category)){
			return category_count.get(category); 
		}
		return 0.0;
	}

	/**
	 * Total number of items.
	 * @return
	 */
	@Override
	public int totalCount() {
		int sum = 0;
		for(int val:category_count.values()){
			sum += val;
		}
		return sum;
	}

	/**
	 * List of all categories.
	 */
	@Override
	public Set<String> categories() {
		return category_count.keySet();
	}

	@Override
	public void train(Object item, String category) {
		String[] features = (String[]) Util.getFeatures(item, type);
		
		for(String feature: features){
			//Increment the count for every feature with this category
			this.incrementFeature(feature, category);
		}
		
		//Increment the count for this category
		this.incrementCategory(category);
	}

	@Override
	public double featureProbability(String feature, String category) {
		
		return 0;
	}

	@Override
	public double weightedProbability(String feature, String category,
			String prf, double weight, double ap) {
	
		return 0;
	}

}
