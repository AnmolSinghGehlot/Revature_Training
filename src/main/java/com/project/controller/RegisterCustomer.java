package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.employee.Customer;
import com.project.exception.BankingException;
import com.project.services.CrudSearchService;
import com.project.services.CrudSearchServiceImpl;

/**
 * Servlet implementation class RegisterCustomer
 */
public class RegisterCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Gson gson = new Gson();
		CrudSearchService crudSearchService = new CrudSearchServiceImpl();

		PrintWriter out = response.getWriter();
		Customer customer = gson.fromJson(request.getReader(), Customer.class);

		String name = request.getParameter("name");
		String testaadhar = request.getParameter("aadhar");
		String testcpass = request.getParameter("password");
		String gender = request.getParameter("Gender");

		if (!name.matches("^[0-9]*$") && name != "" && testaadhar.matches("[0-9]{12}")
				&& testcpass.matches("[0-9]{4}")) {

			long aadhar = Long.parseLong(request.getParameter("aadhar"));
			int password = Integer.parseInt(request.getParameter("password"));

			try {
				customer = crudSearchService.createCustomer(name, aadhar, password, gender);
				String s = String.valueOf(customer.getCustomerid());
				Cookie c1 = new Cookie("customerid", s);
				response.addCookie(c1);
				response.setContentType("text/html");
				out.print("<center><span><b><i><h1>Customer Registered Successful</h1></center></span></b></i>");
				out.print("<center><span><b><i><h2>Note Your Details Carefully</h2></center></span></b></i>");
				out.print("<center><span><b><i><h2>Your Customer Id is :: "
						+ crudSearchService.ViewCustomerId(aadhar, password) + "</h2></center></span></b></i>");
				out.print("<center><span><b><i><h2>Your Password is :: "
						+ crudSearchService.ViewPassword(aadhar, crudSearchService.ViewCustomerId(aadhar, password))
						+ "</h2></center></span></b></i>");
			} catch (BankingException e) {
				System.out.println(e.getMessage());
				response.setContentType("text/html");
				out.print("<center><span style='color:red;'>Invalid Credentials</span></center>");
				out.print("<center><span><b><i><h2>Try Again</h2></center></span></b></i>");
			}

		} else {
			response.setContentType("text/html");
			out.print("<center><span style='color:red;'>Invalid Credentials</span></center>");
			out.print("<center><span><b><i><h2>Try Again</h2></center></span></b></i>");
		}

	}

}
