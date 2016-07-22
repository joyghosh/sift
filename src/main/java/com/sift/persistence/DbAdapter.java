package com.sift.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Database adapter. 
 * Interface for read, write, update and purge operations. 
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 */
public class DbAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(DbAdapter.class);
	private static DatabaseInit initializer;
	private static DbAdapter instance;
	private static Connection conn;
	
	private DbAdapter() {
		//logger.debug("new storage adapter reference.");
		initializer = DatabaseInit.getInitializer();
		conn = initializer.getConnection();
	}
	
	public static DbAdapter getAdapter(){
		if(instance == null){
			synchronized (DbAdapter.class) {
				instance = new DbAdapter();
			}
		}	
		return instance;
	} 
	
	public synchronized double featureCount(String feature, String category){
		
		//logger.debug("fetching feature count.");
		double fc = 0.0; 
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT count FROM "+
											DatabaseConstants.FEATURE_COUNT_TABLE+
											" WHERE feature=? AND category=?");
			stmt.setString(1, feature);
			stmt.setString(2, category);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				fc = rs.getDouble("count");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return fc;
	}
	
	public synchronized void incrFeature(String feature, String category){
		
//		System.out.println("incrementing feature count.");
		double count = featureCount(feature, category); 
		try {
			if(count == 0.0){
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO "+
						DatabaseConstants.FEATURE_COUNT_TABLE+
						"(feature, category, count) VALUES(?, ?, ?)");
				stmt.setString(1, feature);
				stmt.setString(2, category);
				stmt.setDouble(3, 1.0);
				stmt.execute();
			}else{
				PreparedStatement stmt = conn.prepareStatement("UPDATE "+
						DatabaseConstants.FEATURE_COUNT_TABLE+
						" SET count=? WHERE feature=? AND category=?;");
				stmt.setDouble(1, count+1.0);
				stmt.setString(2, feature);
				stmt.setString(3, category);
				stmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
	
	public synchronized void incrCategory(String category){
		int count = categoryCount(category);
		try{
			if(count == 0){
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO "+
						DatabaseConstants.CATEGORY_COUNT_TABLE+
						"(category, count) VALUES(?, ?)");
				stmt.setString(1, category);
				stmt.setDouble(2, 1);
				stmt.execute();
			}else{
				PreparedStatement stmt = conn.prepareStatement("UPDATE "+
						DatabaseConstants.CATEGORY_COUNT_TABLE+
						" SET count=? WHERE category=?");
				stmt.setDouble(1, count+1.0);
				stmt.setString(2, category);
				stmt.executeUpdate();
			}
			conn.commit();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	
	public synchronized int categoryCount(String category){
		int count = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT count FROM "+
					DatabaseConstants.CATEGORY_COUNT_TABLE+
					" WHERE category=?");
			stmt.setString(1, category);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return count;
	}
	
	public synchronized int totalCount(){
		int count = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUM(count) FROM "+DatabaseConstants.CATEGORY_COUNT_TABLE);
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return count;
	}
	
	public synchronized Set<String> categories(){
		Set<String> categories = new HashSet<String>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT category FROM "+DatabaseConstants.CATEGORY_COUNT_TABLE);
			while(rs.next()){
				categories.add(rs.getString("category"));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return categories;
	}
	
	public synchronized void reset(){
		DatabaseInit.dropTables(conn);
	}
}
