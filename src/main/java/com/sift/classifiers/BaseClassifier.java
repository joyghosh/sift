package com.sift.classifiers;

import java.io.File;

/**
 * A base class to model a classifier.
 * Direct instantiation is avoided of this class.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class BaseClassifier implements Classifier {

	protected BaseClassifier() {
		// Avoid instantiating this abstract classifier.
	}
	
	@Override
	public void incrementFeature(String feature, String category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void incrementCategory(String category) {
		
	}

	@Override
	public double featureCount(String feature, String category) {
		return 0;
	}

	@Override
	public int categoryCount(String category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] categories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void train(File item, String category) {
		// TODO Auto-generated method stub

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
