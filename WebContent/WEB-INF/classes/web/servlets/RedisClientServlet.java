package web.servlets;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

import redis.clients.jedis.Jedis;


public class RedisClientServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String result = "";
        
        String action = request.getParameter("action");
        String rkey = request.getParameter("key");
        
        if ( action.equals("set") ){
        
           try {
               Date timeNow = new Date();
               SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
               String rvalue = ft.format(timeNow);
        	
        	   Jedis jedis = new Jedis("localhost");
        	   String setStatus = jedis.set(rkey, rvalue);
        	   jedis.expire(rkey, 10);
        	   result = setStatus;
            }
            catch (Exception e) {
        	   result = e.getMessage();
            }
        
        }
        
        else if ( action.equals("get") ){
            
            try {

         	   Jedis jedis = new Jedis("localhost");
         	   String getValue = jedis.get(rkey);
         	   result = getValue;
             }
             catch (Exception e) {
         	   result = e.getMessage();
             }
         
         }
        
        else{
        	
        	result = "Specify action parameter";
        }
        
        
        
        
        out.println(result);
        
    }

    /**
     * We are going to perform the same operations for POST requests
     * as for GET methods, so this method just sends the request to
     * the doGet method.
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}
