package com.project.employee;

public class Transactions {

	private int transactionid;
	private int customerid;
	private int deposit;
	private int withdraw;

	public Transactions(int transactionid, int customerid, int deposit, int withdraw) {
		super();
		this.transactionid = transactionid;
		this.customerid = customerid;
		this.deposit = deposit;
		this.withdraw = withdraw;
	}

	public Transactions() {
		// TODO Auto-generated constructor stub
	}

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}

	@Override
	public String toString() {
		return "Transactions [transactionid=" + transactionid + ", customerid=" + customerid + ", deposit=" + deposit
				+ ", withdraw=" + withdraw + "]";
	}
}
