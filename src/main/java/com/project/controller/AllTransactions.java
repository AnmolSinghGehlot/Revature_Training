package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.project.exception.BankingException;
import com.project.services.CrudSearchService;
import com.project.services.CrudSearchServiceImpl;

/**
 * Servlet implementation class AllTransactions
 */
public class AllTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Gson gson = new Gson();
		CrudSearchService crudSearchService = new CrudSearchServiceImpl();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		int cid = (Integer) session.getAttribute("cid");
		// System.out.println(cid);

		try {
			out.print(gson.toJson(crudSearchService.getalltransactions(cid)));
		} catch (BankingException e) {
			out.print("Something Went Wrong");
		}

	}

}
