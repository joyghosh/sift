package com.sift.utilities;

import java.io.File;

/**
 * Custom Seed object for initializing and classifier training.
 * @author Joy Ghosh.
 * @version 1.0
 * @since 1.0
 */
public class Seed{
	
	private File file;
	private String text;
	private String category;
	private boolean isText;
	
	/**
	 * For file type objects.
	 * @param file
	 * @param category
	 */
	public Seed(File file, String category){
		this.file = file;
		this.category = category;
		this.isText = false;
	}

	/**
	 * For string type objects.
	 * @param text
	 * @param category
	 */
	public Seed(String text, String category){
		this.text = text;
		this.category = category;
		this.isText = true;
	}
	
	/**
	 * File object.
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Content of text.
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * Get category.
	 * @return
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Check if text or file.
	 * @return
	 */
	public boolean isText(){
		return isText;
	}
}
