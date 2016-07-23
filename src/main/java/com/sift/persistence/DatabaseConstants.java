package com.sift.persistence;

/**
 * 
 * DB constants and naming.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public interface DatabaseConstants {
	public final String SQLITE_CLASS_NAME = "org.sqlite.JDBC";
	public final String H2_CLASS_NAME = "org.h2.Driver"; 
//	public String DB_CONNECTION_STRING = "jdbc:sqlite:sift.db";
	public final String DB_CONNECTION_STRING = "jdbc:h2:./sift";
	public final String DB_USER = "sa";
	public final String DB_PASSWORD = "";
	public String FEATURE_COUNT_TABLE = "feature_count";
	public String CATEGORY_COUNT_TABLE = "category_count";
}
