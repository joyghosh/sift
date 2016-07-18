package com.sift.classifiers;

import java.io.File;

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

	
	public NaiveBayes(String type) {
		super(type);
	}
	
	public NaiveBayes(String type, File document) {
		super(type, document);
	}
	
}
