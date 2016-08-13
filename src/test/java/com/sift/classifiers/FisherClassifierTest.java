package com.sift.classifiers;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Fisher classifier test suite.
 * 
 * @author Joy Ghosh
 * @version 1.1
 * 
 */
public class FisherClassifierTest extends TestCase {
	
	private Classifier classifier;
	private static final String DEFAULT_CATEGORY = "unknown"; 
	
	@BeforeClass
	protected void setUp(){
		ClassifierFactory factory = new ClassifierFactory();
		classifier = factory.getClassifier(ClassifierConstants.FISHER);
	}
	
	@Test
	public void testClassify(){
		
		String spam = "Let us show you how to get more for yourself and your family.";
		String non_spam = "Since I am project leader, I must not be permitted to go insane.";
		
		assertEquals("Should've returned bad", "bad", classifier.classify(spam, DEFAULT_CATEGORY));
		assertEquals("Should've returned good","good", classifier.classify(non_spam, DEFAULT_CATEGORY));
	}
}
