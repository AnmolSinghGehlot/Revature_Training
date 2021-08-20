package com.project.employee;

public class Bank {

	private int employeeid;
	private String employeepassword;
	private String employeename;
	private String employeeaddress;

	public Bank(int employeeid, String employeepassword, String employeename, String employeeaddress) {
		super();
		this.employeeid = employeeid;
		this.employeepassword = employeepassword;
		this.employeename = employeename;
		this.employeeaddress = employeeaddress;
	}


	public Bank() {
		// TODO Auto-generated constructor stub
	}


	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getEmployeepassword() {
		return employeepassword;
	}

	public void setEmployeepassword(String employeepassword) {
		this.employeepassword = employeepassword;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getEmployeeaddress() {
		return employeeaddress;
	}

	public void setEmployeeaddress(String employeeaddress) {
		this.employeeaddress = employeeaddress;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", employeepassword=" + employeepassword + ", employeename="
				+ employeename + ", employeeaddress=" + employeeaddress + "]";
	}

}
