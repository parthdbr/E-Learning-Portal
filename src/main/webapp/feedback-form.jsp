<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
 <section class="ftco-section contact-section ftco-no-pt">
  <div class="container">
  	<div class="row justify-content-center pb-4">
		<div class="col-md-12 heading-section text-center ftco-animate">
			<h2 class="mb-4">Feedback</h2>
		</div>
	</div>
    <div class="row block-9">
      <div class="col-md order-md-last d-flex">
        <form action="feedback" class="bg-light p-5 contact-form" method="POST">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Your Name" name="name">
          </div>
          <div class="form-group">
            <textarea name="msg" id="" cols="30" rows="7" class="form-control" placeholder="Feedback"></textarea>
          </div>
          
          <%
          if(request.getParameter("r")!=null){
        	  if(request.getParameter("r").equals("false")){
          %>
        	  <div class="form-group" style="color:red;">
				<script>
					document.write("Login to Submit the feedback form!");
				</script>
			  </div>
          <%  
        	  }else{
        		  if(request.getParameter("r").equals("true")){
        			  %>
        			  <div class="form-group" style="color:green;">
						<script>
							document.write("Feedback form submitted!");
						</script>
					  </div>        			  
        			  <%
        		  }
        	  }
          }	  
          %>	  
        	  
       	  <div class="form-group">
            <input type="submit" value="Send Feedback" class="btn btn-primary py-3 px-5">
          </div>
        </form>
        
      </div>

      
   </div>
 </div>
</section>