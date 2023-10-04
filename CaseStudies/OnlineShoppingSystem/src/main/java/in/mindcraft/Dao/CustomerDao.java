package in.mindcraft.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.mindcraft.pojos.Customer;
import in.mindcraft.utils.DBUtils;

public class CustomerDao {
	private Connection cn;
	private PreparedStatement ps;
	
	public boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn = DBUtils.openConnection();
		ps = cn.prepareStatement("insert into customers values(?,?,?,?,?)");
		ps.setInt(1, customer.getCustomer_id() );
		ps.setString(2, customer.getCustomer_email());
		ps.setString(3, customer.getCustomer_name());
		ps.setString(4, customer.getCustomer_pass());
		ps.setDouble(5, customer.getCustomer_balance());
		if(ps.executeUpdate()>0) {
			DBUtils.closeConnection();
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DBUtils.openConnection();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM customers");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("customer_id");
                String email = rs.getString("customer_email");
                String name = rs.getString("customer_name");
                String pass = rs.getString("customer_pass");
                double bal = rs.getDouble("customer_balance");

                Customer customer = new Customer(id, email, name, pass, bal);
                customers.add(customer);
            }

            rs.close();
            pst.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }
	

	
	
}
