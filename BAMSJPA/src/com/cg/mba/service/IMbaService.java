package com.cg.mba.service;

import com.cg.mba.dto.MbaCustomer;
import com.cg.mba.exception.MbaException;;



public interface IMbaService {
	
	
public void createAccount(MbaCustomer customer);
	
	public void deposit(String mobileNo, double amount);
	
	public void withdraw(String mobileNo, double amount);
	
	public double checkBalance(String mobileNo);
	
	public void fundTransfer(String sender, String reciever, double amount);
	
	
	public boolean validateAccount(String mobileNo) throws MbaException;
	
	public boolean validateName(String name) throws MbaException;
	
	public boolean validateAge(float age) throws MbaException;
	
	public boolean validateMoileNo(String mobileNo) throws MbaException;
	
	public boolean validateAmount(double amount) throws MbaException;
			
	public void getTransactionList(String mobileNo);

}
