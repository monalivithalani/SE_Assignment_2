package com.Assignment2.monali;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean (name = "loginBean")
@SessionScoped
public class LoginBean {
	
	private String userName;
	private String password;
	private String name;
	public LoginBean() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 
	public String getByUserName(){
		Connection con = null;
		try {
			// Setup the DataSource object
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setServerName("localhost");
			ds.setPortNumber(3306);
			ds.setDatabaseName("icsi518_db");
			ds.setUser("root");
			ds.setPassword("ICSI518_PASSWORD");

			// Get a connection object
			con = ds.getConnection();

			// Get a prepared SQL statement
			String sql = "SELECT name from usertable where userName = ? and password = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.userName);
			st.setString(2, this.password);
			
			// Execute the statement
			ResultSet rs = st.executeQuery();

			// Iterate through results
			if (rs.next()) {
				System.out.println("Hello: " + rs.getString("name"));
				this.name = rs.getString("name");
			}
			else {
				System.out.println("Username or password is incorrect");
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		
		//return "UserMain?faces-redirect=true";
		return "result?faces-redirect=true";
	}
	}
