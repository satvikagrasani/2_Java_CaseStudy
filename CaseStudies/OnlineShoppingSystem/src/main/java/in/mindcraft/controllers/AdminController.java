package in.mindcraft.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import in.mindcraft.Dao.CustomerDao;
import in.mindcraft.Dao.ProductDao;
import in.mindcraft.pojos.Customer;
import in.mindcraft.pojos.Product;
import in.mindcraft.utils.DBUtils;

@Controller
public class AdminController {
	
	private static Connection cn;
	private PreparedStatement pst1;
	
	private CustomerDao customerDoa = new CustomerDao();
	private ProductDao productDao = new ProductDao();
	
	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public ModelAndView checkAdmin(HttpServletRequest request,HttpServletResponse response, Model Model) throws SQLException, ClassNotFoundException {
		String email  = request.getParameter("email");
		String pass  = request.getParameter("pass");
		
		String adminemail = "admin@gmail.com";
		String adminpass = "admin123";
		
		ModelAndView mv = new ModelAndView();
		
		if (adminemail.equals(email) && adminpass.equals(pass)) {
			Model.addAttribute("status","success");
            Model.addAttribute("successMessage", "Admin login successful"); // Set the success message
            mv.setViewName("admindashboard.jsp"); // Redirect to avoid resubmission
        } else {
        	Model.addAttribute("status","danger");
            Model.addAttribute("successMessage", "Invalid credentials! Enter valid username and password");
            mv.setViewName("adminlogin.jsp");
        }
        return mv;
	}
	
//	Submit customer data
	@RequestMapping(value = "/customerData", method = RequestMethod.POST)
	public ModelAndView addCustomer(HttpServletRequest request,HttpServletResponse response, Model Model) throws SQLException, ClassNotFoundException{
		int id = Integer.parseInt(request.getParameter("cusid"));
		String email = request.getParameter("cusemail");
		String name = request.getParameter("cusname");
		String pass = request.getParameter("cuspass");
		double bal = Double.parseDouble(request.getParameter("cusbal"));
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Customer customer = new Customer(id,email,name,pass,bal);
		
		ModelAndView mv = new ModelAndView();
		
		try {
			if(customerDoa.addCustomer(customer) == true) {
				Model.addAttribute("status","success");
				Model.addAttribute("successMessage", "Customer added successfully.");
				mv.setViewName("admindashboard.jsp");
			}
		}catch(Exception e) {
			Model.addAttribute("status","danger");
			Model.addAttribute("successMessage", "Sorry customer not added");
			mv.setViewName("admindashboard.jsp");
		}
		return mv;
	}
	
	
//  Delete customer data
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public ModelAndView deleteCustomer(HttpServletRequest request,HttpServletResponse response, Model Model) throws SQLException, ClassNotFoundException{
		int id = Integer.parseInt(request.getParameter("cusid"));
		String email = request.getParameter("cusemail");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		ModelAndView mv = new ModelAndView();
		
		try {
			cn = DBUtils.openConnection();
			pst1 = cn.prepareStatement("DELETE FROM customers WHERE customer_id = ? AND customer_email = ?");
			pst1.setInt(1, id);
			pst1.setString(2, email);
			int rowsAffected = pst1.executeUpdate();
//			System.out.println("Reached");
			if(rowsAffected>0) {
//				System.out.println("done");
				Model.addAttribute("status","success");
				Model.addAttribute("successMessage", "Customer deleted successfully");
				mv.setViewName("admindashboard.jsp");
			}else {
				Model.addAttribute("status","danger");
				Model.addAttribute("successMessage", "Failed! customer not deleted");
				mv.setViewName("admindashboard.jsp");
			}
		}catch(Exception e){
			Model.addAttribute("status","danger");
			Model.addAttribute("successMessage", "Failed! customer not deleted");
			mv.setViewName("admindashboard.jsp");
		}
		
		return mv;
	}
	
	
	
	
//	Submit product data
	@RequestMapping(value = "/productData", method = RequestMethod.POST)
	public ModelAndView addProduct(HttpServletRequest request,HttpServletResponse response, Model Model) throws SQLException, ClassNotFoundException{
		int pno = Integer.parseInt(request.getParameter("pno"));
		String pname = request.getParameter("pname");
		int pprice = Integer.parseInt(request.getParameter("pprice"));
		int pquant = Integer.parseInt(request.getParameter("pquant"));
		double pdquant = Double.parseDouble(request.getParameter("pdquant"));
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Product product = new Product(pno,pname,pprice,pquant,pdquant);
		
		ModelAndView mv = new ModelAndView();
		
		try {
			if(productDao.addProduct(product) == true) {
				Model.addAttribute("status","success");
				Model.addAttribute("successMessage", "Product added successfully");
				mv.setViewName("admindashboard.jsp");
			}
		}catch(Exception e) {
			Model.addAttribute("status","danger");
			Model.addAttribute("successMessage", "Sorry! product not added");
			mv.setViewName("admindashboard.jsp");
		}
		return mv;
	}
	
	
//  Delete product data
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public ModelAndView deleteProduct(HttpServletRequest request,HttpServletResponse response, Model Model) throws SQLException, ClassNotFoundException{
		int pno = Integer.parseInt(request.getParameter("pno"));
		String pname = request.getParameter("pname");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		ModelAndView mv = new ModelAndView();
		
		try {
			cn = DBUtils.openConnection();
			pst1 = cn.prepareStatement("DELETE FROM products WHERE product_no = ? AND product_name = ?");
			pst1.setInt(1, pno);
			pst1.setString(2, pname);
			int rowsAffected = pst1.executeUpdate();
//			System.out.println("Reached");
			if(rowsAffected>0) {
//				System.out.println("done");
				Model.addAttribute("status","success");
				Model.addAttribute("successMessage", "Product deleted successfully");
				mv.setViewName("admindashboard.jsp");
			}else {
				Model.addAttribute("status","danger");
				Model.addAttribute("successMessage", "Failed! product not deleted");
				mv.setViewName("admindashboard.jsp");
			}
		}catch(Exception e){
			Model.addAttribute("status","danger");
			Model.addAttribute("successMessage", "Failed! product not deleted");
			mv.setViewName("admindashboard.jsp");
		}
		
		return mv;
	}
	
	
//	Getting all customers data and rendering to 'allcustomersDetails.jsp' page
	@RequestMapping(value = "/displayCustomers", method = RequestMethod.GET)
	public String displayAllCustomers(HttpServletRequest request, HttpServletResponse response,Model model) {
	    ModelAndView mv = new ModelAndView();
	    List<Customer> customers = customerDoa.getAllCustomers();
	    request.setAttribute("customers", customers);
	    return "allcustomersDetails.jsp";
	}
	
	
//	Getting all products data and rendering to 'allproductsDetails.jsp' page
	@RequestMapping(value = "/displayProducts", method = RequestMethod.GET)
	public String displayAllProducts(HttpServletRequest request, HttpServletResponse response,Model model) {
	    ModelAndView mv = new ModelAndView();
	    List<Product> products = productDao.getAllProducts();
	    request.setAttribute("products", products);
	    return "allproductsDetails.jsp";
	}

	

	
	
	
	
	
	
//	@RequestMapping(value = "/customerData", method = RequestMethod.POST)
//	public ModelAndView addCustomer(HttpServletRequest request,HttpServletResponse response, Model Model) throws SQLException, ClassNotFoundException{
//		int id = Integer.parseInt(request.getParameter("cusid"));
//		String email = request.getParameter("cusemail");
//		String name = request.getParameter("cusname");
//		String pass = request.getParameter("cuspass");
//		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		
//		String url = "jdbc:mysql://localhost/onlineshoppingsystem";
//		cn = DriverManager.getConnection(url,"root","mihirsql");
//		pst1 = cn.prepareStatement("insert into customers values(?,?,?,?)");
//		pst1.setInt(1, );
//		pst1.setString(2, email);
//		pst1.setString(3, name);
//		pst1.setString(4, pass);
//		
//		ModelAndView mv = new ModelAndView();
//		
//		try {
//			if(pst1.executeUpdate()>0) {
//				System.out.println("Done");
//				Model.addAttribute("status","success");
//	            Model.addAttribute("successMessage", "Customer added successfully.");
//	            mv.setViewName("admindashboard.jsp");
//			}
//		}catch(Exception e) {
//			Model.addAttribute("status","danger");
//			Model.addAttribute("successMessage", "Sorry customer not added");
//			mv.setViewName("admindashboard.jsp");
//		}
//		return mv;
//		
//	}
	
	
	

}
