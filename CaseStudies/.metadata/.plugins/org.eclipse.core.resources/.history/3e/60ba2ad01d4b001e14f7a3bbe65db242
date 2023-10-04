import java.util.Scanner;
import java.sql.*;

class CustomerLogin {
    private Scanner scanner = new Scanner(System.in);
    
    public void deposit(int accno) throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
    	
    	System.out.print("\n Enter amount to deposit: ");
    	int amount = sc.nextInt();
    	
    	String searchQuery = "SELECT * FROM customer WHERE account_number = ?";
    	
    	PreparedStatement stmt = con.prepareStatement(searchQuery);
        stmt.setInt(1, accno);
    	
        ResultSet result = stmt.executeQuery();
        int newAccbalance = 0;
        
        if(result.next()) {
        	System.out.println("\n Account balance earlier: ");
        	System.out.println("\n "+result.getInt(4));
        }
        
        newAccbalance = result.getInt(4) + amount;
        
        String updateQuery = "update customer set account_balance=? where account_number=?";
    	PreparedStatement stmt1 = con.prepareStatement(updateQuery);
    	stmt1.setInt(1, newAccbalance);
    	stmt1.setInt(2, accno);
    	
    	int rowsAffected = stmt1.executeUpdate();
    	
    	if(rowsAffected>0) {
    		System.out.println("\n Amount deposited successfully");
    		System.out.println("\n Current Account balance: ");
        	System.out.println("\n "+newAccbalance);
    		
    	}else {
    		System.out.println("\n Failed to deposit");
    	}
    	
    	// Record deposit transaction
        String depositQuery = "INSERT INTO transaction_history (account_number, transaction_type, amount) VALUES (?, 'Deposit', ?)";
        PreparedStatement depositStmt = con.prepareStatement(depositQuery);
        depositStmt.setInt(1, accno);
        depositStmt.setInt(2, amount);
        depositStmt.executeUpdate();
    }
    
    
    
    
    public void withdraw(int accno) throws SQLException,Exception {
    	Scanner sc = new Scanner(System.in);
    	
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
    	
    	System.out.print("\n Enter amount to withdraw: ");
    	int amount = sc.nextInt();
    	
    	String searchQuery = "SELECT * FROM customer WHERE account_number = ?";
    	
    	PreparedStatement stmt = con.prepareStatement(searchQuery);
        stmt.setInt(1, accno);
    	
        ResultSet result = stmt.executeQuery();
        
        if(result.next()) {
        	System.out.println("\n Account balance earlier: ");
        	System.out.println("\n "+result.getInt(4));
        }
        int newAccbalance;
        if(result.getInt(4)>amount) {
        	newAccbalance = result.getInt(4) - amount;
        	
        	String updateQuery = "update customer set account_balance=? where account_number=?";
        	PreparedStatement stmt1 = con.prepareStatement(updateQuery);
        	stmt1.setInt(1, newAccbalance);
        	stmt1.setInt(2, accno);
        	
        	int rowsAffected = stmt1.executeUpdate();
        	
        	if(rowsAffected>0) {
        		System.out.println("\n Amount withdraw successfully");
        	}else {
        		System.out.println("\n failed to withdraw");
        	}

        }else {
        	throw new Exception("Insufficient balance Cant withdraw");
        }
        
     // Record withdrawal transaction
        String withdrawQuery = "INSERT INTO transaction_history (account_number, transaction_type, amount) VALUES (?, 'Withdrawal', ?)";
        PreparedStatement withdrawStmt = con.prepareStatement(withdrawQuery);
        withdrawStmt.setInt(1, accno);
        withdrawStmt.setInt(2, amount);
        withdrawStmt.executeUpdate();
    }
    
    
    
    
    public void transferAmount(int accno) throws SQLException,Exception {
        Scanner sc = new Scanner(System.in);


        System.out.print("\n Enter receiver's account no: ");
        int destinationAccNo = sc.nextInt();
        sc.nextLine();

        System.out.print("\n Enter amount to transfer: ");
        int amount = sc.nextInt();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");

        String searchQuery = "SELECT account_balance FROM customer WHERE account_number = ?";
        PreparedStatement stmt = con.prepareStatement(searchQuery);
        stmt.setInt(1, accno);

        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            int sourceAccBalance = result.getInt(1);
            if (sourceAccBalance >= amount) {
                int newSourceAccBalance = sourceAccBalance - amount;

                String updateSourceQuery = "UPDATE customer SET account_balance = ? WHERE account_number = ?";
                PreparedStatement updateStmt = con.prepareStatement(updateSourceQuery);
                updateStmt.setInt(1, newSourceAccBalance);
                updateStmt.setInt(2, accno);
                int rowsAffectedSource = updateStmt.executeUpdate();

                if (rowsAffectedSource > 0) {
                    String updateDestinationQuery = "UPDATE customer SET account_balance = account_balance + ? WHERE account_number = ?";
                    PreparedStatement updateDestinationStmt = con.prepareStatement(updateDestinationQuery);
                    updateDestinationStmt.setInt(1, amount);
                    updateDestinationStmt.setInt(2, destinationAccNo);
                    int rowsAffectedDestination = updateDestinationStmt.executeUpdate();

                    if (rowsAffectedDestination > 0) {
                        System.out.println("\n Amount transferred successfully");
                    } else {
                        System.out.println("\n Failed to transfer to receiver's account");
                    }
                } else {
                    System.out.println("Failed to update sender's account");
                }
            } else {
                System.out.println("Insufficient balance in sender's account");
            }
        }
    }
    
    
    
    public void printMiniStatement(int accno) throws SQLException {
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");

        String historyQuery = "SELECT transaction_type, amount, transaction_date FROM transaction_history WHERE account_number = ? ORDER BY transaction_date DESC LIMIT 5";
        PreparedStatement stmt = con.prepareStatement(historyQuery);
        stmt.setInt(1, accno);

        ResultSet result = stmt.executeQuery();

        System.out.println("\n Mini Statement:");
        while (result.next()) {
            String transactionType = result.getString(1);
            int amount = result.getInt(2);
            String transactionDate = result.getTimestamp(3).toString();
            System.out.println("\n Transaction Type: " + transactionType + ", Amount: " + amount + ", Date: " + transactionDate);
        }
    }
    
    public void login() throws SQLException,Exception {
        System.out.print("\n Enter account number: ");
        int accountNumber = scanner.nextInt();
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
        
        String searchQuery = "SELECT * FROM customer WHERE account_number = ?";
        PreparedStatement stmt = con.prepareStatement(searchQuery);
        stmt.setInt(1, accountNumber);
        
        ResultSet result = stmt.executeQuery();

        if(result.next()) {
        	String name = result.getString(2);
        	
        	System.out.println("\n Customer login successfull");
        	while (true) {
                System.out.println("\n ==========Welcome "+name+"======================================================================================================================");
                System.out.println("\n Select Operation to perform:");
                System.out.println(" 1. Deposit");
                System.out.println(" 2. Withdraw");
                System.out.println(" 3. Print Mini Statement");
                System.out.println(" 4. Transfer Amount");
                System.out.println(" 5. Logout");
                System.out.println("\n ================================================================================================================================================");
                System.out.print(" Enter your choice: ");
                int customerChoice = scanner.nextInt();

                switch (customerChoice) {
                    case 1:
                    	System.out.println("\n =======================================================================================================================================");
                        deposit(accountNumber);
                        System.out.println("\n =======================================================================================================================================");
                        break;
                    case 2:
                    	System.out.println("\n ==================================================================================================================================");
                        withdraw(accountNumber);
                        System.out.println("\n ==================================================================================================================================");
                        break;
                    case 3:
                    	System.out.println("\n ==================================================================================================================================");
                        printMiniStatement(accountNumber);
                        System.out.println("\n ==================================================================================================================================");
                        break;
                    case 4:
                    	System.out.println("\n ==================================================================================================================================");
                        transferAmount(accountNumber);
                        System.out.println("\n ==================================================================================================================================");
                        break;
                    case 5:
                    	System.out.println("\n ==================================================================================================================================");
                    	System.out.println("\n Logging out from customer account\n ");
                    	System.out.println("\n Thankyou");
                    	con.close();
                    	System.out.println("\n ==================================================================================================================================");
                    	System.exit(0);
                    
                    default:
                        System.out.println(" Error! Please try again.");
                }
            }
        }
        else {
        	throw new Exception(" Customer Login Failed");
        }
        
    }
}


class AdministratorLogin {
    private Scanner sc = new Scanner(System.in);
    
    public void searchCustomer() throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	System.out.print(" Enter account no: ");
    	int accno = sc.nextInt();
    	
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
        
        String searchQuery = "SELECT * FROM customer WHERE account_number = ?";
        PreparedStatement stmt = con.prepareStatement(searchQuery);
        stmt.setInt(1, accno);
        
        ResultSet result = stmt.executeQuery();
        
        if(result.next()) {
        	System.out.println(" Customer found ");
        	
        	System.out.println(" Account Holder name: "+result.getString(2));
        	System.out.println(" Account no: "+result.getInt(3));
        	System.out.println(" Account balance: "+result.getInt(4));
        }
    }
    
    
    public void modifyCustomer() throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	System.out.print(" Enter account no: ");
    	int accno = sc.nextInt();
    	sc.nextLine(); 
    	
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
        
        String searchQuery = "SELECT * FROM customer WHERE account_number = ?";
        PreparedStatement stmt = con.prepareStatement(searchQuery);
        stmt.setInt(1, accno);
        
        ResultSet result = stmt.executeQuery();
        
        if(result.next()) {
        	System.out.print(" Enter New Name: ");
        	String newName = sc.nextLine();
        	
        	System.out.print(" Enter account no: ");
        	int newAccNo = sc.nextInt();
        	sc.nextLine();
        	
        	System.out.print(" Enter updated balance: ");
        	int newBal = sc.nextInt();
        	
        	String updateQuery = "update customer set name=?,account_number=?,account_balance=? where account_number=?";
        	PreparedStatement stmt1 = con.prepareStatement(updateQuery);
        	stmt1.setString(1, newName);
        	stmt1.setInt(2, newAccNo);
        	stmt1.setInt(3, newBal);
        	stmt1.setInt(4, accno);
        	
        	int rowsAffected = stmt1.executeUpdate();
        	
        	if(rowsAffected>0) {
        		System.out.println(" Customer updated successfully");
        	}else {
        		System.out.println(" Failed to update");
        	}
        	
        }
    }
    
    
    
    public void deleteCustomer() throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	System.out.print(" Enter account no: ");
    	int accno = sc.nextInt();
    	sc.nextLine(); 
    	
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
        
    	String deleteQuery = "DELETE FROM customer WHERE account_number = ?";
        PreparedStatement stmt = con.prepareStatement(deleteQuery);
        stmt.setInt(1, accno);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println(" Customer deleted successfully");
        } else {
            System.out.println(" Failed to delete customer");
        }
    }
    
    
    public void balanceEnquiry() throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	System.out.print(" Enter account no: ");
    	int accno = sc.nextInt();
    	sc.nextLine(); 
    	
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
        
    	String searchAccBalance = "SELECT * FROM customer WHERE account_number = ?";
        PreparedStatement stmt = con.prepareStatement(searchAccBalance);
        stmt.setInt(1, accno);

        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            System.out.println(" Account balance: "+result.getString(4));
        } else {
            System.out.println(" Account not found");
        }
    }
    
   
    
   
    
    public void addCustomer() throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	System.out.print(" Enter name: ");
    	String name = sc.nextLine();
    	System.out.print(" Enter account number: ");
    	int accno = sc.nextInt();
    	sc.nextLine();
    	System.out.println(" Enter initial balance: ");
    	int inibal = sc.nextInt();
    	
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
    	
    	String insertQuery = "INSERT INTO customer(name,account_number,account_balance) values(?,?,?)";
    	PreparedStatement stmt = con.prepareStatement(insertQuery);
    	stmt.setString(1, name);
    	stmt.setInt(2, accno);
    	stmt.setInt(3, inibal);
    	
    	int rowsAffected = stmt.executeUpdate();
    	
    	if(rowsAffected>0) {
        	System.out.println(" Customer added successfully");
        }else {
        	System.out.println(" Sorry, customer not added!");
        }
    	
    	
    	
    }

    public void login() throws SQLException,Exception {
        System.out.print("\n Enter administrator username: ");
        String adminUsername = sc.nextLine();
        System.out.print("\n Enter administrator password: ");
        String adminPassword = sc.nextLine();
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root");
        
        String searchQuery = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement stmt = con.prepareStatement(searchQuery);
        stmt.setString(1, adminUsername);
        stmt.setString(2, adminPassword);
        
        ResultSet result = stmt.executeQuery();
        
        if (result.next()) {
            System.out.println("\n Admin logged in successfully.");
            String name = result.getString(2);
            
            while (true) {
            	System.out.println("\n ==========Welcome "+name+"========================================================================================================");
            	System.out.println("\n Select Operation to perform:");
                System.out.println(" 1. Add Customer");
                System.out.println(" 2. Search Customer");
                System.out.println(" 3. Modify Customer");
                System.out.println(" 4. Delete Customer");
                System.out.println(" 5. Balance Inquiry");
                System.out.println(" 6. Logout");
                System.out.println("\n ==================================================================================================================================");
                System.out.print(" Enter your choice: ");
                int adminChoice = sc.nextInt();

                switch (adminChoice) {
                    case 1:
                    	System.out.println("\n ==================================================================================================================================");
                        addCustomer();
                        System.out.println("\n ==================================================================================================================================");
                        break;
                    case 2:
                    	System.out.println("\n ==================================================================================================================================");
                        searchCustomer();
                        System.out.println("\n ==================================================================================================================================");
                        break;
                    case 3:
                    	System.out.println("\n ==================================================================================================================================");
                    	modifyCustomer();
                    	System.out.println("\n ==================================================================================================================================");
                        break;
                    case 4:
                    	System.out.println("\n ==================================================================================================================================");
                        deleteCustomer();
                        System.out.println("\n ==================================================================================================================================");
                        break;
                    case 5:
                    	System.out.println("\n ==================================================================================================================================");
                        balanceEnquiry();
                        System.out.println("\n ==================================================================================================================================");
                        break;
                    case 6:
                    	System.out.println("\n ==================================================================================================================================");
                    	System.out.println(" Logging out from admin account");
                    	System.out.println(" Thank you");
                    	System.out.println("\n ==================================================================================================================================");
                    	con.close();
                    	System.exit(0);
                    default:
                        System.out.println(" Invalid choice. Please try again.");
                }
            }
            
            
        } else {
            
            throw new Exception("Invalid credentials. Please try again.");
        }

    }
}

public class Tester {
    public static void main(String[] args) throws Exception {
    	Scanner sc = new Scanner(System.in);
       
	        while (true) {
	        	System.out.println("\n ===============Welcome to Bank System===================================================================================================================");
	        	System.out.println(" Login As");
	            System.out.println(" 1. Administrator");
	            System.out.println(" 2. Customer");
	            System.out.println(" 3. Exit");
	            System.out.println("\n =========================================================================================================================================================");
	            System.out.print(" Enter your choice: ");
	            int choice = sc.nextInt();
	
	            switch (choice) {
	                case 1:
	                	System.out.println("\n =================================================================================================================================================");
	                    AdministratorLogin adminLogin = new AdministratorLogin();
	                    adminLogin.login();
	                    System.out.println("\n ==================================================================================================================================================");
	                    break;
	                case 2:
	                	System.out.println("\n ==================================================================================================================================================");
	                    CustomerLogin customerLogin = new CustomerLogin();
	                    customerLogin.login();
	                    System.out.println("\n ==================================================================================================================================================");
	                    break;
	                case 3:
	                	System.out.println("\n ==================================================================================================================================================");
	                	System.out.println(" Thankyou");
	                	System.out.println("\n ==================================================================================================================================================");
	                    System.exit(0);
	                default:
	                    System.out.println(" Invalid choice. Please try again.");
	            }
	        }
        
    }
}
