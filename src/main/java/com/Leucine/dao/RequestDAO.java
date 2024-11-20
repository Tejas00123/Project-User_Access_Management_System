package com.Leucine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.Leucine.utils.DBConnection;

public class RequestDAO {

	public boolean submitAccessRequest(int userId, String software, String accessType, String reason) {
        String query = "INSERT INTO requests (user_id, software_id, access_type, reason, status) "
                     + "SELECT ?, s.id, ?, ?, 'Pending' FROM software s WHERE s.name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, accessType);
            pstmt.setString(3, reason);
            pstmt.setString(4, software);

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
