<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  </head>
  <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .custom-container {
            height: 280px;
            width: 482px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
        }
        
        .mybtn{
           padding-left: 140px;
		    padding-right: 140px;
		    margin-left: 49px;
        }
    </style>
  <body>
  
    
    <form class="custom-container" action="${pageContext.request.contextPath}/customerlogin" method="post">
    
       <h2 style="text-align: center; margin-bottom: 20px;">Login To Customer</h2>
       
        <div class="form-floating mb-3">
            <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com">
            <label for="email">Email address</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="pass" name="pass" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div>
        <button type="submit" name="submit" value="true" class=" mybtn btn btn-primary btn-block mt-3">Submit</button>
        <br/>
        <br/>
        <br/>
        
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
        
    </form>
    
    
    <script>
    // Add this JavaScript code to automatically hide the alert after 4 seconds
    setTimeout(function() {
        var errorAlert = document.getElementById('errorAlert');
        if (errorAlert) {
            errorAlert.style.display = 'none';
        }
    }, 4000); // 4 seconds = 4000 milliseconds
	</script>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
  </body>
</html>



