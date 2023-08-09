<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.sql.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<section class="ftco-section testimony-section bg-bottom" style="background-image: url(images/bg_1.jpg);">
 <div class="overlay"></div>
 <div class="container">
  <div class="row justify-content-center pb-4">
    <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
     <span class="subheading">Testimonial</span>
     <h2 class="mb-4">Feedback</h2>
   </div>
 </div>
 
 
 
 <div class="row ftco-animate">
  <div class="col-md-12">
    <div class="carousel-testimony owl-carousel">
 
 
<% 
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "project";
String userid = "root";
String password = "1234";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;

try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql ="select * from feedback";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
 
 
      <div class="item">
        <div class="testimony-wrap py-4">
          <div class="text">
           <!--  <p class="star">
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
          </p>  -->
          <p class="mb-4"><%= resultSet.getString("msg") %></p>
          <div class="d-flex align-items-center">
           <!--  <div class="user-img" style="background-image: url(images/person_1.jpg)"></div>  -->
           <div class="pl-3">
            <p class="name"><%= resultSet.getString("name") %></p>
            <!--  <span class="position">Marketing Manager</span>  -->
          </div>
        </div>
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
</div>
</div>
</section>