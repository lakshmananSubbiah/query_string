package com.queryBuilder.Data.storage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.queryBuilder.Data.comparator.ScoreComparator;
import com.queryBuilder.Data.core.NameValuePair;

/**
 * 
 * @author Lakshmanan Subbiah
 * 10/07/2019 06:42 
 * This class will store the core map structure which contains the parts of the string and the 
 * Name and score associated with that string
 */
public class ScoreStore {

	 Map<String,SortedSet<NameValuePair>> store = new HashMap<String,SortedSet<NameValuePair>>();
	
	static final ScoreComparator sc = new ScoreComparator();
	
	/**
	 * 
	 * @return
	 */
	public Map<String,SortedSet<NameValuePair>>  getStore(){
		return store;
	}
	
	/**
	 * 
	 * @param String name
	 * @param Name Value Pair
	 */
	public void insertIntoStore(String name, NameValuePair nv) {
		SortedSet<NameValuePair> sv;
		if(store.containsKey(name)) {
			sv = store.get(name);
		}
		else {
			sv = new TreeSet<NameValuePair>(sc);
		}
		sv.add(nv);
		store.put(name, sv);
	}
	
	/**
	 * 
	 * @param keys
	 * @return
	 */
	public SortedSet<NameValuePair> getAllNameValuePairs(Set<String> keys){
		SortedSet<NameValuePair> st = new TreeSet<NameValuePair>(sc);
		if(keys == null || keys.isEmpty()) {
			return st;
		}
		for(String key : keys) {
			if(store.containsKey(key)) {
				st.addAll(store.get(key));
			}
		}
		return st;
	}
	
	/**
	 *  Returns the top 10 scorers in the given keyset
	 * @param keys
	 * @return
	 */
	public Set<NameValuePair> returnTop10NameValuePairs(Set<String> keys){
		Set<NameValuePair> set = new LinkedHashSet<NameValuePair>();
		SortedSet<NameValuePair> st = getAllNameValuePairs(keys);
		Iterator<NameValuePair> it  = st.iterator();
		int count = 0;
		while(it.hasNext() && count < 10) {
			NameValuePair pair = it.next();
			if(!set.contains(pair)) {
				set.add(pair);
				count ++;
			}
		}
		return set;
	}
	
	/**
	 * 
	 */
	
	public void reinitialize() {
		store  =new HashMap<String,SortedSet<NameValuePair>>(); 
	}
	
	/**
	 * 
	 * @param ScoreStore sc
	 */
	
	public void reinitialize(ScoreStore sc) {
		this.store = sc.store;
	}
	
	
	
}
