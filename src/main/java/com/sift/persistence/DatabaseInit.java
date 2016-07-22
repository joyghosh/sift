package com.sift.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Classifier persistence initializer.
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class DatabaseInit {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseInit.class);
	private static DatabaseInit instance;
	private static Connection connection;
	
	/**
	 * Singelton pattern.
	 */
	private DatabaseInit(){
		if(connection == null){
			synchronized (DatabaseInit.class) {
				init();
			}
		}
	}
	
	/**
	 * Returns an initializer instance.
	 * @return
	 */
	public static DatabaseInit getInitializer(){
		if(instance == null){
			synchronized (DatabaseInit.class) {
				instance = new DatabaseInit();
			}
		}
		return instance;
	}
	
	/**
	 * Returns a connection instance.
	 * @return
	 */
	public Connection getConnection(){
		return connection;
	}
	
	/**
	 * Initialize the database.
	 */
	private static void init(){
		//logger.debug("initializing database.");
		try {
			Class.forName(DatabaseConstants.H2_CLASS_NAME);
			connection = DriverManager.getConnection(DatabaseConstants.DB_CONNECTION_STRING,
					DatabaseConstants.DB_USER,DatabaseConstants.DB_PASSWORD);
			createTables(connection);
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * create the empty tables.
	 * @param conn
	 * @throws SQLException
	 */
	private static void createTables(Connection conn) throws SQLException{
		//logger.debug("initializing tables.");
		Statement statement = conn.createStatement();
		String feature_count_table = "CREATE TABLE IF NOT EXISTS "
										+DatabaseConstants.FEATURE_COUNT_TABLE+"(feature VARCHAR(255),category VARCHAR(255),count DOUBLE)";
		String category_count_table = "CREATE TABLE IF NOT EXISTS "+DatabaseConstants.CATEGORY_COUNT_TABLE
										+"(category VARCHAR(255),count INT)";
		conn.setAutoCommit(false);
		statement.addBatch(feature_count_table);
		statement.addBatch(category_count_table);
		statement.executeBatch();
		conn.commit();
	}
	
	/**
	 * Resets the classifier tables.
	 * @throws SQLException 
	 * 
	 */
	public static void dropTables(Connection conn){
		//logger.debug("purging tables.");
		try{
			Statement statement = conn.createStatement();
			String reset_feature = "DELETE FROM "+DatabaseConstants.FEATURE_COUNT_TABLE;
			String reset_category = "DELETE FROM "+DatabaseConstants.CATEGORY_COUNT_TABLE;
			conn.setAutoCommit(false);
			statement.addBatch(reset_feature);
			statement.addBatch(reset_category);
			statement.executeBatch();
			conn.commit();
		}catch(SQLException e){
			logger.error(e.getMessage());
		}
	}
}
