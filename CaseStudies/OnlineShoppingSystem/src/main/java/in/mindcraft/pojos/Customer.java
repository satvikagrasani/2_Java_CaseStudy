package in.mindcraft.pojos;

public class Customer {
	private int customer_id;
	private String customer_email;
	private String customer_name;
    private String customer_pass;
    private double customer_balance;
	
	public double getCustomer_balance() {
		return customer_balance;
	}

	public void setCustomer_balance(double customer_balance) {
		this.customer_balance = customer_balance;
	}

	public Customer() {
		
	}
	
	public Customer(int customer_id, String customer_email, String customer_name, String customer_pass, double customer_balance) {
		this.customer_id = customer_id;
		this.customer_email = customer_email;
		this.customer_name = customer_name;
		this.customer_pass = customer_pass;
		this.customer_balance = customer_balance;
	}
	
	public String getCustomer_pass() {
		return customer_pass;
	}

	public void setCustomer_pass(String customer_pass) {
		this.customer_pass = customer_pass;
	}

	public int getCustomer_id() {
		return customer_id;
	}
	

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	
	public String getCustomer_name() {
		return customer_name;
	}
	
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	
}
