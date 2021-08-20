package com.project.employee;

public class Customer {

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", customername=" + customername + ", aadhar=" + aadhar
				+ ", customerpassword=" + customerpassword + ", balance=" + balance + ", gender=" + gender + "]";
	}

	private int customerid;
	private String customername;
	private long aadhar;
	private int customerpassword;
	private int balance;
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public double getAadhar() {
		return aadhar;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}

	public int getCustomerpassword() {
		return customerpassword;
	}

	public void setCustomerpassword(int customerpassword) {
		this.customerpassword = customerpassword;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerid, String customername, long aadhar, int customerpassword, int balance,
			String gender) {
		super();
		this.customerid = customerid;
		this.customername = customername;
		this.aadhar = aadhar;
		this.customerpassword = customerpassword;
		this.balance = balance;
		this.gender = gender;
	}
	
	

}
