package jdbcDay1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


class User{
	private int account_num;
	private String name;
	private double balance;

	
	
public int getAccount_num() {
		return account_num;
	}



	public void setAccount_num(int account_num) {
		this.account_num = account_num;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



public class Account {

	ArrayList<User> users ;
	Connection conn=null;
	Scanner scanner;
	

	Account() throws ClassNotFoundException, SQLException{
		users = new ArrayList<User>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","janani123");
		scanner = new Scanner(System.in);
	}
	

	@Override
	protected void finalize() throws Throwable {
		conn.close();
	}

	void getUserDetailsFromDatabase() throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("Select * from account_details");
		User user;
		while(rs.next()) {
			/*
			System.out.println("Actor ID "+rs.getInt(1));
			 
			System.out.println("Actor Name "+rs.getString(2)+" "+rs.getString(3));
			System.out.println("-------------------------------");*/
			user = new User();
			user.setAccount_num(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setBalance(rs.getDouble(3));
			users.add(user);
		}
	}
	void printAllUser() {
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	void getUserDetailsFromUser() throws SQLException {
		int account_num=0;
		double balance=0;
		String name = null;
		System.out.println("Please enter the USer account_number");
		account_num=scanner.nextInt();
		System.out.println("Please enter the USer name");
		name=scanner.toString();
		scanner.nextLine();
		System.out.println("Please enter the USer balance");
		balance=scanner.nextDouble();
		User user = new User();
		user.setAccount_num(account_num);
		user.setName(name);
		user.setBalance(balance);
		insertUserDetailsToDatabase(user);
	}
	void insertUserDetailsToDatabase(User user) throws SQLException {
		PreparedStatement st  = conn.prepareStatement("insert into account_details values(?,?,?)");
		st.setInt(1, user.getAccount_num());
		st.setString(2, user.getName());
		st.setDouble(3, user.getBalance());
		int result = st.executeUpdate();//DML
		System.out.println("Data Inserted");
	}
	
	void showAllDetails() {
		
		System.out.println("Account_Number:" + account_num);
		System.out.println("Name:" + name);
		System.out.println("Balance:" + balance);
	}
	
	void updateUserDetailsToDatabase(User user) throws SQLException {
		PreparedStatement st  = conn.prepareStatement("update account_details set(?,?,?)");
		st.setInt(1, user.getAccount_num());
		st.setString(2, user.getName());
		st.setDouble(3, user.getBalance());
		int result = st.executeUpdate();//DML
		System.out.println("Data Inserted");
	}
	/*insert in to the database
Show all account details to user
Update balance for an account
Delete and account
exit*/
	void Menu() {
		int menu;
		System.out.println("Menu");
		System.out.println("1. insert in to the database");
		System.out.println("2. Show all account details to user");
		System.out.println("3. Update balance for an account");
		System.out.println("4. Delete an account");
		System.out.println("5. Exit");
		boolean quit = false;
		do {
			System.out.print("Please enter your choice: ");
			menu = scanner.nextInt();
			switch (menu) {
			case 1:
				insertUserDetailsToDatabase(User);
				break;

			case 2:
				;
				break;

			case 3:
				showAllDetails();
				break;

			case 4:
				quit = true;
				System.out.println("exited");
				break;
			}
		} while (!quit);


	}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Account account = new Account();
		
			account.getUserDetailsFromUser();
			account.getUserDetailsFromDatabase();
			account.printAllUser();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	}

