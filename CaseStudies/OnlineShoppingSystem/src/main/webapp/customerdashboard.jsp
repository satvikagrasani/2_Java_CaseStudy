<%@page import="in.mindcraft.pojos.ProductInCart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  </head>
  <body>
  
   <div class="alert alert-${status}" role="alert" id="errorAlert">
	    ${successMessage}
	</div>
    
            <script>
                setTimeout(function() {
                    var errorAlert = document.getElementById('errorAlert');
                    if (errorAlert) {
                        errorAlert.style.display = 'none';
                    }
                }, 4000); // Hide after 4 seconds
            </script>
  
    
    <div class="container ms-2 my-3">
    	<h2>Hello, <%= request.getAttribute("user") %></h2>
    	<h3>Your wallet balance: <%= request.getAttribute("walletBalance") %></h3>
    </div>
    
    <div class="cotainer mt-3 ms-3 me-3 my-3">
    	<h3 style="text-align: center">Product Details</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Product no</th>
            <th>Product Name</th>
            <th>Product price</th>
            <th>Product quantity</th>
            <th>Product discount</th>
        </tr>
        </thead>
        <tbody>
		<% List<in.mindcraft.pojos.Product> products = (List<in.mindcraft.pojos.Product>) request.getAttribute("products");
        for(int i=0;i<products.size();i++){
        	in.mindcraft.pojos.Product product = products.get(i);
        
        %>
        
            <tr>
                <td><%=product.getProduct_no()%></td>
	            <td><%=product.getProduct_name()%></td>
	            <td><%=product.getProduct_price()%></td>
	            <td><%=product.getQuantity()%></td>
	            <td><%=product.getDiscount()%></td>
            </tr>
            <% 
            }
            %>
        </tbody>
    </table>
    
    <div class="mb-3">
       <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal1">Add to cart</button>
       <button  type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal2">Display cart</button>
       <button  type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal3">Remove from cart</button>
       
	
		
		<!-- Modal 1-->
		<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModal1" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModal1">Add to cart</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        
		        
		                <div class="container">
					        	<form action="${pageContext.request.contextPath}/addtocart" method="post">
								  
								  <div class="mb-3">
								    <label for="cusid" class="form-label">Customer id</label>
								    <input type="text" name="cusid" value=<%= request.getAttribute("cusid") %> class="form-control" id="cusid">
								  </div>
								  
								  <div class="mb-3">
								    <label for="pname" class="form-label">Product name</label>
								    <input type="text" name="pname" class="form-control" id="pname">
								  </div>
								 
								  <div class="mb-3">
								    <label for="pquant" class="form-label">Product quantity</label>
								    <input type="number" name="pquant" class="form-control" id="pquant">
								  </div>
								  
								  <button type="submit" class="btn btn-primary">Submit</button>
								</form>
					        </div>
		        
		        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary">Save changes</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		<!-- Modal 2-->
		<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModal2" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModal2">Your Cart</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		      
		      
		        <!-- Add the table for the cart with the "Total Amount" column -->
			<table class="table table-bordered">
			    <thead>
			    <tr>
			        <th>Product Name</th>
			        <th>Product Price</th>
			        <th>Product Quantity</th>
			        <th>Total Amount</th> <!-- New column -->
			    </tr>
			    </thead>
			    <tbody>
			    <%
			        List<ProductInCart> items = (List<in.mindcraft.pojos.ProductInCart>) request.getAttribute("items");
			        double cartTotal = 0; // Initialize cart total
			        for (int i = 0; i < items.size(); i++) {
			            in.mindcraft.pojos.ProductInCart item = items.get(i);
			            double totalAmount = item.getProduct_price() * item.getProduct_quantity();
			            cartTotal += totalAmount; // Update cart total
			
			    %>
			    <tr>
			        <td><%= item.getProduct_name() %></td>
			        <td><%= item.getProduct_price() %></td>
			        <td><%= item.getProduct_quantity() %></td>
			        <td><%= totalAmount %></td> <!-- Display total amount for each product -->
			    </tr>
			    <%
			        }
			    %>
			    </tbody>
			</table>
                
             <!-- Display the total cart amount at the end of the table -->
			<div class="container">
			    <h4>Total Cart Amount: <%= cartTotal %></h4>
			</div>
    
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" onclick="proceedToCheckout()" class="btn btn-primary">Proceed to checkout</button>
		      </div>
		      
		      <script>
			    function proceedToCheckout() {
			        // Send an AJAX request to the server to check if the balance is sufficient and deduct the amount
			        var customer_id = <%= request.getAttribute("cusid") %>
			        var cartTotal = <%= cartTotal %>; // Get the cart total from JSP
			        
			        // You can use JavaScript's fetch() or an AJAX library like jQuery for the AJAX request
			        fetch("${pageContext.request.contextPath}/finalcheckout?cartTotal=" + cartTotal + "&customer_id=" + customer_id, {
					    method: 'POST'
					})
			        .then(response => response.json())
			        .then(data => {
			            if (data.success) {
			                // Redirect to a success page or do something else
			                window.location.href = "${pageContext.request.contextPath}/finalcheckout";
			            } else {
			                // Handle insufficient balance error or other errors
			                alert("Error: " + data.message);
			            }
			        })
			        .catch(error => {
			            console.error('Error:', error);
			        });
			    }
	</script>
		      
		    </div>
		  </div>
		</div>
		
		
		<!-- Modal 3-->
		<div class="modal fade" id="exampleModal3" tabindex="-1" aria-labelledby="exampleModal3" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModal3">Remove items</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        ...
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary">Save changes</button>
		      </div>
		    </div>
		  </div>
		</div>
		 
    </div>
    
    
          
    
    
    </div>
    
    
    
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
  </body>
</html>

