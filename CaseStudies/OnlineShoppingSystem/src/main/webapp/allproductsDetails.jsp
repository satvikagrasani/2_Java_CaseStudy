<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h2>Product Details</h2>
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
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
