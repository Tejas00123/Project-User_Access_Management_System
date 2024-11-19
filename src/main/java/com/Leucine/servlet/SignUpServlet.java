package com.Leucine.servlet;

import java.io.IOException;

import com.Leucine.dao.RegisterDAO;
import com.Leucine.utils.AppConstant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//retrive form data
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role = AppConstant.ROLE_EMPLOYEE; //default role
		
		//calling the DAO method to insert user into the database
		boolean k = new RegisterDAO().registerUser(username, password, role);
		
		if(k) { //if true
			resp.sendRedirect(req.getContextPath()+"/login.jsp?msg=User registered Successfully..!");
		}else {
			resp.sendRedirect(req.getContextPath()+"/login.jsp?msg=Registration failed.. Try again!!!");
		}
	}
}
