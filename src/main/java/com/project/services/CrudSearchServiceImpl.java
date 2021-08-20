package com.project.services;

import java.util.List;

import com.project.dao.BankingDAO;
import com.project.dao.BankingDAOImpl;
import com.project.employee.Bank;
import com.project.employee.Customer;
import com.project.employee.Transactions;
import com.project.exception.BankingException;

public class CrudSearchServiceImpl implements CrudSearchService {

	private BankingDAO bankingDAO = new BankingDAOImpl();

	@Override
	public int withdrawal(int customerid, int amount) throws BankingException {

		if (customerid <= 0)
			throw new BankingException("Invalid Customer Id: " + customerid);
		else {
			return bankingDAO.withdrawal(customerid, amount);
		}
	}

	@Override
	public int deposit(int id, int give) throws BankingException {
		if (id <= 0)
			throw new BankingException("Invalid Customer Id: " + id);
		else {
			return bankingDAO.deposit(id, give);
		}
	}

	@Override
	public List<Transactions> getalltransactions(int customerid) throws BankingException {
		List<Transactions> transactionlist = null;
		if (customerid <= 0)
			throw new BankingException("Invalid Customer Id: " + customerid);
		else {
			transactionlist = bankingDAO.getalltransactions(customerid);
		}
		return transactionlist;
	}

	@Override
	public Bank getemployee(int employeeid, String employeepassword) throws BankingException {
		Bank customer = null;
		if (employeeid <= 0)
			throw new BankingException("Invalid Employee Id: " + employeeid);
		else {
			customer = bankingDAO.getemployee(employeeid, employeepassword);
		}
		return customer;
	}

	@Override
	public Customer createCustomer(String name, long aadhar, int password, String gender) throws BankingException {
		Customer customer = null;
		if ((name == null) && (aadhar == 0) && (password == 0))
			throw new BankingException("Please enter valid Credentials");
		else {
			customer = bankingDAO.createCustomer(name, aadhar, password, gender);
		}
		return customer;
	}

	@Override
	public Customer getCustomer(int id, int pass) throws BankingException {
		Customer customer = null;
		if (pass == 0)
			throw new BankingException("Please enter valid Credentials");
		else {
			customer = bankingDAO.getCustomer(id, pass);
		}
		return customer;
	}

	@Override
	public List<Customer> getallcustomers() throws BankingException {
		List<Customer> customerslist = null;
		customerslist = bankingDAO.getallcustomers();
		return customerslist;
	}

	@Override
	public Customer deletecustomer(int customerid) throws BankingException {
		Customer customers = null;
		if (customerid <= 0)
			throw new BankingException("Invalid Customer Id: " + customerid);
		else {
			customers = bankingDAO.deletecustomer(customerid);
		}
		return customers;
	}

	@Override
	public int ViewBalance(int id) throws BankingException {
		int customers = 0;
		if (id <= 0)
			throw new BankingException("Invalid Customer Id: " + id);
		else {
			customers = bankingDAO.viewBalance(id);
		}
		return customers;

	}

	@Override
	public int ViewCustomerId(long youraadhar, int yourPassword) throws BankingException {
		int customers = 0;
		if ((youraadhar < 0) && (yourPassword < 0))
			throw new BankingException("Invalid Customer Credentials");
		else {
			customers = bankingDAO.ViewCustomerId(youraadhar, yourPassword);
		}
		return customers;
	}

	@Override
	public int ViewPassword(long aadhar, int cusId) throws BankingException {
		int customers = 0;
		if ((aadhar <= 0) && (cusId <= 0))
			throw new BankingException("Invalid Customer Credentials");
		else {
			customers = bankingDAO.ViewPassword(aadhar, cusId);
		}
		return customers;
	}

}
