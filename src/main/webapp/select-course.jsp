<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<%@page import="java.sql.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<section class="ftco-section img ftco-select-destination" style="background-image: url(images/1.jpg); background-size:50%; background-position:left; background-repeat:repeat-x;">
			<div class="container">
				<div class="row justify-content-center pb-4">
					<div class="col-md-12 heading-section text-center ftco-animate">
						<!-- <span class="subheading">Book a room</span> -->
						<h2 class="mb-4">Select Your course</h2>
					</div>
				</div>
			</div>
			<div class="container container-2">
				<div class="row">
					<div class="col-md-12">
						<div class="carousel-destination owl-carousel ftco-animate">					
						
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
							connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningportal", "root", "root");
							statement=connection.createStatement();
							String sql = "SELECT * FROM courses WHERE popularity='popular'";
							
							resultSet = statement.executeQuery(sql);
							while(resultSet.next()){
								String filename = resultSet.getString("photo"); 
								String popularity = resultSet.getString("popularity");
							%>						
								<div class="item">
									<div class="project-destination">
										<a href="#" class="img" style="background-image: url(images/<%= filename %>);">
											<div class="text">
												<h3><%= resultSet.getString("cname") %></h3>
												<!-- <span>8 Tours</span> -->
											</div>
										</a>
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