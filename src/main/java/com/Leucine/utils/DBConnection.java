package com.Leucine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	    static {
	        try {
	            // Explicitly load the PostgreSQL JDBC driver
	            Class.forName("org.postgresql.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            throw new RuntimeException("PostgreSQL JDBC Driver not found.");
	        }
	    }

	  
       public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(AppConstant.DB_URL,
	        		AppConstant.DB_USER, AppConstant.DB_PASSWORD);
	    }
	 
	 
}
