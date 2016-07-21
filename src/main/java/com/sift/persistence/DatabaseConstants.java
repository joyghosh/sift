package com.sift.persistence;

/**
 * 
 * Constants and common names.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public interface DatabaseConstants {
	public String CLASS_NAME = "org.sqlite.JDBC";
	public String DB_CONNECTION_STRING = "jdbc:sqlite:sift.db";
	public String FEATURE_COUNT_TABLE = "feature_count";
	public String CATEGORY_COUNT_TABLE = "category_count";
}
