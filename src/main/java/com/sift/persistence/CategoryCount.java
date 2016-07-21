package com.sift.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * Model containing category count.
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 * @deprecated
 */
public class CategoryCount {
	
	private Map<String, Integer> cc;
	private DbAdapter adapter;
	
	public CategoryCount() {
		this.cc = new HashMap<String, Integer>();
	}

	public CategoryCount(DbAdapter adapter) {
		this.cc = new HashMap<String, Integer>();
		this.adapter = adapter;
	}
	
	public Map<String, Integer> getCc() {
		return cc;
	}

	public void setCc(Map<String, Integer> cc) {
		this.cc = cc;
	}
	
	public int getCount(String category){
//		if(cc.containsKey(category))
//				return cc.get(category);
//		return 0;
		return adapter.categoryCount(category);
	}
	
	public void setCount(String category, int value){
//		cc.put(category, value);
		adapter.incrCategory(category);
	}
}
