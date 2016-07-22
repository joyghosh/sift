package com.sift.utilities;

import java.util.concurrent.Callable;

import com.sift.classifiers.Classifier;

/**
 * Task entity used for bulk training of a classifier.
 * Reduces training latency time.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class TrainingTask implements Callable<Boolean> {

	private Classifier classifier;
	private Seed seed;
	
	public TrainingTask(Classifier classifier, Seed seed) {
		this.classifier = classifier;
		this.seed = seed;
	}

	@Override
	public Boolean call() throws Exception {
		boolean status = false;
		
		if(seed.isText()){
			classifier.train(seed.getText(), seed.getCategory());
			status = true;
		}else{
			classifier.train(seed.getFile(), seed.getCategory());
			status = true;
		}
		return status;
	}
}
