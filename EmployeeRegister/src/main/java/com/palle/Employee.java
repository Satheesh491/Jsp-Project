package com.palle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/register")
public class Employee extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String phone = request.getParameter("phone");
	        String salaryStr = request.getParameter("salary");
	        double salary = Double.parseDouble(salaryStr);


	        try {
	            // Load JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Connect to DB
	            Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "359035");

	            // SQL Insert Query
	            String sql = "INSERT INTO emp (Empname, email, phone, salary) VALUES (?, ?, ?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(sql);

	            // Set parameters
	            stmt.setString(1, name);
	            stmt.setString(2, email);
	            stmt.setString(3, phone);
	            stmt.setDouble(4, salary);

	            // Execute update
	            int rowsInserted = stmt.executeUpdate();

	            // Response
	            PrintWriter out = response.getWriter();
	            if (rowsInserted > 0) {
	               RequestDispatcher dispatcher = request.getRequestDispatcher("redirect.html");
	               dispatcher.forward(request, response);
	            } else {
	                out.println("Error registering employee.");
	            }
	            stmt.close();
	            conn.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
             
	
	}

}
