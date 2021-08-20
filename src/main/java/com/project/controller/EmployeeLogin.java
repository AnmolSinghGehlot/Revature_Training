package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.BankingDAO;
import com.project.dao.BankingDAOImpl;
import com.project.employee.Bank;
import com.project.exception.BankingException;

/**
 * Servlet implementation class EmployeeLogin
 */
public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Bank bank = new Bank();
		BankingDAO bankingDAO = new BankingDAOImpl();
		System.out.println("Test 0");
		PrintWriter out = response.getWriter();
		System.out.println("Test 3");
		bank.setEmployeeid(Integer.parseInt(request.getParameter("employeeid")));
		System.out.println("Test 4");
		bank.setEmployeepassword(request.getParameter("employeepassword"));
		System.out.println("Test 5");
		try {
			if (bankingDAO.isValidEmployee(bank)) {
				System.out.println("Test 1");
				response.sendRedirect("Project_1_Employee.html");
			} else {
				System.out.println("Test 2");
				response.setContentType("text/html");
				out.print("<h1><center><span style='color:red;'>Invalid Credentials</span></center></h1>");
				out.print("<center><span><b><i><h2>Try Again</h></center></span></b></i>");
			}
		} catch (BankingException e) {
			out.print("<h1><center><span style='color:red;'>Invalid Credentials</span></center></h1>");
			out.print("<center><span><b><i><h2>Try Again</h></center></span></b></i>");
		}

	}

}
