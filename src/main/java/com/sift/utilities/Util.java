package com.sift.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	
	/**
	 * utility method which returns the feature set as an array.
	 * @param obj
	 * @param type
	 * @return
	 */
	public static Object[] getFeatures(Object obj, String type){
		
		Object[] features = null;
		switch (type) {
			case Feature.WORDS:	features = getWords((File)obj);
								break;
			default:	System.err.println("No such feature type supported.");	
						break;
		}
		return features;
	}
	
	private static String[] getWords(File document){
		
		Scanner in = null;
		String[] words = null;
		
		try {
			in = new Scanner(document);
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
}
