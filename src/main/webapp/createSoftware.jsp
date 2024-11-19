<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Software</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Link to your CSS -->
</head>
<body>
    <div class="software-container">
        <h1>Create Software</h1>
        <form method="post" action="SoftwareServlet">
            <div class="form-group">
                <label for="name">Software Name</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <label>Access Levels</label>
                <div class="checkbox-group">
                    <label><input type="checkbox" name="accessLevels" value="Read"> Read</label>
                    <label><input type="checkbox" name="accessLevels" value="Write"> Write</label>
                    <label><input type="checkbox" name="accessLevels" value="Admin"> Admin</label>
                </div>
            </div>
            <button type="submit">Add Software</button>
        </form>
        <% 
            String msg = request.getParameter("msg");
            if (msg != null && !msg.trim().isEmpty()) {
        %>
            <p class="message"><%= msg %></p>
        <% } %>
    </div>
</body>
</html>
