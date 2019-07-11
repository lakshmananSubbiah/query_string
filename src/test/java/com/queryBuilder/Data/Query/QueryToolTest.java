package com.queryBuilder.Data.Query;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		for(int i=0;i<1000000;i++) {
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
		queryTool.insert("samd", 12000);
		Set<NameValuePair> set = queryTool.getTop10ScorersOnPrefix("sam");
		Iterator<NameValuePair> iter = set.iterator();
		assertThat(iter.next().getName(),equalTo("sample"));
		assertThat(iter.next().getName(),equalTo("samd"));
	}
	
	
	
	@Test
	void tenSuccessCase() {
		queryTool.insert("sample", 15000);
		queryTool.insert("samd", 1200);
		queryTool.insert("samdee", 2900);
		queryTool.insert("samdev", 2920);
		queryTool.insert("samdeo", 2910);
		queryTool.insert("samden", 2800);
		queryTool.insert("samdex", 2940);
		queryTool.insert("samdesj", 2760);
		queryTool.insert("samskd", 2000);
		queryTool.insert("sammox", 1900);
		Set<NameValuePair> set = queryTool.getTop10ScorersOnPrefix("sam");
		Iterator<NameValuePair> iter = set.iterator();
		assertThat(iter.next().getName(),equalTo("sample"));
		assertThat(iter.next().getName(),equalTo("samdex"));
	}
	
	
	@Test
	void tenInvolvingSplitSuccessCase() {
		queryTool.insert("max_sum", 15200);
		queryTool.insert("summed", 12090);
		queryTool.insert("max_summed", 29200);
		queryTool.insert("nail_sum", 2920);
		queryTool.insert("eosi_sum", 27120);
		queryTool.insert("omg_sum", 2900);
		queryTool.insert("postit_summing", 2940);
		queryTool.insert("summer_camp", 2760);
		queryTool.insert("this_summer", 2000);
		queryTool.insert("summers", 1900);
		queryTool.insert("post_summer", 2000);
		Set<NameValuePair> set = queryTool.getTop10ScorersOnPrefix("sum");
		Iterator<NameValuePair> iter = set.iterator();
		assertThat(set.size(),equalTo(10));
		assertThat(iter.next().getName(),equalTo("max_summed"));
		assertThat(iter.next().getName(),equalTo("eosi_sum"));
		assertThat(iter.next().getName(),equalTo("max_sum"));
		assertThat(iter.next().getName(),equalTo("summed"));
		assertThat(iter.next().getName(),equalTo("postit_summing"));
		assertThat(iter.next().getName(),equalTo("nail_sum"));
		assertThat(iter.next().getName(),equalTo("omg_sum"));
		assertThat(iter.next().getName(),equalTo("summer_camp"));
		assertThat(iter.next().getName(),equalTo("this_summer"));
		assertThat(iter.next().getName(),equalTo("post_summer"));
	}
	
	@Test
	void nullCaseCheckinQueryTool() {
		RuntimeException exception = assertThrows(RuntimeException.class,()->{
			queryTool.getTop10ScorersOnPrefix(null);
		});
		assertThat(exception.getMessage(),equalTo("search word cannot be null."));
	}

	
	@Test
	void reverseandFrontSwapTest() {
		queryTool.insert("abc", 40000);
		queryTool.insert("abc_max", 41000);
		queryTool.insert("sup_abc", 42000);
		queryTool.insert("abc_axy_max", 43000);
		queryTool.insert("xxx_abc", 45000);
		queryTool.insert("acb_abc", 44000);
		queryTool.insert("abc_axm", 39000);
		queryTool.insert("maxmax_abc", 46000);
		queryTool.insert("max_abc", 47000);
		queryTool.insert("abc_iwd", 48000);
		queryTool.insert("abc_akxm", 42500);
		queryTool.insert("axmmabc", 50000);
		Set<NameValuePair> set = queryTool.getTop10ScorersOnPrefix("abc");
		Iterator<NameValuePair> iter = set.iterator();
		assertThat(set.size(),equalTo(10));
		assertThat(iter.next().getName(),equalTo("abc_iwd"));
		assertThat(iter.next().getName(),equalTo("max_abc"));
		assertThat(iter.next().getName(),equalTo("maxmax_abc"));
		assertThat(iter.next().getName(),equalTo("xxx_abc"));
		assertThat(iter.next().getName(),equalTo("acb_abc"));
		assertThat(iter.next().getName(),equalTo("abc_axy_max"));
		assertThat(iter.next().getName(),equalTo("abc_akxm"));
		assertThat(iter.next().getName(),equalTo("sup_abc"));
		assertThat(iter.next().getName(),equalTo("abc_max"));
		assertThat(iter.next().getName(),equalTo("abc"));
	}
	
	@Test
	void emptyCaseTest() {
		queryTool.insert("abc", 40000);
		queryTool.insert("abc_max", 41000);
		queryTool.insert("sup_abc", 42000);
		queryTool.insert("abc_axy_max", 43000);
		queryTool.insert("xxx_abc", 45000);
		queryTool.insert("acb_abc", 44000);
		queryTool.insert("abc_axm", 39000);
		queryTool.insert("maxmax_abc", 46000);
		queryTool.insert("max_abc", 47000);
		queryTool.insert("abc_iwd", 48000);
		queryTool.insert("abc_akxm", 42500);
		queryTool.insert("axmmabc", 50000);
		Set<NameValuePair> set = queryTool.getTop10ScorersOnPrefix("");
		Iterator<NameValuePair> iter = set.iterator();
		assertThat(set.size(),equalTo(10));
		assertThat(iter.next().getName(),equalTo("axmmabc"));
		assertThat(iter.next().getName(),equalTo("abc_iwd"));
		assertThat(iter.next().getName(),equalTo("max_abc"));
		assertThat(iter.next().getName(),equalTo("maxmax_abc"));
		assertThat(iter.next().getName(),equalTo("xxx_abc"));
		assertThat(iter.next().getName(),equalTo("acb_abc"));
		assertThat(iter.next().getName(),equalTo("abc_axy_max"));
		assertThat(iter.next().getName(),equalTo("abc_akxm"));
		assertThat(iter.next().getName(),equalTo("sup_abc"));
		assertThat(iter.next().getName(),equalTo("abc_max"));
		
	}
}
