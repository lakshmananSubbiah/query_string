package com.queryBuilder.Data.comparator;

import java.util.Comparator;

import com.queryBuilder.Data.core.NameValuePair;

/**
 * 
 * @author Lakshmanan Subbiah
 * 09/07/2019 - 17:02 
 *   Comparator Class That will be useful in ordering the Names and scores based on Scores
 *
 */
public class ScoreComparator implements Comparator<NameValuePair> {

	@Override
	public int compare(NameValuePair o1, NameValuePair o2) {
		 if(o1.getScore()>o2.getScore()){
	            return -1;
	        } else if(o1.getScore()< o2.getScore()) {
	            return 1;
	        }
	        else {
	        	if(o1.getName().equals(o2.getName())) {
	        		return 0;
	        	}
	        	return 1;
	        }
	}
}
