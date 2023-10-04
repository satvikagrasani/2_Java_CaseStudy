package in.mindcraft.pojos;

public class ProductInCart {
	private int customer_id;
	private String product_name;
	private double product_price;
	private int product_quantity;
	
	public ProductInCart() {
		
	}

	public ProductInCart(int customer_id, String product_name, double product_price, int product_quantity) {
		this.customer_id = customer_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_quantity = product_quantity;

	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	
	
	
	
}
