package com.queryBuilder.Data.code;

/**
 * 
 * @author Lakshmanan Subbiah
 * 09-07-2019 
 * This class holds the Name and Score of individual data.
 * This class will be used in the comparision.
 */
public class NameValuePair {

	private String name;
	
	private int score;
	
	public NameValuePair(String name, int score) {
		if(name==null) {
			throw new RuntimeException("Name cannot be null.");
		}
		this.name = name;
		this.score = score;
		
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
	
	public String[] getAllSubStrings() {
		return this.name.split("_");
	}
	
	
}
