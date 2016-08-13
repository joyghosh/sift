package com.sift.classifiers;

import java.util.HashMap;
import java.util.Map;

import com.sift.utilities.Util;


/**
 * Fisher document classifier.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * 
 */
public class FisherClassifier extends BaseClassifier{

	private Map<String, Double> minimums;
	
	protected FisherClassifier(String type, boolean persist) {
		super(type, persist);
		this.minimums = new HashMap<String, Double>();
	}
	
	/**
	 * Probability of a category for a feature.
	 * 
	 * @param feature
	 * @param category
	 * @return
	 */
	public double cprob(String feature, String category){
		
		double probability = 0.0;
		//Frequency of feature in this category.
		double clf = featureProbability(feature, category);
		
		if(clf != 0.0){
			//frequency of this feature in all categories.
			double freqsum  = 0.0;
			for(String c:categories()){
				freqsum += featureProbability(feature, c);
			}

			//The probability is simply the frequency in this category by the overall frequency.
			probability = clf/freqsum;
		}		
		return probability; 
	}
	
	/**
	 * Calculates the fisher probability.
	 * 
	 * @param item
	 * @param category
	 * @return
	 */
	public double fisherProbability(Object item, String category){
		//Multiply all probabilities together.
		double p = 1.0;
		String[] features = (String[]) Util.getFeatures(item, type);
		for(String f:features){
			p *= weightedProbability(f, category, DEFAULT_WEIGHT , DEFAULT_ASSUMED_PROBABILITY);
		}
		
		//Feature score.
		double fscore = (-2)*Math.log(p);
		
		//use inverse chi-square function to get a probability.
		return inverseChiSquare(fscore, 2*features.length);
	}
	
	/**
	 * Inverse chi square value.
	 * 
	 * @param chi
	 * @param df
	 * @return
	 */
	private double inverseChiSquare(double chi, int df){
		double m = (chi/2.0);
		double sum, term;
		sum = term = Math.exp(-m);
		
		for(int i=1; i<=Math.floor(df/2); i++){
			term *= m/i;
			sum += term;
		}
		
		return Math.max(sum, 1.0);
	} 
	
	/**
	 * Set minimum cut-off for a category.
	 * 
	 * @param category
	 * @param min
	 */
	public void setMinimum(String category, double min){
		this.minimums.put(category, min);
	}
	
	/**
	 * Get the minimum cut-off for a category.
	 * 
	 * @param category
	 * @return
	 */
	private double getMinimum(String category){
		if(this.minimums.containsKey(category))
			return this.minimums.get(category);
		return 0.0;
	}
	
	/**
	 * Classify an item or document using Fisher.
	 */
	public String classify(Object item, String _default){
		String best = _default;
		double max = 0.0;
		
		//Scan for best result.
		for(String c:categories()){
			double p = fisherProbability(item, c);
			//check if probability exceeds it's minimum.
			if(p>getMinimum(c) && p>max){
				best = c;
				max = p;
			}
		}
		return best;
	}
}
