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
    public static void main( String[] args ) throws  IOException
    {
    	try {
    		QueryTool queryTool = new QueryTool();
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	boolean flag = true;
	        while(flag) {
	        	System.out.println("Enter your option from 1 to 5/ 0 to Exit");
	        	System.out.println("1. Insert data via file");
	        	System.out.println("2. Insert Data Manually");
	        	System.out.println("3. Load an already filled name and score pairs");
	        	System.out.println("4. Store the Current data into a file");
	        	System.out.println("5. Search for a prefix");
	        	int option = Integer.parseInt(br.readLine());
	        	switch(option) {
	        		case 1: 
	        			getValueFromFile(br,queryTool);
	        			break;
	        		case 2:
	        			getNameValuePairsFromUsers(br,queryTool);
	        			break;
	        		case 3:
	        			break;
	        		case 4:
	        			break;
	        		case 5:
	        			searchForaPrefix(br,queryTool);
	        			break;
	        		case 0: System.out.print("Thank you for your time");
	        		default:
	        			System.out.println("Exiting the program ");
	        			flag = false;
	        			break;
	        	}
	        }
    	}
    	catch(NumberFormatException nf) {
    		System.out.println("Not a Valid Number ----- Exiting Program Now");
    	}
    }

	private static void getValueFromFile(BufferedReader br, QueryTool queryTool) {
		System.out.println("Enter the absolute path of file");
		
	}

	private static void searchForaPrefix(BufferedReader br, QueryTool queryTool) throws IOException {
		  System.out.println("Enter the prefix to be searched for");
	        String prefix = br.readLine();
	        
	        System.out.println("The top results");
	        
	        Set<NameValuePair> set = queryTool.getTop10ScorersOnPrefix(prefix);
	        for(NameValuePair s : set) {
	        	System.out.println(s);
	        }
		
	}

	private static void getNameValuePairsFromUsers(BufferedReader br, QueryTool queryTool) throws NumberFormatException, IOException {
		System.out.println("Enter the number of Name Value pairs");
        
        int size = Integer.parseInt(br.readLine());
        System.out.println("Enter the Name and values comma seperated");
     
        for(int i=0;i<size;i++) {
        	String str = br.readLine();
        	queryTool.insert(str.split(",")[0], Integer.parseInt(str.split(",")[1]));
        }
		
	}
}
