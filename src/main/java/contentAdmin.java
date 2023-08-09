

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.conn;

/**
 * Servlet implementation class contentAdmin
 */
@WebServlet("/contentAdmin")
public class contentAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contentAdmin() {
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
		String cid = request.getParameter("coursename");
		String maintopic = request.getParameter("mtopic");
		String subtopic = request.getParameter("stopic");
		String content = request.getParameter("content");
		
		String coursename="";
		int mainid,subid;
		
		Connection connection = null;
	    conn con;
	    
	    try {
   	 		con = new conn();
   	 		connection = conn.connect();
   	 		Statement stmt = connection.createStatement();
   	 		String sql = "select cname from courses where id='"+cid+"'";
   	 		ResultSet rs=stmt.executeQuery(sql);
   	 		
   	 		if(rs.next()) {
   	 			coursename = rs.getString(1);
   	 		}
   	 		
   	 		String sql1 = "insert ignore into main_topic(id,cid,mtopic) VALUES(null,'"+cid+"','"+maintopic+"')";
   	 		stmt.execute(sql1);
   	 		
   	 		
   	 		String sql2 = "select id from main_topic where mtopic='"+maintopic+"'";
	 		ResultSet rs1=stmt.executeQuery(sql2);
	 		
	 		if(rs1.next()) {
	 			mainid = rs1.getInt(1);
	 			String sql3 = "insert ignore into sub_topic(id,mainid,stopic) VALUES(null,'"+mainid+"','"+subtopic+"')";
	   	 		stmt.execute(sql3);
	 		}
	 		
	 		String sql3 = "select id from sub_topic where stopic='"+subtopic+"'";
	 		ResultSet rs2=stmt.executeQuery(sql3);
	 		
	 		if(rs2.next()) {
	 			subid = rs2.getInt(1);
	 		}
	 		
	 		String filename = coursename+"_"+maintopic+"_"+subtopic+".txt";
	 		
	 		File myfile = new File("D:\\Study\\MCA\\sem II\\ajava\\Practical\\E-learning portal\\src\\main\\webapp\\data\\"+filename);
	 		FileWriter myWriter = new FileWriter("D:\\Study\\MCA\\sem II\\ajava\\Practical\\E-learning portal\\src\\main\\webapp\\data\\"+filename);
	 		myWriter.write(content);
	 		myWriter.close();
	 		response.sendRedirect("Admin/content.jsp?res=1");
   	 	}catch(Exception e) {

   	 		e.printStackTrace();
   	 	}
	    
	    
	}

}
