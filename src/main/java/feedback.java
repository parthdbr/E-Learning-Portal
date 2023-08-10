

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.conn;

/**
 * Servlet implementation class feedback
 */
@WebServlet("/feedback")
public class feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public feedback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		PrintWriter out = response.getWriter();
		
		
		 
	     String name = request.getParameter("name");
	     String msg = request.getParameter("msg");
	     
	     
	     
	     Connection connection = null;
    	 conn con;
    	 int id;
    	 String m;
    	 int rid;
    	 
    	 try {
    		 con = new conn();
    		 connection = conn.connect();
    		 Statement stmt = connection.createStatement();
    		 
    		 HttpSession session=request.getSession(false);
    	     String mail=(String)session.getAttribute("mail");
    	     
    	     if(mail == null) {
    	    	 response.sendRedirect("index.jsp?r=false");
    	     }
    	      
    	     String sql = "select id,mail from register where mail='"+mail+"'";
    		 ResultSet rs=stmt.executeQuery(sql);
    		 
    		 
    		 
    		 while(rs.next()) {
    			 rid=rs.getInt(1);
    			 m=rs.getString(2);
    			 
    			 if(mail.equals(m)) {
    				 out.print("mails matched "+rid+" "+m);
        			 String sql1 = "insert into feedback (rid,name,msg) VALUES ('"+rid+"','"+name+"','"+msg+"')";
        			 stmt.executeUpdate(sql1);
        			 response.sendRedirect("index.jsp?r=true");
        		 }else {
        			 out.print("not Executed");
        			 response.sendRedirect("index.jsp?r=false");
        		 }
    		 }
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    	 }catch(Exception e) {
    		 /*out.print("Database is not connected");*/
    		 out.print("something else");
    		 /*response.sendRedirect("registration/login.jsp?res=false");*/
    	 }

	}

}
