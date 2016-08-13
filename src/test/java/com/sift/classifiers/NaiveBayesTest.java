package com.sift.classifiers;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Test suite for Naive Bayes classifier.
 * 
 * @author Joy Ghosh
 * @version 1.1
 * 
 */
public class NaiveBayesTest extends TestCase {
	
	private Classifier classifier;
	private static final String DEFAULT_CATEGORY = "unknown"; 
	
	@BeforeClass
	protected void setUp(){
		ClassifierFactory factory = new ClassifierFactory();
		classifier = factory.getClassifier(ClassifierConstants.BAYES);
	}
	
	@Test
	public void testClassify(){
		
		String spam = "The February 2009 Great Britain and Ireland snowfall was the heaviest London had seen for";
		String non_spam = "Since I am project leader, I must not be permitted to go insane.";
		
		assertEquals("bad", classifier.classify(spam, DEFAULT_CATEGORY));
		assertEquals("good", classifier.classify(non_spam, DEFAULT_CATEGORY));
	}
}
