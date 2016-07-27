package com.sift;

import com.sift.classifiers.Classifier;
import com.sift.classifiers.NaiveBayes;
import com.sift.utilities.Feature;
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
        Classifier classifier = new NaiveBayes(Feature.WORDS);
        Util.sampleTrain(classifier);
//        classifier.train("the quick brown fox jumps over the lazy dog", "good");
//        classifier.train("make quick money in the online casino","bad");
        System.out.println(classifier.featureCount("jumps", "bad"));
        System.out.println(classifier.featureCount("jumps", "good"));
    }
}