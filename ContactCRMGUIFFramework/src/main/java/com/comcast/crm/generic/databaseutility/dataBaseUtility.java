package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class dataBaseUtility {
	// connection of database
	 Connection con;
	 public void getDbconnection(String url,String username,String password) {
		 try {
			 Driver driver = new Driver();
			 DriverManager.registerDriver(driver);
			 
			 DriverManager.getConnection(url,username,password);
		 }catch(Exception e) {
			 
		 }
	 }
	 
	 // other method of connection of database (hardcoded)
	 public void getDbconnection( ) {
		 try {
			 Driver driver = new Driver();
			 DriverManager.registerDriver(driver);
			 
			 DriverManager.getConnection("jsbc:mysql://localhost:3306/projects","root","root");
		 }catch(Exception e) {
			 
		 }
	 }
	 
	 public void closeDbconnection () throws SQLException {
		 try {
			 con.close();
		 }catch(Exception e) {
			 
		 }
		 
	 }
	 
	 public ResultSet executeconSelectQuery(String query) {
		 ResultSet result=null;
		 try {
			  Statement stat = con.createStatement();
			  result=stat.executeQuery(query);
		 }catch(Exception e) {
			 
		 }
		return result;
	 }
	 
	 public int executeNonSelectQuery(String query) {
		 int result =0;
		 try {
		Statement stat= con.createStatement();
		 result=stat.executeUpdate(query);
		 }catch(Exception e) {
			 
		 }
		return result;
	 }

}
