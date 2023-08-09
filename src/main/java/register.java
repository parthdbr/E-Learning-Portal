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

/**
 * Servlet implementation class register
 */


@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		response.setContentType("text/Html");
		
		 PrintWriter out = response.getWriter();
		 String username = request.getParameter("name").trim();
	     String pass = request.getParameter("pass").trim();
	     String rpass = request.getParameter("re_pass").trim();
	     String email = request.getParameter("email").trim();
	     if(pass.equals(rpass)) {
	    	 Connection connection = null;
	    	 conn con;
	    	 int id;
	    	 String m="",p="",n="",eq=null;
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
	    		 }
	    		 if(email.equals(m)) {
	    			eq="yes";
					response.sendRedirect("registration/signup.jsp?m="+eq);
	    		 }else {
	    			 String sql1 = "INSERT INTO register VALUES (null, '"+username+"', '"+pass+"', '"+email+"')";
	    			 stmt.execute(sql1);
	    			 HttpSession session = request.getSession(true);
	    			 session.setAttribute("user", n);
	    			 response.sendRedirect("index.jsp?res=true");
	    		 }
	    	}catch(Exception e) {
	    		 out.print("Database is not conneted");
	    	 }
	    	/* response.sendRedirect("index.jsp?res=true");*/
	     }else {
	    	 response.sendRedirect("registration/signup.jsp?res=false");
	     }
	}

}
