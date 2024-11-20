<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Requests</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Add custom styles for the table and its borders */
        table {
            width: 100%;
            border-collapse: collapse; /* Ensures borders are joined */
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd; /* Light grey border for cells */
            text-align: left;
        }
        th {
            background-color: #f2f2f2; /* Light grey background for headers */
        }
        td {
            background-color: #fff;
        }
        /* Style for buttons inside the table */
        button {
            padding: 5px 10px;
            margin-right: 5px;
            cursor: pointer;
        }
        .approve {
            background-color: green;
            color: white;
        }
        .reject {
            background-color: red;
            color: white;
        }
    </style>
</head>
<body>
    <div class="pending-requests-container">
        <h1>Pending Access Requests</h1>

        <%
            // Use the implicit session object
            List<Map<String, String>> pendingRequests = (List<Map<String, String>>) session.getAttribute("pendingRequests");
            
            // Check if the list of pending requests is null or empty
            if (pendingRequests == null || pendingRequests.isEmpty()) {
        %>
            <p>No pending requests at the moment.</p>
        <%
            } else {
        %>
        <table>
            <thead>
                <tr> 
                    <th>Employee Name</th>
                    <th>Software Name</th>
                    <th>Access Type</th>
                    <th>Reason</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    for (Map<String, String> req : pendingRequests) { 
                %>
                <tr>
                    <td><%= req.get("employeeName") %></td>
                    <td><%= req.get("softwareName") %></td>
                    <td><%= req.get("accessType") %></td>
                    <td><%= req.get("reason") %></td>
                    <td>
                        <form method="post" action="ApprovalServlet">
                            <input type="hidden" name="requestId" value="<%= req.get("requestId") %>">
                            <button type="submit" name="action" value="approve" class="approve">Approve</button>
                            <button type="submit" name="action" value="reject" class="reject">Reject</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</body>
</html>
