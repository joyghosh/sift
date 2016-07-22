package com.sift.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sift.classifiers.Classifier;
import com.sift.classifiers.ClassifierFactory;

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
	private static final int POOL_SIZE = 10;
	
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
	
	/**
	 * Extract all words from a file.
	 * @param document
	 * @return
	 */
	private static String[] getWords(File document){
		
		Scanner in = null;
		String[] words = null;
		Pattern del = Pattern.compile("\\s+");
		Pattern wp = Pattern.compile("[\\w']+");
		
		try {
			in = new Scanner(document);
			in.useDelimiter(del);
			StringBuilder sb = new StringBuilder();
			Matcher matcher;
			while(in.hasNext()){
				matcher = wp.matcher(in.nextLine());
				while(matcher.find())
					sb.append(","+matcher.group());
			}
			words =  sb.toString().split(",");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}finally{
			in.close();
		}
		
		return words;
	}
	
	/**
	 * Extract all the words from text.
	 * @param sentence
	 * @return
	 */
	private static String[] getWords(String text){
		
		Scanner in = null;
		String[] words = null;
		try{
			in = new Scanner(text);
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
	 * @deprecated
	 */
	public static void train(Classifier classifier, List<Seed> seeds){
		logger.debug("training classifier with provided seeds.");
		for(Seed s:seeds){
			if(s.isText()){
				classifier.train(s.getText(), s.getCategory());
			}else{
				classifier.train(s.getFile(), s.getCategory());
			}
		}
	}
	
	/**
	 * Return seed objects for initializing and training the classifier. 
	 * @param path directory path for files.
	 * @return seeds
	 */
	public static List<Seed> getSeeds(String path, String category){
		
		logger.debug("Searching directory for seed files in "+category+" category.");
		File folder = new File(path);
		List<Seed> seeds = new ArrayList<Seed>();
		if(folder.isDirectory()){
			
			File[] files = folder.listFiles();
			for(File f: files){
				seeds.add(new Seed(f, category));
			}
		}else{
			logger.error("no such directory found "+path);
			System.exit(1);
		}
			return seeds;
	}
	
	/**
	 * Bulk parallel training for classifier.
	 * @param seeds
	 * @param classifierType
	 */
	public static void batchTrain(List<Seed> seeds, String classifierType){
		
		logger.debug("starting bulk training for classifier.");
		ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
		List<Classifier> classifiers = new ArrayList<Classifier>();
		ClassifierFactory factory = new ClassifierFactory();
		List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();
		
		//classifier sets half the size of thread pool.
		for(int i=0;i<POOL_SIZE/2;i++){
			classifiers.add(factory.getClassifier(classifierType)); 
		}
		
		//start workers using round robin fashioned classifier selection.
		for(int i=0;i<seeds.size();i++){
			Callable<Boolean> task = new TrainingTask(classifiers.get(i%classifiers.size()), seeds.get(i));
			futures.add(executor.submit(task));
		}
		
		//Wait for the tasks to finish.
		for(Future<Boolean> f:futures){
			try{
				f.get();
			}catch(InterruptedException | ExecutionException e){
				logger.error(e.getMessage());
			}
		}
		
		executor.shutdown();
		logger.debug("training process complete.");
	}
}
