package com.sift.utilities;

import java.io.File;

/**
 * Custom training seed object.
 * @author Joy Ghosh.
 * @version 1.0
 * @since 1.0
 */
public class Seed{
	
	private File file;
	private String text;
	private String category;
	private boolean isText;
	
	public Seed(File file, String category){
		this.file = file;
		this.category = category;
		this.isText = false;
	}

	public Seed(String text, String category){
		this.text = text;
		this.category = category;
		this.isText = true;
	}
	
	public File getFile() {
		return file;
	}

	public String getText() {
		return text;
	}

	public String getCategory() {
		return category;
	}
	
	public boolean isText(){
		return isText;
	}
}
