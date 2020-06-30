package jdbcDay1Assessment;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

class AccountDetail{
	private String account_number,name,type;
	private Double balance;
	
	@Override
	public String toString() {
		return "AccountDetail [account_number=" + account_number + ", C_name=" + name + ", type=" + type
				+ ", balance=" + balance + "]";
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getname() {
		return name;
	}
	public void setname(String c_name) {
		name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	//public String () {
		// TODO Auto-generated method stub
		//return null;
	}//
//}


public class Account {
	
	ArrayList<AccountDetail> AccountDetails;
	Connection conn = null;
	Scanner scanner;
	
	Account()throws ClassNotFoundException,SQLException {
		AccountDetails = new ArrayList<AccountDetail>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "root");
		scanner = new Scanner(System.in);
		
	}
	
		void getAccountDetailsFromDatabase() throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("Select * from AccountDetails");
		AccountDetail accd;
		while (rs.next()) {
		accd = new AccountDetail();
		accd.setAccount_number(rs.getString(1));
		accd.setname(rs.getString(2));
		accd.setType(rs.getString(3));
		accd.setBalance(rs.getDouble(4));
		AccountDetails.add(accd);
		}
				
		}
		
		void printAllAccounts() {
			for (AccountDetail accd : AccountDetails) {
				System.out.println(accd);
			}
		}
		
		
		void getAccountDetailsFromUser() throws SQLException {
			
			String account_number = null;
			String name = null;
			String type = null;
			double balance=0.0f;
			System.out.println("Please enter the Account Number");
			account_number = scanner.nextLine();
			System.out.println("Please enter the Customer Name");
			name = scanner.nextLine();
			System.out.println("Please enter the Account Type");
			type= scanner.nextLine();
			System.out.println("Please enter the Balance");
			balance= scanner.nextDouble();
			AccountDetail accd = new AccountDetail();
			accd.setAccount_number(account_number);
			accd.setname(name);
			accd.setType(type);
			accd.setBalance(balance);
			insertAccountDetailsToDatabase(accd);
			//DeleteAccountDetailsInDatabase(accd);
			
	
		
		}

	 void insertAccountDetailsToDatabase(AccountDetail accd) throws SQLException {
		 
		 PreparedStatement st = conn.prepareStatement("insert into AccountDetails values(?,?,?,?)");
			st.setString(1, accd.getAccount_number());
			st.setString(2,accd.getname());
			st.setString(3,accd.getType());
			st.setDouble(4,accd.getBalance());
			int result = st.executeUpdate();
			System.out.println("Data Inserted");
		 
			
		}
	 
//void DeleteAccountDetailsInDatabase(AccountDetail accd) throws SQLException {
//	String account_number = null;
//	System.out.println("Please enter the Account Number");
//	account_number = scanner.nextLine();
//		 PreparedStatement st = conn.prepareStatement("delete from AccountDetails where account_number =?");
//			st.setString(1, accd.getAccount_number());
//			//st.setString(2,accd.getC_name());
//			//st.setString(3,accd.getType());
//			//st.setDouble(4,accd.getBalance());
//			int result = st.executeUpdate();
//			System.out.println("Data Deleted");
//		 
//			
//		}
	 
	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			try {
			Account acc = new Account();
			acc.getAccountDetailsFromUser();
			acc.getAccountDetailsFromDatabase();
			acc.printAllAccounts();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	



		

	}
