

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import connection.conn;

/**
 * Servlet implementation class courseAdmin
 */
@WebServlet("/courseAdmin")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class courseAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
   
	/**
     * @see HttpServlet#HttpServlet()
     */
    public courseAdmin() {
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
		
		final PrintWriter out = response.getWriter();
		
		final String coursename = request.getParameter("coursename");
		final String popularity = request.getParameter("popularity");

		
		
		final Part filePart = request.getPart("img");
	    final String fileName = getSubmittedFileName(filePart); // For Servlet 3.1.0 and higher filePart.getSubmittedFileName();  
	    //final String path = getServletContext().getRealPath("/"+"images"+File.separator+fileName);  
	    String path = "D:\\Study\\MCA\\sem II\\ajava\\Practical\\E-learning portal\\src\\main\\webapp\\images\\"+fileName;
	    filePart.write(path); 
	    
	    /*String coursefile = coursename+".txt"; 
	    
        //File file = new File(getServletContext().getRealPath("/"+"data"+File.separator+coursefile)); //initialize File object and passing path as argument  
	    File file = new File("D:\\Study\\MCA\\sem II\\ajava\\Practical\\E-learning portal\\src\\main\\webapp\\data\\"+coursefile);
	    file.createNewFile();  //creates a new file  
	            
	    //out.print(path);
	   // out.println(fileName); */
	    
	    String res,course;
	    Connection connection = null;
	    conn con;
   	 	try {
   	 		con = new conn();
   	 		connection = conn.connect();
   	 		Statement stmt = connection.createStatement();
   	 		
   	 		
   	 		String sql1 = "select * from courses where cname='"+coursename+"'";
   	 		ResultSet rs=stmt.executeQuery(sql1);
   	 		
	   	 	if (rs.next()) {
					 response.sendRedirect("Admin/courseAdmin.jsp?failed=true");
	   	 	}else {
	   	 		
	   	 		String sql = "INSERT INTO courses VALUES (null, '"+coursename+"', '"+popularity+"', '"+fileName+"')";
	   	 		stmt.execute(sql);
	   	 		response.sendRedirect("Admin/courseAdmin.jsp?res=true");
	   	 	}
   	 	}catch(Exception e) {
   	 	response.sendRedirect("Admin/courseAdmin.jsp?failed=true");
   	 	}
	    
	}
	
	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
}
