package query_strings.query_strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import com.queryBuilder.Data.Query.QueryTool;
import com.queryBuilder.Data.core.NameValuePair;

/**
 * 
 * @author Lakshmanan Subbiah
 *11/07/2019 13:11
 *Sample Main function
 */
public class App 
{
    public static void main( String[] args ) throws NumberFormatException, IOException
    {
        System.out.println("Enter the number of Name Value pairs");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        System.out.println("Enter the Name and values comma seperated");
        QueryTool queryTool = new QueryTool();
        for(int i=0;i<size;i++) {
        	String str = br.readLine();
        	queryTool.insert(str.split(",")[0], Integer.parseInt(str.split(",")[1]));
        }
        
        System.out.println("Enter the prefix to be searched for");
        String prefix = br.readLine();
        
        System.out.println("The top results");
        
        Set<NameValuePair> set = queryTool.getTop10ScorersOnPrefix(prefix);
        for(NameValuePair s : set) {
        	System.out.println(s);
        }
    }
}
