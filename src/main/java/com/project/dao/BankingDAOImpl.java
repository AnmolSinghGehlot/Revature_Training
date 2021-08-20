package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import java.sql.SQLException;

import com.project.dbutil.PostgresConnection;
import com.project.employee.Bank;
import com.project.employee.Customer;
import com.project.employee.Transactions;
import com.project.exception.BankingException;

public class BankingDAOImpl implements BankingDAO {
	private static Logger log = Logger.getLogger(BankingDAOImpl.class);

	Customer customer = new Customer();

	@Override
	public Customer getCustomer(int customerid, int customerpassword) throws BankingException {
		Customer customer = null;

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Select * from lena_bank_schema.bank where customerid=? and customerpassword=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerid);
			preparedStatement.setInt(2, customerpassword);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				customer = new Customer();
				customer.setCustomerid(resultSet.getInt("customerid"));
				customer.setCustomername(resultSet.getString("customername"));
				customer.setAadhar(resultSet.getLong("aadhar"));
			} else {
				throw new BankingException("Wrong Credentials Entered");
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BankingException("Internal Error Occured, Contact Anmol ;P");
		}
		return customer;
	}

	@Override
	public Bank getemployee(int employeeid, String employeepassword) throws BankingException {
		Bank employee = null;

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Select * from lena_bank_schema.employeedetails where employeeid=? and employeepassword=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employeeid);
			preparedStatement.setString(2, employeepassword);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				employee = new Bank();
				employee.setEmployeeid(resultSet.getInt("employeeid"));
				employee.setEmployeename(resultSet.getString("employeename"));
				employee.setEmployeeaddress(resultSet.getString("employeeaddress"));
			} else {
				throw new BankingException("Wrong Credentials Entered");
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BankingException("Internal Error Occured, Contact Anmol ;P");
		}
		return employee;
	}

	@Override
	public Customer createCustomer(String name, long aadhar, int password, String gender) throws BankingException {

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into lena_bank_schema.bank(customername,aadhar,customerpassword,gender) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, name);
			preparedStatement.setLong(2, aadhar);
			preparedStatement.setInt(3, password);
			preparedStatement.setString(4, gender);
			int c = preparedStatement.executeUpdate();

			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();

				if (resultSet.next()) {
					customer.setCustomername(resultSet.getString("customername"));
					customer.setCustomerpassword(resultSet.getInt("customerpassword"));
					customer.setAadhar(resultSet.getLong("aadhar"));
					customer.setCustomerid(resultSet.getInt("customerid"));
					customer.setBalance(resultSet.getInt("balance"));
				} else {
					throw new BankingException("Customer Registration Failed. Please try again..");
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BankingException("Internal Error Occured, Contact Anmol ;P");
		}
		return customer;
	}

	@Override
	public Customer deletecustomer(int customerid) throws BankingException {
		Customer customer = null;

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "delete from lena_bank_schema.bank where customerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, customerid);
			int c = preparedStatement.executeUpdate();

			if (c == 1) {
				@SuppressWarnings("unused")
				ResultSet resultset = preparedStatement.getGeneratedKeys();
			} else {
				throw new BankingException("No product found with id " + customerid);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BankingException("Internal error occured... Kindly conatct Anmol :P");
		}
		return customer;
	}

	@Override
	public int viewBalance(int id) throws BankingException {

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Select balance from lena_bank_schema.bank where customerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				customer.setBalance(resultset.getInt("balance"));
			} else {
				throw new BankingException();
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BankingException("Internal error occured... Kindly conatct Anmol :P");
		}
		return customer.getBalance();
	}

	@Override
	public int ViewCustomerId(long youraadhar, int yourPassword) throws BankingException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Select customerid from lena_bank_schema.bank where aadhar=? and customerpassword=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, youraadhar);
			preparedStatement.setInt(2, yourPassword);
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				customer.setCustomerid(resultset.getInt("customerid"));
			} else {
				throw new BankingException();
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BankingException("Internal error occured... Kindly conatct Anmol :P");
		}
		return customer.getCustomerid();
	}

	@Override
	public int ViewPassword(long aadhar, int cusId) throws BankingException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Select customerpassword from lena_bank_schema.bank where aadhar=? and customerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, aadhar);
			preparedStatement.setInt(2, cusId);
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				customer.setCustomerpassword(resultset.getInt("customerpassword"));
			} else {
				throw new BankingException();
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BankingException("Internal error occured... Kindly conatct Anmol :P");
		}
		return customer.getCustomerpassword();
	}

	@Override
	public int withdrawal(int customerid, int amount) throws BankingException {

		int t = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into lena_bank_schema.transactions(customerid,withdrawal,deposit) values(?,?,0)";
			String sql1 = "update lena_bank_schema.bank set balance=balance-? where customerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, customerid);
			preparedStatement.setInt(2, amount);
			preparedStatement1.setInt(1, amount);
			preparedStatement1.setInt(2, customerid);

			preparedStatement.executeUpdate();
			int c = preparedStatement1.executeUpdate();
			ResultSet resultSet = preparedStatement1.getGeneratedKeys();
			if (c == 1)
				if (resultSet.next()) {
					t = resultSet.getInt("balance");
				}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BankingException("Internal error occured... Kindly conatct Anmol :P");
		}

		return t;
	}

	@Override
	public int deposit(int customerid, int deposit) throws BankingException {

		int t = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into lena_bank_schema.transactions(customerid,withdrawal,deposit) values(?,0,?)";
			String sql1 = "update lena_bank_schema.bank set balance=balance+? where customerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, customerid);
			preparedStatement.setInt(2, deposit);
			preparedStatement1.setInt(1, deposit);
			preparedStatement1.setInt(2, customerid);

			preparedStatement.executeUpdate();
			int c = preparedStatement1.executeUpdate();
			ResultSet resultSet = preparedStatement1.getGeneratedKeys();
			if (c == 1)
				if (resultSet.next()) {
					t = resultSet.getInt("balance");
				}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BankingException("Internal error occured... Kindly conatct Anmol :P");
		}

		return t;
	}

	@Override
	public List<Transactions> getalltransactions(int customerid) throws BankingException {
		List<Transactions> transactionslist = new ArrayList<>();

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from lena_bank_schema.transactions where customerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerid);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Transactions transactions = new Transactions();
				transactions.setTransactionid(resultSet.getInt("transactionid"));
				transactions.setCustomerid(resultSet.getInt("customerid"));
				transactions.setDeposit(resultSet.getInt("deposit"));
				transactions.setWithdraw(resultSet.getInt("withdrawal"));
				transactionslist.add(transactions);
			}

			if (transactionslist.size() == 0) {
				throw new BankingException("No transactions from Customer Id ::" + customerid);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);// logger
			throw new BankingException("Internal error occured... Kindly conatct Anmol ;P");
		}
		return transactionslist;
	}

	@Override
	public List<Customer> getallcustomers() throws BankingException {
		List<Customer> customerlist = new ArrayList<>();

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select * from lena_bank_schema.bank";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				customer.setCustomerid(resultSet.getInt("customerid"));
				customer.setCustomername(resultSet.getString("customername"));
				customer.setAadhar(resultSet.getLong("aadhar"));
				customer.setBalance(resultSet.getInt("balance"));
				customerlist.add(customer);
			}

			if (customerlist.size() == 0) {
				throw new BankingException("No customer to show");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);// logger
			throw new BankingException("Internal error occured... Kindly conatct Anmol ;P");
		}
		return customerlist;
	}

	@Override
	public boolean isValidCustomer(Customer customer) throws BankingException {
		boolean z = false;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select customerid from lena_bank_schema.bank where customerid=? and customerpassword=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getCustomerid());
			preparedStatement.setInt(2, customer.getCustomerpassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				z = true;
			} else {
				z = false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new BankingException("Invalid Login Credentials");
		}
		return z;
	}

	@Override
	public boolean isValidEmployee(Bank bank) throws BankingException {
		boolean z = false;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select employeeid from lena_bank_schema.employeedetails where employeeid=? and employeepassword=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bank.getEmployeeid());
			preparedStatement.setString(2, bank.getEmployeepassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				z = true;
			} else {
				z = false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new BankingException("Invalid Login Credentials");
		}
		return z;
	}

}
