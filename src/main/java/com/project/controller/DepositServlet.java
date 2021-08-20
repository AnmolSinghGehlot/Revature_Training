package com.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.exception.BankingException;
import com.project.services.CrudSearchService;
import com.project.services.CrudSearchServiceImpl;

/**
 * Servlet implementation class DepositServlet
 */
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepositServlet() {
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

		HttpSession session = request.getSession(false);
		int cid = (Integer) session.getAttribute("cid");
		int deposit = Integer.parseInt(request.getParameter("deposit"));

		if (deposit > 0 && cid > 0) {
			try {
				crudSearchService.deposit(cid, deposit);
				response.setContentType("text/html");
				out.print("<center><span><b><i><h1>Deposit Successful</h1></center></span></b></i>");
				out.print("<center><span><b><i><h2>Your Current Balance is:: " + crudSearchService.ViewBalance(cid)
						+ "</h2></center></span></b></i>");
			} catch (BankingException e) {
				e.printStackTrace();
			}

		} else {
			out.print("<center><span><b><i>Deposit Failed</center></span></b></i>");
			try {
				out.print("<center><span><b><i><h2>Your Current Balance is:: " + crudSearchService.ViewBalance(cid)
						+ "</h2></center></span></b></i>");
			} catch (BankingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
