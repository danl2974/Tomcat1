package web.servlets;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head>");
        out.println("<title>Request Information Example</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Request Information Example</h3>");
        
        out.println("Header: <br/><br/>");
        
        Enumeration<String> hdrf = request.getHeaderNames();
        
        while(hdrf.hasMoreElements()){
        	String elem = hdrf.nextElement();
        	out.println( elem + " : " + request.getHeader(elem) + "<br/>" );
        }
        
        out.println("<br/><br/>");
        
        out.println("Method: " + request.getMethod());
        out.println("Request URI: " + request.getRequestURI());
        out.println("Protocol: " + request.getProtocol());
        out.println("PathInfo: " + request.getPathInfo());
        out.println("Remote Address: " + request.getRemoteAddr());
        out.println("</body>");
        out.println("</html>");
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
