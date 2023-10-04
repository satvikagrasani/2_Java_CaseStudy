package in.mindcraft.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.mindcraft.Dao.ProductDao;
import in.mindcraft.pojos.Customer;
import in.mindcraft.pojos.Product;
import in.mindcraft.pojos.ProductInCart;
import in.mindcraft.utils.DBUtils;

@Controller
public class CustomerController {
	
	private static Connection cn;
	private PreparedStatement pst;
	private PreparedStatement pst1;
	
	private ProductDao productDao = new ProductDao();

	@RequestMapping(path = "/customerlogin", method = RequestMethod.POST)
	public ModelAndView checkCustomer(HttpServletRequest request,HttpServletResponse response, Model model) throws SQLException, ClassNotFoundException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		ModelAndView mv = new ModelAndView();
		
		try {
			cn = DBUtils.openConnection();
			pst = cn.prepareStatement("SELECT customer_id,customer_balance,customer_name FROM customers WHERE customer_email=? AND customer_pass=?");
			pst.setString(1, email);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				int customer_id = rs.getInt("customer_id");
				double walletBalance = rs.getDouble("customer_balance");
				String customername = rs.getString("customer_name");
				model.addAttribute("status","success");
	            model.addAttribute("successMessage", "Customer login successful"); // Set the success message
	            
	            List<ProductInCart> items = productDao.allproductsInCart(customer_id);
	    	    request.setAttribute("items", items);
	            
	    	    List<Product> products = productDao.getAllProducts();
	    	    request.setAttribute("products", products);
	    	    request.setAttribute("walletBalance", walletBalance);
	    	    request.setAttribute("user", customername);
	    	    request.setAttribute("cusid", customer_id);
	            mv.setViewName("customerdashboard.jsp"); 
			}else {
				model.addAttribute("status","danger");
	            model.addAttribute("successMessage", "Invalid credentials! Enter valid username and password");
	            mv.setViewName("customerlogin.jsp");
			}
			
		}catch(Exception e) {
			model.addAttribute("status","danger");
            model.addAttribute("successMessage", "Invalid credentials! Enter valid username and password");
            mv.setViewName("customerlogin.jsp");
		}
		return mv;
		
	}
	
	
	
	@RequestMapping(path = "/addtocart", method = RequestMethod.POST)
	public ModelAndView addToCart(HttpServletRequest request, HttpServletResponse response, Model model) throws SQLException, ClassNotFoundException {
	    int customer_id = Integer.parseInt(request.getParameter("cusid"));
	    String product_name = request.getParameter("pname");
	    int product_quantity = Integer.parseInt(request.getParameter("pquant"));

	    Class.forName("com.mysql.cj.jdbc.Driver");
	    
//	    Gain username and userbalance
	    pst1 = cn.prepareStatement("SELECT customer_name,customer_balance FROM customers WHERE customer_id=?");
	    pst1.setInt(1, customer_id);
	    ResultSet rs3 = pst1.executeQuery();
	    if(rs3.next()) {
	    	double walletBalance = rs3.getDouble("customer_balance");
            String customername = rs3.getString("customer_name");
            
            request.setAttribute("walletBalance", walletBalance);
    	    request.setAttribute("user", customername);
	    }

	    ModelAndView mv = new ModelAndView();
	    

	    try {
	        cn = DBUtils.openConnection();
	        pst = cn.prepareStatement("SELECT product_name,product_price,product_quantity,product_discount FROM products WHERE product_name=?");
	        pst1 = cn.prepareStatement("SELECT customer_name,customer_balance FROM customers WHERE customer_id=?");
	        pst.setString(1, product_name);
	        pst1.setInt(1, customer_id);
	        ResultSet rs = pst.executeQuery();
	        ResultSet rs1 = pst1.executeQuery();
	        if (rs.next() && rs1.next()) {
	            String product_name1 = rs.getString("product_name");
	            double product_price = rs.getDouble("product_price");
	            int product_quantity1 = rs.getInt("product_quantity");
//	            double product_discount = rs.getDouble("product_discount");
	            double walletBalance = rs1.getDouble("customer_balance");
	            String customername = rs1.getString("customer_name");
	            

	            // Adding items to the cart
	            ProductInCart productcart = new ProductInCart(customer_id, product_name1, product_price, product_quantity);

	            try {
	                if(productDao.addProductInCart(productcart, customer_id) == true) {
	                	model.addAttribute("status", "success");
		                model.addAttribute("successMessage", "Product added to cart");
		                request.setAttribute("walletBalance", walletBalance);
		        	    request.setAttribute("user", customername);
	                }
	              
	            } catch (Exception e) {
	                model.addAttribute("status", "danger");
	                model.addAttribute("successMessage", "Can't add to cart");
	            }
	        } else {
	            model.addAttribute("status", "danger");
	            model.addAttribute("successMessage", "Can't add to cart");
	        }

	    } catch (Exception e) {
	    	System.out.println("Reached");
	        model.addAttribute("status", "danger");
	        model.addAttribute("successMessage", "Can't add to cart");
	    }

	    // Set the "items" attribute in both success and error cases
	    List<ProductInCart> items = productDao.allproductsInCart(customer_id);
	    request.setAttribute("items", items);

	    // Set the "products" attribute in both success and error cases
	    List<Product> products = productDao.getAllProducts();
	    request.setAttribute("products", products);
	    request.setAttribute("cusid", customer_id);
	    
	   
	    
        // set customer name and balance regardless of condition
//	    request.setAttribute("walletBalance", walletBalance);
//	    request.setAttribute("user", customername);

	    mv.setViewName("customerdashboard.jsp");
	    return mv;
	}
	
	
	
	
	
	
	@RequestMapping("/finalcheckout")
	public ModelAndView finalCheckout(HttpServletRequest request,HttpServletResponse response, Model model, @RequestParam double cartTotal, @RequestParam int customer_id) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
//		ModelAndView mv = new ModelAndView();
		ModelAndView mv = new ModelAndView("redirect:/customerdashboard.jsp");
		
//		Fetching the customer balance
		cn = DBUtils.openConnection();
		
		pst = cn.prepareStatement("SELECT customer_balance FROM customers WHERE customer_id=?");
		pst.setInt(1, customer_id);
		ResultSet rs = pst.executeQuery();
		
		
//	    Gain username and userbalance
	    pst1 = cn.prepareStatement("SELECT customer_name,customer_balance FROM customers WHERE customer_id=?");
	    pst1.setInt(1, customer_id);
	    ResultSet rs3 = pst1.executeQuery();
	    if(rs3.next()) {
	    	double walletBalance = rs3.getDouble("customer_balance");
            String customername = rs3.getString("customer_name");
            
            request.setAttribute("walletBalance", walletBalance);
    	    request.setAttribute("user", customername);
	    }

		
		
		if(rs.next()) {
			double customer_balance = rs.getDouble("customer_balance");
			if(customer_balance>=cartTotal) {
				customer_balance = customer_balance - cartTotal;
//				UPDATE customers SET customer_balance = ? WHERE customer_id = ?
				pst1 = cn.prepareStatement("UPDATE customers SET customer_balance = ? WHERE customer_id = ?");
				pst1.setDouble(1, customer_balance);
				pst1.setInt(2, customer_id);
				
				int rowsUpdated = pst1.executeUpdate();
				
				if(rowsUpdated>0) {
					System.out.println("reached here congrats");
					model.addAttribute("status", "success");
		            model.addAttribute("successMessage", "Order placed successfully");
//		            mv.setViewName("finalcheckout.jsp");
				}
			}else {
				model.addAttribute("status", "danger");
	            model.addAttribute("successMessage", "Order failed to placed! You dont have suffiicient balance");
//	            mv.setViewName("customerdashboard.jsp");
			}
		}
		
		// Set the "items" attribute in both success and error cases
	    List<ProductInCart> items = productDao.allproductsInCart(customer_id);
	    request.setAttribute("items", items);

	    // Set the "products" attribute in both success and error cases
	    List<Product> products = productDao.getAllProducts();
	    request.setAttribute("products", products);
	    request.setAttribute("cusid", customer_id);
	    
	 // Add any model attributes if needed
	    return mv;
		
//	    mv.setViewName("customerdashboard.jsp");
	    
//		return "customerdashboard.jsp";
		
	}
	

}
