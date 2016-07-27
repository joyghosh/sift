package com.sift.classifiers;

import java.util.Set;

/**
 * Interface for document classifier.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public interface Classifier {
	
	public void incrementFeature(String feature, String category);
	public void incrementCategory(String category);
	public double featureCount(String feature, String category);
	public double categoryCount(String category);
	public int totalCount();
	public Set<String> categories();
	public void train(Object item, String category);
	public double featureProbability(String feature, String category);
	public double weightedProbability(String feature, String category,
										double weight, double ap);
	public String classify(Object item, String _default);
	public void reset();
}
