package com.palle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class View
 */
@WebServlet("/view")
public class View extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("loggedIn") == null) {
	            response.sendRedirect("login.html");
	            return;
	        }
		
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><head><title>Employee List</title>");
        out.println("<style>table { border-collapse: collapse; width: 80%; margin: 20px auto; }");
        out.println("th, td { padding: 10px; border: 1px solid #ccc; text-align: center; }");
        out.println("th { background-color: #007bff; color: white; }</style></head>");
        out.println("<body><h2 style='text-align:center;'>Employee Details</h2>");

        try {
            // DB Connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "359035");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp");

            out.println("<table>");
            out.println("<tr><th>Name</th><th>Department</th><th>Salary</th><th>Email</th><th>Phone</th><th>Date_Of_Joining</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>" + rs.getString("salary") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("phone") + "</td>");
                out.println("<td>" + rs.getDate("date_of_joining") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            con.close();

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
	}

	

}
