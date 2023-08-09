<%@page import="java.util.Scanner" %>
<%@page import="java.sql.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.ObjectOutputStream"%>

<jsp:include page="header.jsp" />



<jsp:include page="nav.jsp" />

<div style="margin-top:5.5%; margin-bottom:5.5%; height:100%">

<%
	String content = request.getParameter("content");
	//out.print(content);
try {
	Class.forName("com.mysql.jdbc.Driver");
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
int cid=0;

try{ 
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
	statement=connection.createStatement();
	String sql = "SELECT * FROM courses where cname='"+content+"'";
	resultSet = statement.executeQuery(sql);
	while(resultSet.next()){
		cid = resultSet.getInt(1);
	}
}catch (Exception e) {
	e.printStackTrace();
}
	
%>
	<ul class="nav flex-column" style="position:absolute; width: 150px; height:100%;">
	   <li class="nav-item">
	   		<a class="nav-link" href="content.jsp?content=<% out.print(content); %>" style="color:green;"><% out.print(content); %> HOME</a>
	   </li>
	  <%
							
							
							try{ 
							connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
							statement=connection.createStatement();
							String sql = "SELECT * FROM main_topic where cid='"+cid+"'";
							
							resultSet = statement.executeQuery(sql);
							while(resultSet.next()){
								String mn = resultSet.getString("mtopic");
								String mid = resultSet.getString("id");
								String compare = content+" "+"HOME";
								
								if(mn.equals(compare)) {
									continue;
								}
								
							%>
	 <li class="nav-item dropdown">
	    <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false" style="color:green;"><% out.print(mn); %></a>
	    <div class="dropdown-menu">
	   <%
	   						try{
							connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
							statement=connection.createStatement();
							String sql1 = "SELECT * FROM sub_topic where mainid='"+mid+"'";
							
							ResultSet rs = statement.executeQuery(sql1);
							while(rs.next()){
								String sn = rs.getString("stopic");
								
								
								
							%>
	    	
          <a class="dropdown-item" href="content.jsp?content=<% out.print(content); %>&sid=<%out.print(rs.getInt("id"));%>"><% out.print(sn); %></a>
          <%
							}
							connection.close();
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
        </div>
	  </li>
	  <%
							}
							connection.close();
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
	  
	</ul>

	<div style="margin-left: 150px; font-size: 20px; padding-left:50px; padding-top:20px; color:black;">
	<%
	int i;
	if(request.getParameter("sid")!=null){
		int id = Integer.parseInt(request.getParameter("sid"));
		String filename="";
		//out.print(id);
		try{
							connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
							statement=connection.createStatement();
							String sql = "select s.stopic,m.mtopic,c.cname from sub_topic AS s left join main_topic AS m ON s.mainid=m.id left join courses AS c ON m.cid=c.id where c.cname='"+content+"' AND s.id='"+id+"'";
							ResultSet rs = statement.executeQuery(sql);
							if(rs.next()){
								String subtopic = rs.getString("stopic");
								String maintopic = rs.getString("mtopic");
								filename = content+"_"+maintopic+"_"+subtopic+".txt";
							}
							connection.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		//out.print(filename);
		FileInputStream f = new FileInputStream("D:\\Study\\MCA\\sem II\\ajava\\Practical\\E-learning portal\\src\\main\\webapp\\data\\"+filename);
		Scanner sc = new Scanner(f);
		while(sc.hasNextLine())  
		{  
			out.print(sc.nextLine()+"<br>");      //returns the line that was skipped 
			
		}  
		sc.close();
		f.close();
	}else{
		String filename = content+"_"+content+" HOME_.txt";
		FileInputStream f = new FileInputStream("D:\\Study\\MCA\\sem II\\ajava\\Practical\\E-learning portal\\src\\main\\webapp\\data\\"+filename);
		Scanner sc = new Scanner(f);
		while(sc.hasNextLine())  
		{  
			out.print(sc.nextLine()+"<br>");      //returns the line that was skipped 
			
		}  
		sc.close();
		f.close();
	}
	%>
	</div>
</div>


<jsp:include page="footer.jsp" />
