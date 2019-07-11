package com.queryBuilder.Data.retrievalBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.queryBuilder.Data.constants.QueryBuilderConstants;

/**
 * 
 * @author Lakshmanan Subbiah
 * 10/07/2019 20:40 
 * Tests to verify the Trie class consistency.
 *
 */
class Trietest {

	static final Trie trie = new Trie();
	
	@BeforeAll
	static void initializeTriewithRandomStrings() {
		Random random = new Random();
		for(int i = 0;i< 10000000; i++) {
			int length = random.nextInt(7);
			StringBuilder builder  = new StringBuilder();
			for(int j = 0;j<length;j++) {
				int element = random.nextInt(51);
				builder.append(QueryBuilderConstants.NumberVsChars.get(element));
			}
			trie.insert(builder.toString());
		}
	}
	
	@Test
	void testSingleStringInsertinTrie() {
		trie.insert("sample");
		Set<String> set = trie.search("sam");
		assertThat(set.contains("sample"),equalTo(true));
	}
	
	@Test
	void testMultipleStringInsertInTrie() {
		trie.insert("sampleyoo");
		trie.insert("sam");
		trie.insert("sammu");
		trie.insert("samantha");
		Set<String> set = trie.search("sam");
		assertThat(set.contains("sampleyoo"),equalTo(true));
		assertThat(set.contains("samantha"),equalTo(true));
		if(set.size()<4) {
			fail("Size is less than four");
		}
	}
	
	@Test
	void testNonExistentString() {
		trie.insert("sam");
		trie.insert("sammu");
		Set<String> set = trie.search("samsdfksfjh");
		assertThat(set.size(),equalTo(0));
	}
	
	@Test
	void testEmpty() {
		trie.insert("sam");
		trie.insert("sammu");
		RuntimeException exception = assertThrows(RuntimeException.class,()->{
			trie.search(null);
		});
		assertThat(exception.getMessage(),equalTo("search word cannot be null."));
	}
	
	@Test
	void insertStringWrong() {
		
		RuntimeException exception = assertThrows(RuntimeException.class,()->{
			trie.insert("28skdfs");
			trie.insert("sammu");
			trie.search(null);
		});
		assertThat(exception.getMessage(),equalTo("Not a character"));
	}
	
	@Test
	void testTrieWithEmptyString() {
		trie.insert("sam");
		trie.insert("sammu");
		Set<String> set = trie.search("");
		System.out.println(set.size());
		if(set.size()<100) {
			fail("Error on retrieving");
		}
	}
}
