package com.Leucine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.Leucine.utils.DBConnection;

public class RegisterDAO {
	 // Method to insert a user into the database
    public boolean registerUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);

            int k = preparedStatement.executeUpdate();
            return k > 0; // Return true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an error
        }
        
    }
}
