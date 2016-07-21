package com.sift;

import java.util.List;

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
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ClassifierFactory factory = new ClassifierFactory();
        Classifier classifier = factory.getClassifier(ClassifierConstants.BAYES);
        classifier.reset();
        List<Seed> spams = FileReader.spams("/home/joy/Desktop/training/spam");
        List<Seed> nospams = FileReader.noSpams("/home/joy/Desktop/training/nonspam");
        
        Util.train(classifier, nospams);
        Util.train(classifier, spams);
        System.out.println(classifier.classify("You have received this message because you subscribed to it", "unknown"));
        System.out.println(classifier.classify("Why Spend upwards of $4000 on a DVD Burner when we will show you an alternative", "unknown"));
        System.out.println(classifier.classify("Best Price on the net", "unknown"));
    }
}
