<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.Leucine.dao.SoftwareDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Access</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Link to external CSS -->
</head>
<body>
    <div class="request-container">
        <h1>Request Access</h1>
        
        <form method="post" action="RequestServlet">
            <!-- Software Name Dropdown -->
            <div class="form-group">
                <label for="software">Software Name</label>
                <select id="software" name="software" required>
                    <% 
                        try {
                            // Fetch the software list dynamically
                            SoftwareDAO dao = new SoftwareDAO();
                            List<String> softwareList = dao.getAllSoftwareNames();
                            if (softwareList != null && !softwareList.isEmpty()) {
                                for (String software : softwareList) {
                    %>
                                    <option value="<%= software %>"><%= software %></option>
                    <% 
                                }
                            } else { 
                    %>
                                <option value="" disabled>No software available</option>
                    <% 
                            }
                        } catch (Exception e) {
                            // Handle any errors in fetching the list
                    %>
                            <option value="" disabled>Error fetching software</option>
                    <% } %>
                </select>
            </div>

            <!-- Access Type Dropdown -->
            <div class="form-group">
                <label for="accessType">Access Type</label>
                <select id="accessType" name="accessType" required>
                    <option value="Read">Read</option>
                    <option value="Write">Write</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>

            <!-- Reason for Request -->
            <div class="form-group">
                <label for="reason">Reason for Request</label>
                <textarea id="reason" name="reason" rows="4" required></textarea>
            </div>

            <!-- Submit Button -->
            <button type="submit">Submit Request</button>
        </form>

        <% 
            // Show success or failure messages
            String msg = request.getParameter("msg");
            if (msg != null && !msg.trim().isEmpty()) {
        %>
            <p class="message"><%= msg %></p>
        <% } %>
    </div>
</body>
</html>
