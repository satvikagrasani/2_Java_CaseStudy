package in.mindcraft.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.mindcraft.pojos.Customer;
import in.mindcraft.pojos.Product;
import in.mindcraft.pojos.ProductInCart;
import in.mindcraft.utils.DBUtils;

public class ProductDao {
	private Connection cn;
	private PreparedStatement ps;
	
	public boolean addProduct(Product product) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn = DBUtils.openConnection();
		ps = cn.prepareStatement("insert into products values(?,?,?,?,?)");
		ps.setInt(1, product.getProduct_no());
		ps.setString(2, product.getProduct_name());
		ps.setDouble(3, product.getProduct_price());
		ps.setInt(4, product.getQuantity());
		ps.setDouble(5, product.getDiscount());
		if(ps.executeUpdate()>0) {
			DBUtils.closeConnection();
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public boolean addProductInCart(ProductInCart productcart,int customer_id) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn = DBUtils.openConnection();
		ps = cn.prepareStatement("insert into allcustomer_cartdetails values(?,?,?,?)");
		ps.setInt(1, customer_id);
		ps.setString(2, productcart.getProduct_name());
		ps.setDouble(3, productcart.getProduct_price());
		ps.setInt(4, productcart.getProduct_quantity());
		if(ps.executeUpdate()>0) {
			DBUtils.closeConnection();
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DBUtils.openConnection();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM products");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int product_no = rs.getInt("product_no");
                String product_name = rs.getString("product_name");
                double product_price = rs.getDouble("product_price");
                int product_quantity = rs.getInt("product_quantity");
                double product_discount = rs.getDouble("product_discount");

                Product product = new Product(product_no,product_name,product_price,product_quantity,product_discount);
                products.add(product);
            }

            rs.close();
            pst.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
	
	
	
//	Getting all products in cart
	public List<ProductInCart> allproductsInCart(int customer_id) {
        List<ProductInCart> items = new ArrayList<>();

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DBUtils.openConnection();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM allcustomer_cartdetails WHERE customer_id = ?");
            pst.setInt(1, customer_id );
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int customer_id1 = rs.getInt("customer_id");
                String product_name = rs.getString("product_name");
                double product_price = rs.getDouble("product_price");
                int product_quantity = rs.getInt("product_quantity");

                ProductInCart item = new ProductInCart(customer_id1,product_name,product_price,product_quantity);
                items.add(item);
            }

            rs.close();
            pst.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
	
	
	
	
	
	
	
}
