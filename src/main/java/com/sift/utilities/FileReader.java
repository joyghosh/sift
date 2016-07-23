package com.sift.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File reader utility class.
 * Used to initialize and train the classifier.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 * @deprecated
 */
public class FileReader {

	private static final Logger logger = LoggerFactory.getLogger(FileReader.class);
	
	/**
	 * Re
	 * @param path
	 * @return
	 */
	public static List<Seed> spams(String path){
		logger.debug("finding spam files.");
		File folder = new File(path);
		File[] files = folder.listFiles();
		List<Seed> seeds = new ArrayList<Seed>();
		for(File f: files){
			seeds.add(new Seed(f, "bad"));
		}
		
		return seeds;
	}
	
	public static List<Seed> noSpams(String path){
		logger.debug("finding no-spam files.");
		File folder = new File(path);
		File[] files = folder.listFiles();
		List<Seed> seeds = new ArrayList<Seed>();
		for(File f: files){
			seeds.add(new Seed(f, "good"));
		}
		
		return seeds;
	}
}
