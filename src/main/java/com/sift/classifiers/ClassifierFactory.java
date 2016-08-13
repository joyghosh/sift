package com.sift.classifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sift.utilities.Feature;

/**
 * Factory class for classifiers.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * 
 */
public class ClassifierFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassifierFactory.class);
	
	public Classifier getClassifier(String type){
		
		if(type == null) return null;	
		Classifier cl = null;
		switch(type){
			case ClassifierConstants.BAYES:	cl = new NaiveBayes(Feature.WORDS, 
											ClassifierConstants.PERSISTENCE_ENABLED);
											break;
										
			case ClassifierConstants.FISHER: cl = new FisherClassifier(Feature.WORDS, 
											ClassifierConstants.PERSISTENCE_ENABLED);
											break;
											
			default: logger.warn("no such classfier found.");
		}
		
		return cl;
	}
}
