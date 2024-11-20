package com.Leucine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Leucine.utils.DBConnection;

public class LoginDAO {
   
	public String validateUser(String username, String password) {
        String role = null;
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

         // Validating user credentials and return the user's role if valid.
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log exception
        }

        return role;
    }
	
	 public Integer getUserId(String username, String password) {
	        Integer userId = null;
	        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, username);
	            ps.setString(2, password);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                userId = rs.getInt("id");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return userId;
	    }
}
