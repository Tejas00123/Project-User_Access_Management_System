<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <style>
        /* Styling for the Navbar */
        .navbar {
            background-color: #333;
            overflow: hidden;
        }

        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
        }

        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Styling for the page content */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        }

        .container {
            padding: 20px;
        }

        h2 {
            color: #333;
        }
    </style>
</head>

<body>

    <!-- Navbar Section -->
    <div class="navbar">
        <a href="#">User Access Management System</a> <!-- Add your project name here -->
        <a href="signup.jsp">Sign Up</a>
        <a href="login.jsp">Log In</a>
    </div>

    <!-- Main Content Section -->
    <div class="container">
        <h2><%= "Welcome to User Access Management System!" %></h2>
    </div>

</body>
</html>
