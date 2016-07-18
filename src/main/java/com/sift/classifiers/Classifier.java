package com.sift.classifiers;

import java.io.File;

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
	public int categoryCount(String category);
	public int totalCount();
	public String[] categories();
	public void train(File item, String category);
	public double featureProbability(String feature, String category);
	public double weightedProbability(String feature, String category, String prf,
										double weight, double ap);
}
