package com.queryBuilder.Data.retrievalBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.queryBuilder.Data.constants.QueryBuilderConstants;
/**
 * 
 * @author Lakshmanan Subbiah
 * 10/07/2019 16:45 
 *  Trie or Retrieval Data Structure used for optimized insertion and searching of elements
 *  Also known as Dictionary Data Structure.
 *  This is the data structure used to retrieve all the texts from the given prefix
 */
public class Trie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5505988570597885915L;
	/**
	 * 
	 */
	
	TrieNode root;
	
	public Trie(){
		root = new TrieNode();
	}
	
	static final int MAX_ALPHABET_SIZE = 52;
	
	static class TrieNode implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -459273482079883335L;

		TrieNode[] children = new TrieNode[MAX_ALPHABET_SIZE];
		
		boolean isEndOfWord;
		
		TrieNode() {
			isEndOfWord = false;
			for(int i=0;i<MAX_ALPHABET_SIZE;i++) {
				children[i] = null;
			}
		}
	}
	
	
	
	public void insert(String key) {
		
		int length = key.length();
		Integer index;
		
		TrieNode crawl = root;
		for(int level =0;level<length;level++) {
			index = QueryBuilderConstants.allowedChars.get(key.charAt(level));
			if(index == null) {
				throw new RuntimeException("Not a character");
			}
			if(crawl.children[index] == null) {
				crawl.children[index] = new TrieNode();
			}
			
			crawl = crawl.children[index];
		}
		crawl.isEndOfWord = true;
	}
	
	public Set<String> search(String key) {
		if(key == null) {
			throw new RuntimeException("search word cannot be null.");
		}
		Set<String> stringSet = new HashSet<String>();
		int length = key.length();
		Integer index;
		TrieNode pCrawl = root; 
		
		for(int level = 0;level<length; level++) {
			index = QueryBuilderConstants.allowedChars.get(key.charAt(level));
			 if (pCrawl.children[index] == null) {
	                return stringSet;
			 }
	         pCrawl = pCrawl.children[index]; 
		}

		if(pCrawl.isEndOfWord) {
			stringSet.add(key);
		}
		
		stringSet = findAllSubSets(key,pCrawl,stringSet);
		return stringSet;
	}
	
	public static Set<String> findAllSubSets(String currentText, TrieNode pCrawl, Set<String> stringSet) {
		
		for(int i=0;i<MAX_ALPHABET_SIZE;i++) {
			if(pCrawl.children[i] != null) {
				
				Character c = QueryBuilderConstants.NumberVsChars.get(i);
				
				String CT = currentText + c;
				
				if(pCrawl.children[i].isEndOfWord) {
					stringSet.add(CT);
				}
				findAllSubSets(CT,pCrawl.children[i],stringSet);
				
			}
		}
		
		return stringSet;
	}
}
