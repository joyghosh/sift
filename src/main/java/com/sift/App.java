package com.sift;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sift.classifiers.Classifier;
import com.sift.classifiers.ClassifierConstants;
import com.sift.classifiers.ClassifierFactory;
import com.sift.classifiers.NaiveBayes;
import com.sift.utilities.Feature;
import com.sift.utilities.FileReader;
import com.sift.utilities.Seed;
import com.sift.utilities.Util;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	
    	logger.debug("initiating training process.");
    	
        ClassifierFactory factory = new ClassifierFactory();
        Classifier classifier = factory.getClassifier(ClassifierConstants.BAYES);
        classifier.reset();
    	
    	//Start with training set.
    	List<Seed> spams = Util.getSeeds("/home/joy/Desktop/training/spam", "bad");
    	List<Seed> nospams = Util.getSeeds("/home/joy/Desktop/training/nonspam", "good");
    	
    	Util.batchTrain(spams, ClassifierConstants.BAYES);
    	Util.batchTrain(nospams, ClassifierConstants.BAYES);
    	
        System.out.println(classifier.classify("You have received this message because you subscribed to it", "unknown"));
        System.out.println(classifier.classify("Why Spend upwards of $4000 on a DVD Burner when we will show you an alternative", "unknown"));
        System.out.println(classifier.classify("Best Price on the net", "good"));
        System.out.println(classifier.classify("The survey will take no more than two minutes of your time and we would", "unknown"));
        System.out.println(classifier.classify("Hard-drive-based MP3 players, which allow you to store massive", "unknown"));
    }
}
