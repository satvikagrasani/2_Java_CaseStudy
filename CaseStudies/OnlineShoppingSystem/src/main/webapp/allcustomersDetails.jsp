<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h2>Customer Details</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Customer ID</th>
            <th>Email</th>
            <th>Name</th>
            <th>Balance</th>
        </tr>
        
        </thead>
        
        
        <tbody>
        
        <% List<in.mindcraft.pojos.Customer> customers = (List<in.mindcraft.pojos.Customer>) request.getAttribute("customers");
        for(int i=0;i<customers.size();i++){
        	in.mindcraft.pojos.Customer customer = customers.get(i);
        
        %>
        
            <tr>
                <td><%=customer.getCustomer_id()%></td>
	            <td><%=customer.getCustomer_email()%></td>
	            <td><%=customer.getCustomer_name()%></td>
	            <td><%=customer.getCustomer_balance()%></td>
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
