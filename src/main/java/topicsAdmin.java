

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.conn;

/**
 * Servlet implementation class topicsAdmin
 */
@WebServlet("/topicsAdmin")
public class topicsAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public topicsAdmin() {
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
		String main_topic = request.getParameter("mtopic");
		String sub_topic = request.getParameter("stopic");
		Connection connection = null;
	    conn con;
	    try {
   	 		con = new conn();
   	 		connection = conn.connect();
   	 		Statement stmt = connection.createStatement();
		
   	 		if(main_topic == "" || main_topic == null) {
				out.println(sub_topic);
			}else if(sub_topic == "" || sub_topic == null) {
				out.print(main_topic);
			}
   	 		
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

}
