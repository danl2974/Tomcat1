package web.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class Tester extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
    	String longestId = "";
    	Integer longestTime = 0;
    	
    	Integer[] uids = {1,2,3,4,5,4,1,5,3,2,2,3};
    	Integer[] timestamps = {13,7,6,23,11,3,5,17,8,17,16,15};
    	
    	int i;
    	HashMap<Integer, ArrayList<Integer>> idmap = new HashMap<Integer, ArrayList<Integer>>();
    	
    	for (i=0; i < uids.length; i++){
    		
    		if( idmap.containsKey(uids[i]) )
    		{
    			idmap.get(uids[i]).add(timestamps[i]);
    		}
    		else
    		{
    		    idmap.put(uids[i], new ArrayList<Integer>());
    		    idmap.get(uids[i]).add(timestamps[i]);  
    		}
    		
    	}
    	
    	Iterator it = idmap.entrySet().iterator();
    	
    
    	while (it.hasNext()) {

    		Map.Entry entry = (Map.Entry) it.next();

    		Integer uidKey = (Integer) entry.getKey();

    		ArrayList<Integer> ts = (ArrayList<Integer>) entry.getValue();
    		
    		Collections.sort(ts);
    		
    		Integer difference = ( (Integer) ts.get(ts.size() - 1) ) - ( (Integer) ts.get(0) );

    		if (difference > longestTime){
    			longestTime = difference;
    			longestId = String.valueOf(uidKey);
    		}
    		
    		out.println(String.valueOf(uidKey) + ":  " + String.valueOf(difference) + "<br/>" );

    		}
    	
    	out.println("Winner is " + longestId + " with a time of " + String.valueOf(longestTime));
    	
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}
