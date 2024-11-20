package com.Leucine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Leucine.utils.DBConnection;

public class SoftwareDAO {
      //Add new software to the database
	public boolean addSoftware(String name, String description, String accessLevels) {
        String query = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, accessLevels);

            return pstmt.executeUpdate() > 0; // Returns true if the insert was successful
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
        }

        return false;
    }
	
	//Retrieves all software names from the database.
	public List<String> getAllSoftwareNames() {
        List<String> softwareList = new ArrayList<>();
        String query = "SELECT name FROM software";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                softwareList.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return softwareList;
    }
}
