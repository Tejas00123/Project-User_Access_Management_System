package com.Leucine.servlet;

import java.io.IOException;

import com.Leucine.dao.SoftwareDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Retrieving user session to validate Admin access
        HttpSession session = req.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;
        
        if (role == null || !role.equals("Admin")) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?msg=Unauthorized Access");
            return;
        }
        
      // Retrieving form data
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String[] accessLevels = req.getParameterValues("accessLevels");
        
     // Validating access levels
        StringBuilder accessLevelsStr = new StringBuilder();
        if (accessLevels != null) {
            for (String level : accessLevels) {
                accessLevelsStr.append(level).append(",");
            }
        }
        
     // Removing trailing comma
        String accessLevelsFormatted = accessLevelsStr.length() > 0 
                ? accessLevelsStr.substring(0, accessLevelsStr.length() - 1)
                : "";
        
     // Save software to the database
     // Returns true if the insert was successful
        boolean success = new SoftwareDAO().addSoftware(name, description, accessLevelsFormatted);
         
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/createSoftware.jsp?msg=Software added successfully");
        } else {
            resp.sendRedirect(req.getContextPath() + "/createSoftware.jsp?msg=Error adding software");
        }
	}
}
