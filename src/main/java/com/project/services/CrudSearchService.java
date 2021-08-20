package com.project.services;

import java.util.List;

import com.project.employee.Bank;
import com.project.employee.Customer;
import com.project.employee.Transactions;
import com.project.exception.BankingException;

public interface CrudSearchService {

	public int withdrawal(int customerid, int amount) throws BankingException;

	public int deposit(int id, int give) throws BankingException;

	public List<Transactions> getalltransactions(int customerid) throws BankingException;
	
	public List<Customer> getallcustomers() throws BankingException;

	public Customer getCustomer(int id, int pass) throws BankingException;

	public Bank getemployee(int employeeid, String employeepassword) throws BankingException;

	public Customer createCustomer(String name, long aadhar, int password, String gender) throws BankingException;

	public Customer deletecustomer(int customerid) throws BankingException;

	public int ViewBalance(int id) throws BankingException;

	public int ViewCustomerId(long youraadhar, int yourPassword) throws BankingException;

	public int ViewPassword(long aadhar, int cusId) throws BankingException;

}
