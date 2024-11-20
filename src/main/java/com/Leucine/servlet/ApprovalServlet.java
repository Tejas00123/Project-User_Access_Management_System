package com.Leucine.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.Leucine.dao.RequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get session and check role
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        // Validate user role for authorization
        if (role == null || !role.equals("Manager")) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?msg=Unauthorized access");
            return;
        }

        // Retrieve the form data for the request ID and action (approve or reject)
        String requestId = req.getParameter("requestId");
        String action = req.getParameter("action");

        // Validate requestId and action
        if (requestId == null || action == null) {
            resp.sendRedirect(req.getContextPath() + "/pendingRequests.jsp?msg=Invalid request parameters");
            return;
        }

        // Process the request based on the action (approve/reject)
        boolean success = false;
        RequestDAO requestDAO = new RequestDAO();

        try {
           //Perform the update based on action
            if (action.equals("approve")) {
                success = requestDAO.updateRequestStatus(Integer.parseInt(requestId), "Approved");
            } else if (action.equals("reject")) {
                success = requestDAO.updateRequestStatus(Integer.parseInt(requestId), "Rejected");
            }

            // After updating, fetch the updated pending requests list
            if (success) {
                // Optionally, you can reload the pending requests here if needed
                List<Map<String, String>> pendingRequests = requestDAO.getPendingRequests();
                session.setAttribute("pendingRequests", pendingRequests);

                resp.sendRedirect(req.getContextPath() + "/pendingRequests.jsp?msg=Request " + action + "d successfully");
            } else {
                resp.sendRedirect(req.getContextPath() + "/pendingRequests.jsp?msg=Failed to " + action + " request");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/pendingRequests.jsp?msg=Error processing request");
        }
    }
}
