<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">
				<img src="./images/logo.png" alt="" width="150" alt="" class="d-inline-block align-middle mr-2">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="index.jsp" class="nav-link">Home</a></li>
					<li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
					<li class="nav-item"><a href="courses.jsp" class="nav-link">Courses</a></li>					
					<li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
					
					
				   <%
				  
				      if (session != null) {
				    	  
				         if (session.getAttribute("user") != null) {
				            String name = (String) session.getAttribute("user");
				            String mail = (String) session.getAttribute("mail");

				            
				            
				   %>
				   			
				   			<li class="nav-item"><a href="logout.jsp" class="nav-link">Logout</a></li>
				   
				   <%
				         } else {
				   %>
				   
				   		<li class="nav-item"><a href="registration/login.jsp" class="nav-link">Login</a></li>
						<li class="nav-item"><a href="registration/signup.jsp" class="nav-link">Sign-up</a></li>
				   
				   <%
				         }
				      }
				   
				   
				   %>
				</ul>
			</div>
		</div>
	</nav>