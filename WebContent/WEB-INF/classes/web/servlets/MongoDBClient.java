package web.servlets;

import java.io.*;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;


public class MongoDBClient extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        String result = "";
        
        try {
         	
        	MongoClient mongo = new MongoClient( "localhost" , 27017 );
        	DB db = mongo.getDB("maindb");
        	boolean auth = db.authenticate("dbuser", "nibul7474".toCharArray());
         
        	DBCollection collection = db.getCollection("dantest");
        	
        	Date created = new Date(session.getCreationTime() - 14400000);

        	BasicDBObject mydoc = new BasicDBObject("Name", request.getParameter("name") ).
                    append("info", new BasicDBObject("SessionCreationTime", created ).append("SessionId", session.getId() ).append("RemoteAddress", request.getRemoteAddr())  );
         
        	WriteResult writeResult = collection.insert(mydoc);
        	result = writeResult.toString();
        	
        }

         catch (UnknownHostException e) {
        	e.printStackTrace();
        	result = e.getMessage();
            } catch (MongoException e) {
        	e.printStackTrace();
        	result = e.getMessage();
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
