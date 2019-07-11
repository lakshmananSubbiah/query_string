package com.queryBuilder.Data.Query;

import java.io.Serializable;
import java.util.Set;

import com.queryBuilder.Data.core.NameValuePair;
import com.queryBuilder.Data.retrievalBuilder.Trie;
import com.queryBuilder.Data.storage.ScoreStore;

/**
 * 
 * @author Lakshmanan Subbiah
 * 11/07/2019 07:37 
 * Main Query tool which combines both the score store and Trie implementations
 */
public class QueryTool implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4899613165981910710L;
	Trie trie;
	ScoreStore scoreStore;
	
	public QueryTool(){
		trie = new Trie();
		scoreStore = new ScoreStore();
	}
	
	public void insert(String name, Integer score) {
		NameValuePair nv = new NameValuePair(name, score);
		String[] subStrings = nv.getAllSubStrings();
		for(String subString : subStrings) {
			trie.insert(subString);
			scoreStore.insertIntoStore(subString, nv);
		}
	}
	
	public Set<NameValuePair> getTop10ScorersOnPrefix(String prefix){
		Set<String> stringsOnPrefixes = trie.search(prefix);
		return scoreStore.returnTop10NameValuePairs(stringsOnPrefixes);
	}
}
