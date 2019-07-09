package com.queryBuilder.Data.comparator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.queryBuilder.Data.core.NameValuePair;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

class ScoreComparatorTest {
	
	static ScoreComparator sc;
	
	@BeforeAll
	static void initializeScoreComparatorObject() {
		sc  = new ScoreComparator();
	}

	@Test
	void testSuccessComparatorCase() {
		NameValuePair nv = new NameValuePair("Hello", 30);
		NameValuePair nv2 = new NameValuePair("Jesk",20);
		assertThat(sc.compare(nv, nv2),equalTo(1));
	}
	
	@Test
	void testSuccessSecondValueGreaterComparatorCase() {
		NameValuePair nv = new NameValuePair("Hello", 30);
		NameValuePair nv2 = new NameValuePair("Jesk",50);
		assertThat(sc.compare(nv, nv2),equalTo(-1));
	}

	@Test
	void testSuccessEqualValueGreaterComparatorCase() {
		NameValuePair nv = new NameValuePair("Hello", 30);
		NameValuePair nv2 = new NameValuePair("Jesk",30);
		assertThat(sc.compare(nv, nv2),equalTo(-1));
	}
	
}
