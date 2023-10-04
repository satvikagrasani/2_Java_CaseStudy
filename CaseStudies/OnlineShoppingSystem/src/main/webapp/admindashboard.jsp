<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    
    <style>
    .line {
        border-top: 1px solid #ccc; /* Horizontal line color */
        width: 100%;
        max-width: 50px; /* Adjust the width of the line as needed */
    }
	</style>
	
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

    
  
    
    
    <div class="container mt-5">
        <h2 style="text-align: center;">Welcome, Admin!</h2>
        <div class="row mt-4 justify-content-center">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">Manage Products</div>
                    <div class="card-body">
                    
                        <div class="d-grid gap-2 col-7 mx-auto">
						  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModalp1"> Add Product </button>
						  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModalp2"> Remove Product </button>
						  <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/displayProducts?method=get">Display Products</a>
					   </div>
					  
                    </div>
                    
                    
                    <div class="modal fade" id="exampleModalp1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					  
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Add Product</h1>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      
					      <div class="modal-body">

					        <div class="container">
					        	<form action="${pageContext.request.contextPath}/productData" method="post">
								  <div class="mb-3">
								    <label for="pno" class="form-label">Product no</label>
								    <input type="number" name="pno" class="form-control" id="pno" aria-describedby="emailHelp">
								  </div>
								  <div class="mb-3">
								    <label for="pname" class="form-label">Product name</label>
								    <input type="text" name="pname" class="form-control" id="pname">
								  </div>
								  <div class="mb-3">
								    <label for="pprice" class="form-label">Product price</label>
								    <input type="number" name="pprice" class="form-control" id="pprice">
								  </div>
								  <div class="mb-3">
								    <label for="pquant" class="form-label">Product quantity</label>
								    <input type="number" name="pquant" class="form-control" id="pquant">
								  </div>
								  <div class="mb-3">
								    <label for="pdquant" class="form-label">Product discount</label>
								    <input type="number" name="pdquant" class="form-control" id="pdquant">
								  </div>
								  <button type="submit" class="btn btn-primary">Submit</button>
								</form>
					        </div>
					        
					      </div>
					     
					    </div>
					  </div>
					</div>
					
					
					
					<div class="modal fade" id="exampleModalp2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Remove Product</h1>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        <div class="container">
					        	<form action="${pageContext.request.contextPath}/deleteProduct" method="post">
								  <div class="mb-3">
								    <label for="pno" class="form-label">Product no</label>
								    <input type="number" name="pno" class="form-control" id="pno" aria-describedby="emailHelp">
								  </div>
								  <div class="mb-3">
								    <label for="pname" class="form-label">Product name</label>
								    <input type="text" name="pname" class="form-control" id="pname">
								  </div>
							
								  <button type="submit" class="btn btn-danger">Remove</button>
								  
								</form>
					        </div>
					      </div>
					      
					    </div>
					  </div>
					</div>
					
					
					
                    
                </div>
            </div>
            
         
            &nbsp;
            
            <div class="col-md-4 ">
                <div class="card me-6">
                    <div class="card-header">Manage Customers</div>
                    <div class="card-body">
                    
                    <div class="d-grid gap-2 col-8 mx-auto">
						  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> Add Customer </button>
						  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal1"> Remove Customer </button>
						  <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/displayCustomers?method=get">Display Customers</a>
						  
					</div>
					
					</div>
					
					
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					  
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Add Customer</h1>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      
					      <div class="modal-body">

					        <div class="container">
					        	<form action="${pageContext.request.contextPath}/customerData" method="post">
								  <div class="mb-3">
								    <label for="exampleInputEmail1" class="form-label">Enter id</label>
								    <input type="number" name="cusid" class="form-control" id="cusid" aria-describedby="emailHelp">
								  </div>
								  <div class="mb-3">
								    <label for="cusemail" class="form-label">Enter email</label>
								    <input type="email" name="cusemail" class="form-control" id="cusemail">
								  </div>
								  <div class="mb-3">
								    <label for="cusname" class="form-label">Enter name</label>
								    <input type="text" name="cusname" class="form-control" id="cusname">
								  </div>
								  <div class="mb-3">
								    <label for="cuspass" class="form-label">Enter password</label>
								    <input type="text" name="cuspass" class="form-control" id="cuspass">
								  </div>
								  <div class="mb-3">
								    <label for="cusbal" class="form-label">Initial balance</label>
								    <input type="text" name="cusbal" class="form-control" id="cusbal">
								  </div>
								  <button type="submit" class="btn btn-primary">Submit</button>
								</form>
					        </div>
					      </div>
					     
					    </div>
					  </div>
					</div>
					
					
					
					
					<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Remove Customer</h1>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        <div class="container">
					        	<form action="${pageContext.request.contextPath}/deleteCustomer" method="post">
								  <div class="mb-3">
								    <label for="cusid" class="form-label">Enter id</label>
								    <input type="number" name="cusid" class="form-control" id="cusid" aria-describedby="emailHelp">
								  </div>
								  <div class="mb-3">
								    <label for="cusemail" class="form-label">Enter email</label>
								    <input type="email" name="cusemail" class="form-control" id="cusemail">
								  </div>
								
			
								  <button type="submit" class="btn btn-danger">Remove</button>
								</form>
					        </div>
					      </div>
					      
					    </div>
					  </div>
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
