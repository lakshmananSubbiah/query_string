package query_strings.query_strings;

import com.queryBuilder.Data.core.NameValuePair;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        NameValuePair nvPair = new NameValuePair("hello",30);
        
        System.out.println(nvPair.getName());
        System.out.println(nvPair.getScore());
    }
}
