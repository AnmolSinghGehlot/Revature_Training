package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.exception.BankingException;
import com.project.services.CrudSearchService;
import com.project.services.CrudSearchServiceImpl;

/**
 * Servlet implementation class ForgotPassword
 */
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPassword() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cid = Integer.parseInt(request.getParameter("cid"));
		long aadhar = Long.parseLong(request.getParameter("aadhar"));

		if (cid > 1000) {
			try {
				out.print("<center><span><b><i><h2>Your Password is:: " + crudSearchService.ViewPassword(aadhar, cid)
						+ "</h2></center></span></b></i>");
			} catch (BankingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			out.print("<h1><center><span style='color:red;'>Invalid Credentials</span></center></h1>");

		}

	}

}
