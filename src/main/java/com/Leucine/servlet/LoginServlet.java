package com.Leucine.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.Leucine.dao.LoginDAO;
import com.Leucine.dao.RequestDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Retrieve form data
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// Validate user credentials and return the user's role if valid.
		String role = new LoginDAO().validateUser(username, password);
		Integer userId = new LoginDAO().getUserId(username, password); // Retrieve userId
		
		if(role!=null)
		{
			// User is valid, create a session
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            session.setAttribute("userId", userId); //save user id
            
         // Redirect based on role
            switch (role) {
                case "Employee":
                    // Redirect to requestAccess.jsp
                    resp.sendRedirect(req.getContextPath() + "/requestAccess.jsp");
                    break;
                case "Manager":
                	// Fetch pending requests for managers
                    RequestDAO dao = new RequestDAO();
                    List<Map<String, String>> pendingRequests = dao.getPendingRequests();

                    // Set pending requests in the session
                    session.setAttribute("pendingRequests", pendingRequests);

                    // Redirect to pendingRequests.jsp
                    resp.sendRedirect(req.getContextPath() + "/pendingRequests.jsp");
                    break;
                case "Admin":
                    resp.sendRedirect(req.getContextPath() + "/createSoftware.jsp");
                    break;
                default:
                    // Role not recognized
                    session.invalidate();
                    resp.sendRedirect(req.getContextPath() + "/login.jsp?msg=Unauthorized role");
            }
		}
		else {
            // Invalid credentials, redirect back to login page with error message
            resp.sendRedirect(req.getContextPath() + "/login.jsp?msg=Invalid username or password");
        }
		
		
	}
}
