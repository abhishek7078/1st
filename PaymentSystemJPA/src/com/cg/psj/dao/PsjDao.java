package com.cg.psj.dao;

import com.cg.psj.dto.PsjCustomer;
import com.cg.psj.exception.PsjException;



public interface PsjDao {

	public void createAccount(PsjCustomer customer);
	
	public void deposit(String mobileNo, double amount);
	
	public void withdraw(String mobileNo, double amount);
	
	public double checkBalance(String mobileNo);
	
	public void fundTransfer(String sender, String reciever, double amount);
	
	public boolean validateAccount(String mobileNo) throws PsjException;
	
	public void getTransactionList(String mobileNo);
	
}
