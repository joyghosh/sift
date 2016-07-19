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
//        System.out.println(classifier.featureCount("jumps", "bad"));
//        System.out.println(classifier.featureCount("jumps", "good"));
//        System.out.println(classifier.featureProbability("fox", "good"));
//        System.out.println(classifier.weightedProbability("money", "good",1.0, 0.5));
//        Util.sampleTrain(classifier);
//        System.out.println(classifier.weightedProbability("money", "good",1.0, 0.5));
//        Util.sampleTrain(classifier);
//        System.out.println(classifier.weightedProbability("money", "good",1.0, 0.5));
//        System.out.println(((NaiveBayes)classifier).probability("quick money", "good"));
//        System.out.println(((NaiveBayes)classifier).probability("quick money", "bad"));
         System.out.println(((NaiveBayes)classifier).classify("quick rabbit", "unknown"));
         System.out.println(((NaiveBayes)classifier).classify("quick money", "unknown"));
         ((NaiveBayes)classifier).setThreshold("bad", 3.0);
         System.out.println(((NaiveBayes)classifier).classify("quick money", "unknown"));
         for(int i=0;i<10;i++){
        	 Util.sampleTrain(classifier);
         }
         System.out.println(((NaiveBayes)classifier).classify("quick money", "unknown"));
    }
}
