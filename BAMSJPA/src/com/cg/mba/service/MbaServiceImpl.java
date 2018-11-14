package com.cg.mba.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.mba.dao.MbaDaoImpl;
import com.cg.mba.dto.MbaCustomer;
import com.cg.mba.exception.MbaException;


public class MbaServiceImpl implements IMbaService {

	MbaDaoImpl dao = new MbaDaoImpl();
	
	@Override
	public void createAccount(MbaCustomer customer) {
		dao.createAccount(customer);
		
	}

	@Override
	public void deposit(String mobileNo, double amount) {
		dao.deposit(mobileNo, amount);
		
	}

	@Override
	public void withdraw(String mobileNo, double amount) {
		dao.withdraw(mobileNo, amount);
		
	}

	@Override
	public double checkBalance(String mobileNo) {
		
		return dao.checkBalance(mobileNo);
	}

	@Override
	public void fundTransfer(String sender, String reciever, double amount) {
		dao.fundTransfer(sender, reciever, amount);
		
	}

	@Override
	public boolean validateAccount(String mobileNo) throws MbaException {
		
		return dao.validateAccount(mobileNo);
	}

	@Override
	public boolean validateName(String name) throws MbaException {
		try{
			if(name == null)
				throw new MbaException("Null value found");
			Pattern p = Pattern.compile("[A-Z]{1}[a-z]{3,20}");
			Matcher m = p.matcher(name); 
			if(!m.matches())
				System.out.println("Name invalid!, please enter correct name..");
			return m.matches();
		}catch(MbaException e){
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean validateAge(float age) throws MbaException {
		try{
			// TODO Auto-generated method stub
			if(age == 0)
				throw new MbaException("Age cannot be  null");
			else if(age >100)
				throw new MbaException("Age cannot be  greater than 100");
			else if(age < 0)
				throw new MbaException("Age cannot be a negative number");
			else if(age >17)
				return true;
			
		
	} catch (Exception e) {
		System.out.println(e);
	}
		return false;
	}

	@Override
	public boolean validateMoileNo(String mobileNo) throws MbaException {
		try{
			// TODO Auto-generated method stub
			if(mobileNo == null)
				throw new MbaException("Null value found");
			Pattern p = Pattern.compile("[0-9]{10}");
			Matcher m = p.matcher(mobileNo);
			if(!m.matches())
				System.out.println("Mobile Number Invalid!");
			return m.matches();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public boolean validateAmount(double amount) throws MbaException {
		try{
			if(amount == 0)
				throw new MbaException("Null value found, please enter monbile no again");
			String am = String.valueOf(amount);
			if(!am.matches("\\d{1,9}\\.\\d{0,3}"))
				System.out.println("Invalid Amount!");
			return (am.matches("\\d{1,9}\\.\\d{0,3}"));
		}catch(MbaException e){
			System.out.println(e);
		}
		return false;
	}

	@Override
	public void getTransactionList(String mobileNo) {
		dao.getTransactionList(mobileNo);
		
	}

}
