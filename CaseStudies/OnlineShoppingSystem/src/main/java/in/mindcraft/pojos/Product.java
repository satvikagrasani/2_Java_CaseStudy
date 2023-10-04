package in.mindcraft.pojos;

public class Product {
	private int product_no;
	private String product_name;
	private double product_price;
	private int quantity;
	private double discount;
	
	public Product() {
		
	}

	public Product(int product_no, String product_name, double product_price, int quantity, double discount) {
		this.product_no = product_no;
		this.product_name = product_name;
		this.product_price = product_price;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	public int getProduct_no() {
		return product_no;
	}
	
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public double getProduct_price() {
		return product_price;
	}
	
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}
