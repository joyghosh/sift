package com.sift.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sift.classifiers.Classifier;

/**
 * 
 * <p> A utility container holding all the essential
 * utility methods used by the document classifier </p>
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class Util {
	
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	
	/**
	 * utility method which returns the feature set as an array.
	 * @param obj
	 * @param type
	 * @return
	 */
	public static Object[] getFeatures(Object obj, String type){
		
		Object[] features = null;
		switch (type) {
			case Feature.WORDS:	features = (obj instanceof File)? getWords((File)obj) : getWords((String)obj);
								break;
			default:	System.err.println("No such feature type supported.");	
						break;
		}
		return features;
	}
	
	private static String[] getWords(File document){
		
		Scanner in = null;
		String[] words = null;
		Pattern p = Pattern.compile("[\\w']+");
		
		try {
			in = new Scanner(document);
			in.useDelimiter(p);
			StringBuilder sb = new StringBuilder();
			while(in.hasNext()){
				sb.append(","+in.next());
			}
			words =  sb.toString().split(",");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}finally{
			in.close();
		}
		
		return words;
	}
	
	private static String[] getWords(String sentence){
		
		Scanner in = null;
		String[] words = null;
		try{
			in = new Scanner(sentence);
			StringBuilder sb = new StringBuilder();
			while(in.hasNext()){
				sb.append(","+in.next());
			}
			words =  sb.toString().split(",");
		}finally{
			in.close();
		}
		
		return words;
	}
	
	/**
	 * Train the classifier with sample data.
	 * @param classifier
	 * @deprecated
	 */
	public static void sampleTrain(Classifier classifier){
		classifier.train("Nobody owns the water.", "good");
		classifier.train("the quick rabbit jumps fences", "good");
		classifier.train("buy pharmaceuticals now", "bad");
		classifier.train("make quick money at the online casino", "bad");
		classifier.train("the quick brown fox jumps", "good");
	}
	
	/**
	 * Train the classifier with some serious training stuff.
	 * @param classifier
	 * @param seeds
	 */
	public static void train(Classifier classifier, List<Seed> seeds){
		logger.debug("training classifier with some serious stuff.");
		for(Seed s:seeds){
			if(s.isText()){
				classifier.train(s.getText(), s.getCategory());
			}else{
				classifier.train(s.getFile(), s.getCategory());
			}
		}
	}
}
