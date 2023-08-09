import connection.conn;

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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
	     String pass = request.getParameter("your_pass");
	     String email = request.getParameter("email");
	     Connection connection = null;
    	 conn con;
    	 int id;
    	 String p="",m="",n="",eq=null;
    	 try {
    		 con = new conn();
    		 connection = conn.connect();
    		 Statement stmt = connection.createStatement();
    		 String sql = "select * from register where mail='"+email+"'";
    		 ResultSet rs=stmt.executeQuery(sql);
    		 while (rs.next()) {   
    			 id = rs.getInt(1);          
    			 n = rs.getString(2).trim();
    			 p = rs.getString(3).trim();
    			 m = rs.getString(4).trim(); 
    			 if(pass.equals(p)) {
        			 HttpSession session = request.getSession(true);
        			 session.setAttribute("user", n);
        			 session.setAttribute("mail", m);
        			  response.sendRedirect("index.jsp?res=true");
        		 }else {
        			 response.sendRedirect("registration/login.jsp?res=false");
        		 }
    		 }
    	}catch(Exception e) {
    		 /*out.print("Database is not connected");*/
    		 response.sendRedirect("registration/login.jsp?res=false");
    	 }
    }
}
