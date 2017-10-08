package com.Assignment2.monali;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean (name = "regBean")
@SessionScoped
public class RegBean {
	private String userName;
	private String password;
	private String name;
	private String email;
	private String s;
	

	public String getS() {
		return s;
	}


	public void setS(String s) {
		this.s = s;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String Register(){
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
			String sql = "INSERT INTO usertable (name, email, userName, password) values (?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.name);
			st.setString(2, this.email);
			st.setString(3, this.userName);
			st.setString(4, this.password);
			// Execute the statement
			int rs = st.executeUpdate();
			
			// Iterate through results
			if (rs > 0) {
				System.out.println("Hello: you are successfully registered");
				//this.name = rs.getString("name");
			}
			
				//System.out.println();
			
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		//return "UserMain?faces-redirect=true";
		return "Signin?faces-redirect=true";
		
	}
	public String userNameValidator(){
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
			String sql = "SELECT userName from usertable where userName = ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, this.userName);
			
			// Execute the statement
			ResultSet rs = st.executeQuery();
			
			// Iterate through results
			if (rs.next()) {
				System.out.println(this.userName +"is already registered");
				//this.name = rs.getString("name");
			}
			
				//System.out.println();
			
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		//return "UserMain?faces-redirect=true";
		//return "Signin?faces-redirect=true";
		return this.userName;
		
	}
}
