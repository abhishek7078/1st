package com.cg.mba.dao;

import com.cg.mba.dto.MbaCustomer;
import com.cg.mba.exception.MbaException;



public interface IMbaDao {

	public void createAccount(MbaCustomer customer);
	
	public void deposit(String mobileNo, double amount);
	
	public void withdraw(String mobileNo, double amount);
	
	public double checkBalance(String mobileNo);
	
	public void fundTransfer(String sender, String reciever, double amount);
	
	public boolean validateAccount(String mobileNo) throws MbaException;
	
	public void getTransactionList(String mobileNo);
	
}
