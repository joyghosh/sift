package com.sift.classifiers;

import java.util.HashMap;
import java.util.Map;

import com.sift.utilities.Util;

/**
 * A naive document classifier based on Bayes theorem.
 * Extends the <code> {@link BaseClassifier} </code> model.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 * 
 */
public class NaiveBayes extends BaseClassifier {
	
	private Map<String, Double> thresholds;
	
	protected NaiveBayes(String type, boolean persist) {
		super(type, persist);
		this.thresholds = new HashMap<String, Double>();
	}
	
//	private NaiveBayes(String type, File document, boolean persist) {
//		super(type, document, persist);
//		this.thresholds = new HashMap<String, Double>();
//	}
	
	/**
	 * Document probability.
	 * @param item
	 * @param category
	 * @return
	 */
	private double documentProbability(Object item, String category){
		String[] features = (String[]) Util.getFeatures(item, type);
		double p = 1.0;
		for(String feature:features){
			p *= weightedProbability(feature, category, DEFAULT_WEIGHT, DEFAULT_ASSUMED_PROBABILITY);
		}
		return p;
	}
	
	/**
	 * Probability of a category.
	 * @param item
	 * @param category
	 * @return
	 */
	public double probability(Object item, String category){
		double category_probability = categoryCount(category)/totalCount();
		double doc_probability = documentProbability(item, category);
		return (doc_probability*category_probability);
	}
	
	/**
	 * Set threshold value for category.
	 * @param category
	 * @param threshold
	 */
	public void setThreshold(String category, double threshold){
		thresholds.put(category, threshold);
	}
	
	/**
	 * Return threshold value for a category.
	 * @param category
	 * @return
	 */
	public double getThreshold(String category){
		
		double threshold = 1.0;
		if(thresholds.containsKey(category)){
			threshold = thresholds.get(category);
		}
		return threshold;
	}
	
	/**
	 * Classify item.
	 * @param item
	 * @param _default
	 * @return
	 */
	public String classify(Object item, String _default){
		
		Map<String, Double> probs = new HashMap<String, Double>();
		
		//Find the category with the highest probability.
		double max = 0.0; String best = null;
		for(String category:categories()){
			probs.put(category, probability(item, category));
			if(probs.get(category) > max){
				max = probs.get(category);
				best = category;
			}
		}
		
		//Make sure the probability exceeds threshold*next best.
		for(String category:probs.keySet()){
			if(category.equalsIgnoreCase(best)) continue;
			if(probs.get(category)*getThreshold(category) > probs.get(best)){
				return _default;
			}
		}
		
		return best;
	}
}
