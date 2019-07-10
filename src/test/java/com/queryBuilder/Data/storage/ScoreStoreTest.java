package com.queryBuilder.Data.storage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.Test;

import com.queryBuilder.Data.core.NameValuePair;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * 
 * @author Lakshmanan Subbiah
 * 10/07/2019 07:20
 *
 *Test cases for the ScoreStore Class
 */
class ScoreStoreTest {

	@Test
	void successCaseTest() {
		ScoreStore st = new ScoreStore();
		st.insertIntoStore("sample", new NameValuePair("sample",59));
		st.insertIntoStore("pay", new NameValuePair("pay_back",29));
		st.insertIntoStore("back", new NameValuePair("pay_back",29));
		st.insertIntoStore("sample", new NameValuePair("Mack_sample",80));
		
		Set<String> set = new HashSet<String>();
		set.add("sample");
		
		SortedSet<NameValuePair> nv = st.getAllNameValuePairs(set);
		assertThat(nv.size(),equalTo(2));
		assertThat(nv.iterator().next().getName(),equalTo("Mack_sample"));
	}

	@Test
	void getTop10ScorersSuccessTestCase() {
		ScoreStore st = new ScoreStore();
		st.insertIntoStore("sample", new NameValuePair("sample",59));
		st.insertIntoStore("pay", new NameValuePair("pay_back",29));
		st.insertIntoStore("back", new NameValuePair("pay_back",29));
		st.insertIntoStore("sample", new NameValuePair("Mack_sample",80));
		st.insertIntoStore("Mack", new NameValuePair("Mack_sample",80));
		st.insertIntoStore("pay", new NameValuePair("Mobi_pay",100));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_pay",100));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi",2939));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_Day",1200));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_may",2038));
		st.insertIntoStore("sample", new NameValuePair("sample_data",2));
		st.insertIntoStore("sample", new NameValuePair("data_sample",100));
		st.insertIntoStore("sample", new NameValuePair("Mobi_sample",920));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_sample",920));
		st.insertIntoStore("sample", new NameValuePair("Mackinng_sample",20));
		Set<String> set = new HashSet<String>();
		set.add("sample");
		set.add("Mobi");
		
		Set<NameValuePair> scores = st.returnTop10NameValuePairs(set);
		
		assertThat(scores.size(),equalTo(10));
		Iterator<NameValuePair> it = scores.iterator();
		assertThat(it.next().getName(),equalTo("Mobi"));
		assertThat(it.next().getName(),equalTo("Mobi_may"));
		for(int i =0;i<7;i++) {
			it.next();
		}
		assertThat(it.next().getName(),equalTo("Mackinng_sample"));
	}
	
	@Test
	void verifyWithaNonExistentKey() {
		ScoreStore st = new ScoreStore();
		st.insertIntoStore("sample", new NameValuePair("sample",59));
		st.insertIntoStore("pay", new NameValuePair("pay_back",29));
		st.insertIntoStore("back", new NameValuePair("pay_back",29));
		st.insertIntoStore("sample", new NameValuePair("Mack_sample",80));
		st.insertIntoStore("Mack", new NameValuePair("Mack_sample",80));
		st.insertIntoStore("pay", new NameValuePair("Mobi_pay",100));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_pay",100));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi",2939));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_Day",1200));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_may",2038));
		st.insertIntoStore("sample", new NameValuePair("sample_data",2));
		st.insertIntoStore("sample", new NameValuePair("data_sample",100));
		st.insertIntoStore("sample", new NameValuePair("Mobi_sample",920));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_sample",920));
		st.insertIntoStore("sample", new NameValuePair("Mackinng_sample",20));
		Set<String> set = new HashSet<String>();
		set.add("sample1");
		set.add("Mobi1");
		
		Set<NameValuePair> scores = st.returnTop10NameValuePairs(set);
		
		assertThat(scores.size(),equalTo(0));
	}
	
	@Test
	void emptyKeysCase() {
		ScoreStore st = new ScoreStore();
		st.insertIntoStore("sample", new NameValuePair("sample",59));
		st.insertIntoStore("pay", new NameValuePair("pay_back",29));
		st.insertIntoStore("back", new NameValuePair("pay_back",29));
		st.insertIntoStore("sample", new NameValuePair("Mack_sample",80));
		st.insertIntoStore("Mack", new NameValuePair("Mack_sample",80));
		st.insertIntoStore("pay", new NameValuePair("Mobi_pay",100));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_pay",100));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi",2939));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_Day",1200));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_may",2038));
		st.insertIntoStore("sample", new NameValuePair("sample_data",2));
		st.insertIntoStore("sample", new NameValuePair("data_sample",100));
		st.insertIntoStore("sample", new NameValuePair("Mobi_sample",920));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_sample",920));
		st.insertIntoStore("sample", new NameValuePair("Mackinng_sample",20));
		Set<String> set = new HashSet<String>();
		
		Set<NameValuePair> scores = st.returnTop10NameValuePairs(set);
		
		assertThat(scores.size(),equalTo(0));
	}
	
	@Test
	void nullKeysCase() {
		ScoreStore st = new ScoreStore();
		st.insertIntoStore("sample", new NameValuePair("sample",59));
		st.insertIntoStore("pay", new NameValuePair("pay_back",29));
		st.insertIntoStore("back", new NameValuePair("pay_back",29));
		st.insertIntoStore("sample", new NameValuePair("Mack_sample",80));
		st.insertIntoStore("Mack", new NameValuePair("Mack_sample",80));
		st.insertIntoStore("pay", new NameValuePair("Mobi_pay",100));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_pay",100));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi",2939));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_Day",1200));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_may",2038));
		st.insertIntoStore("sample", new NameValuePair("sample_data",2));
		st.insertIntoStore("sample", new NameValuePair("data_sample",100));
		st.insertIntoStore("sample", new NameValuePair("Mobi_sample",920));
		st.insertIntoStore("Mobi", new NameValuePair("Mobi_sample",920));
		st.insertIntoStore("sample", new NameValuePair("Mackinng_sample",20));
		
		Set<NameValuePair> scores = st.returnTop10NameValuePairs(null);
		
		assertThat(scores.size(),equalTo(0));
	}
}
