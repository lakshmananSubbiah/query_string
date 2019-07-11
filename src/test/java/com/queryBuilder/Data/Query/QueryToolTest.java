package com.queryBuilder.Data.Query;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.queryBuilder.Data.constants.QueryBuilderConstants;
import com.queryBuilder.Data.core.NameValuePair;
/**
 * 
 * @author Lakshmanan Subbiah
 * 11/07/2019 07:54
 *
 * Test cases for the Query Tool
 */
class QueryToolTest {

	static QueryTool queryTool;
	
	@BeforeAll
	static void initialize() {
		Random random = new Random();
		queryTool = new QueryTool();
		for(int i=0;i<100000;i++) {
			int length = random.nextInt(7);
			StringBuilder builder  = new StringBuilder();
			for(int j = 0;j<length;j++) {
				int element = random.nextInt(51);
				builder.append(QueryBuilderConstants.NumberVsChars.get(element));
				int value = random.nextInt(1000);
				queryTool.insert(builder.toString(), value);
			}
		}
	}
	
	@Test
	void firstSuccessCase() {
		queryTool.insert("sample", 15000);
		queryTool.insert("samd", 1200);
		Set<NameValuePair> set = queryTool.getTop10ScorersOnPrefix("sam");
		Iterator<NameValuePair> iter = set.iterator();
		assertThat(iter.next().getName(),equalTo("sample"));
		assertThat(iter.next().getName(),equalTo("samd"));
	}
	

}
