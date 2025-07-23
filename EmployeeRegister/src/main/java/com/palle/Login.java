package com.palle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		
        
		String password = request.getParameter("password");

		if ("Satheesh".equals(password)) {
		    HttpSession session = request.getSession();
		    session.setAttribute("loggedIn", true);
		    response.sendRedirect("view");
        } else {
            // ❌ wrong password
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3 style='color:red;'>Invalid Password</h3>");
            out.println("<a href='login.html'>Try Again</a>");
        }
	}

}
