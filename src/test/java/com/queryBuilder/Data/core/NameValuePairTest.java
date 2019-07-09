package com.queryBuilder.Data.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.queryBuilder.Data.core.NameValuePair;

/**
 * 
 * @author Lakshmanan Subbiah
 * 09/07/2019 - 16:42 
 * 
 * Test cases for the NameValuePairTest POJO
 * 
 */

class NameValuePairTest {

	
	@Test
	void NameValueSuccessCreateTest() {
		NameValuePair nv = new NameValuePair("Sample",30);
		assertThat(nv.getName(),equalTo("Sample"));
		assertThat(nv.getScore(),equalTo(30));
	}
	
	@Test
	void NameValueValueNullCase() {
		RuntimeException exception = assertThrows(RuntimeException.class,()->{
			new NameValuePair(null,50);
		});
		assertThat(exception.getMessage(),equalTo("Name cannot be null."));
	}
	
	@Test
	void NameValueStringsSplitCase() {
		NameValuePair nv = new NameValuePair("Sample",30);
		String split[] = nv.getAllSubStrings();
		assertThat(split[0],equalTo("Sample"));
		assertThat(split.length,equalTo(1));
	}
	
	@Test
	void NameValueStringsSplitMoreThanOneCase() {
		NameValuePair nv = new NameValuePair("Sample_Max_Size",30);
		String split[] = nv.getAllSubStrings();
		assertThat(split[2],equalTo("Size"));
		assertThat(split.length,equalTo(3));
	}

}
