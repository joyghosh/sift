package com.sift.classifiers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
	
	public NaiveBayes(String type) {
		super(type);
		this.thresholds = new HashMap<String, Double>();
	}
	
	public NaiveBayes(String type, File document) {
		super(type, document);
		this.thresholds = new HashMap<String, Double>();
	}
	
}
