package com.Leucine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Leucine.utils.DBConnection;

public class RequestDAO {

    // Method to submit a new access request
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
    
    // Method to fetch all pending requests
    public List<Map<String, String>> getPendingRequests() {
        String sql = "SELECT r.id AS request_id, u.username AS employee_name, s.name AS software_name, r.access_type, r.reason "
                     + "FROM requests r "
                     + "JOIN users u ON r.user_id = u.id "
                     + "JOIN software s ON r.software_id = s.id "
                     + "WHERE r.status = 'Pending'";

        List<Map<String, String>> pendingRequests = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, String> request = new HashMap<>();
                request.put("requestId", String.valueOf(rs.getInt("request_id")));
                request.put("employeeName", rs.getString("employee_name"));
                request.put("softwareName", rs.getString("software_name"));
                request.put("accessType", rs.getString("access_type"));
                request.put("reason", rs.getString("reason"));
                pendingRequests.add(request);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingRequests;
    }

    // Method to update the status of an access request
    public boolean updateRequestStatus(int requestId, String status) {
        String sql = "UPDATE requests SET status = ? WHERE id = ?"; // Corrected to 'id' instead of 'request_id'
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, requestId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Return true if the update is successful

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
