<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Link to external CSS -->
</head>
<body>
    <div class="login-container">
        <h4>Login</h4>
        <form method="post" action="LoginServlet">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
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
