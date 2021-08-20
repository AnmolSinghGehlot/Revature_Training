package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.BankingDAO;
import com.project.dao.BankingDAOImpl;
import com.project.employee.Customer;
import com.project.exception.BankingException;

/**
 * Servlet implementation class LoginCustomer
 */
public class LoginCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Customer customer = new Customer();
		BankingDAO bankingDAO = new BankingDAOImpl();

		PrintWriter out = response.getWriter();
		customer.setCustomerid(Integer.parseInt(request.getParameter("cid")));
		customer.setCustomerpassword(Integer.parseInt(request.getParameter("password")));
		HttpSession session = request.getSession();
		session.setAttribute("cid", customer.getCustomerid());
		try {
			if (bankingDAO.isValidCustomer(customer)) {
				response.sendRedirect("Project_1_CFeatures.html");
			} else {
				response.setContentType("text/html");
				out.print("<center><span><b><i><h1>Invalid Credentials</h1></center></span></b></i>");
				out.print("<center><span><b><i><h2>Try Again</h></center></span></b></i>");

			}
		} catch (BankingException e) {
			out.print("<center><span style='color:red;'>Invalid Credentials</span></center>");
			out.print("<center><span><b><i><h2>Try Again</h></center></span></b></i>");
		}

	}

}
