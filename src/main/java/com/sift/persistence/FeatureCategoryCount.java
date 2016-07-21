package com.sift.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Joy Ghosh
 * @version 1.0
 * @since 1.0
 * @deprecated
 */
public class FeatureCategoryCount {
	
	private Map<String, Map<String, Double>> fcc;
	
	public FeatureCategoryCount() {
		this.fcc = new HashMap<String, Map<String,Double>>();
	}

	public Map<String, Map<String, Double>> getFcc() {
		return fcc;
	}

	public void setFcc(Map<String, Map<String, Double>> fcc) {
		this.fcc = fcc;
	}
	
	public Double getCount(String feature, String category){
		if(fcc.containsKey(feature)){
			if(fcc.get(feature).containsKey(category))
				return fcc.get(feature).get(category);
		}
		return 0.0;
	}
	
	public void setCount(String feature, String category, double value){
		if(fcc.containsKey(feature)){
			if(fcc.get(feature).containsKey(category)){
				fcc.get(feature).put(category, value);
			}else{
				fcc.get(feature).put(category, value);
			}
		}else{
			fcc.put(feature, new HashMap<String, Double>());
			fcc.get(feature).put(category, value);
		}
	}
	
	public void reset(){
		fcc.clear();
	}
}
