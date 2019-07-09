package com.queryBuilder.Data.comparator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.queryBuilder.Data.core.NameValuePair;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
/**
 * 
 * @author Lakshmanan Subbiah
 * 09/07/2019 18:49 PM
 * Test cases for the Score Comparator Class
 *
 */
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
		assertThat(sc.compare(nv, nv2),equalTo(-1));
	}
	
	@Test
	void testSuccessSecondValueGreaterComparatorCase() {
		NameValuePair nv = new NameValuePair("Hello", 30);
		NameValuePair nv2 = new NameValuePair("Jesk",50);
		assertThat(sc.compare(nv, nv2),equalTo(1));
	}

	@Test
	void testSuccessEqualValueGreaterComparatorCase() {
		NameValuePair nv = new NameValuePair("Hello", 30);
		NameValuePair nv2 = new NameValuePair("Jesk",30);
		assertThat(sc.compare(nv, nv2),equalTo(1));
	}
	
	@Test
	void testTreeSetWorkingOfComparator() {
		SortedSet<NameValuePair> s = new TreeSet<NameValuePair>(sc);
		NameValuePair nv = new NameValuePair("Hello", 30);
		NameValuePair nv2 = new NameValuePair("Jesk",50);
		s.add(nv);
		s.add(nv2);
		
		NameValuePair n = s.iterator().next();
		
		assertThat(n.getName(),equalTo("Jesk"));
		assertThat(n.getScore(),equalTo(50));
	}
	
	@Test
	void testTreeSetMixingUpwithVariousOthers() {
		SortedSet<NameValuePair> s = new TreeSet<NameValuePair>(sc);
		NameValuePair nv = new NameValuePair("Hello", 30);
		NameValuePair nv2 = new NameValuePair("Jesk",50);
		s.add(nv);
		s.add(nv2);
		
		SortedSet<NameValuePair> s1 = new TreeSet<NameValuePair>(sc);
		NameValuePair nv3 = new NameValuePair("Jamie",20);
		NameValuePair nv4 = new NameValuePair("Jekar",90);
		NameValuePair nv5 = new NameValuePair("Janu",100);
		s1.add(nv2);
		s1.add(nv3);
		s1.add(nv4);
		s1.add(nv5);
		s1.add(nv);
		
		s.addAll(s1);
	NameValuePair n = s.iterator().next();
		
		assertThat(n.getName(),equalTo("Janu"));
		assertThat(n.getScore(),equalTo(100));
		assertThat(s1.size(),equalTo(5));
		
	}
}
