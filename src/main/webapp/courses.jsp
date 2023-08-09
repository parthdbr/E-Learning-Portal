  
<%@page import="java.sql.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<jsp:include page="header.jsp" />

<jsp:include page="nav.jsp" />

<section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('images/bg_1.jpg');">
  <div class="overlay"></div>
  <div class="container">
    <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-center">
      <div class="col-md-9 ftco-animate pb-5 text-center">
         <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="fa fa-chevron-right"></i></a></span> <span>Courses <i class="fa fa-chevron-right"></i></span></p>
         <h1 class="mb-0 bread">Courses</h1>
     </div>
 </div>
</div>
</section>

<section class="ftco-section">
   <div class="container">
    <div class="row align-items-center">
    
    	<%
							try {
								Class.forName("com.mysql.jdbc.Driver");
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
							
							Connection connection = null;
							Statement statement = null;
							ResultSet resultSet = null;
							
							try{ 
							connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningportal","root","root");
							statement=connection.createStatement();
							String sql = "SELECT * FROM courses";
							
							resultSet = statement.executeQuery(sql);
							while(resultSet.next()){
								String filename = resultSet.getString("photo"); 
								String popularity = resultSet.getString("popularity");
							%>			
    	
       <div class="col-md-4 ftco-animate">
          <div class="project-wrap">
          	
          
             <a href="content.jsp?content=<%= resultSet.getString("cname") %>" class="img" style="background-image: url(images/<%= filename %>);"></a>
            <div class="text p-4">
                <h3><a href="content.jsp"><%= resultSet.getString("cname") %></a></h3>
                <p class="location"><span class="fa fa-map-marker"></span> &nbsp;<%= popularity %> </p>
             </div>
          
       </div>
   </div>
   
    <%
							}
							connection.close();
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
   
   

</div>

</div>
</section>

<jsp:include page="footer.jsp" />