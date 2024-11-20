package com.Leucine.servlet;

import java.io.IOException;

import com.Leucine.dao.RequestDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Check if the session exists
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?msg=Please login to submit requests");
            return;
        }

        // Retrieve userId from the session
        Integer userId =(Integer) session.getAttribute("userId");

		/*
		 * System.out.println("====================================");
		 * System.out.println("Session ID: " + session.getId());
		 * System.out.println("UserID: " + userId);
		 */

        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?msg=Please login to submit requests");
            return;
        }

        // Retrieving form data
        String software = req.getParameter("software");
        String accessType = req.getParameter("accessType");
        String reason = req.getParameter("reason");

        // Save the request to the database
        boolean success = new RequestDAO().submitAccessRequest(userId, software, accessType, reason);

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/requestAccess.jsp?msg=Request submitted successfully");
        } else {
            resp.sendRedirect(req.getContextPath() + "/requestAccess.jsp?msg=Failed to submit request");
        }
    }
}
