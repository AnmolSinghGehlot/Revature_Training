package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.BankingDAO;
import com.project.dao.BankingDAOImpl;
import com.project.employee.Customer;
import com.project.exception.BankingException;
import com.project.services.CrudSearchService;
import com.project.services.CrudSearchServiceImpl;

/**
 * Servlet implementation class DeleteCustomerServlet
 */
public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CrudSearchService crudSearchService = new CrudSearchServiceImpl();
		BankingDAO bankingDAO = new BankingDAOImpl();
		Customer customer = new Customer();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = null;

		int cid = Integer.parseInt(request.getParameter("customerid"));
		customer.setCustomerid(Integer.parseInt(request.getParameter("cid")));

		try {
			if (bankingDAO.isValidCustomer(customer)) {

				crudSearchService.deletecustomer(cid);
				out.print("<center><span><b><i><h1>Delection Successful</h1></center></span></b></i>");
				requestDispatcher = request.getRequestDispatcher("Project_1_Employee.html");
				requestDispatcher.include(request, response);
			} else {
				out.print("No Such Customer");
			}

		} catch (BankingException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
