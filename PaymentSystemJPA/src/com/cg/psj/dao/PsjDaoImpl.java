package com.cg.psj.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.cg.psj.dto.PsjCustomer;
import com.cg.psj.dto.PsjTransaction;
import com.cg.psj.exception.PsjException;

public class PsjDaoImpl implements PsjDao {

	
	EntityManager manager;
	
	
	
	
	public PsjDaoImpl() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
		manager = emf.createEntityManager();
	}

	@Override
	public void createAccount(PsjCustomer customer) {
		manager.getTransaction().begin();
		manager.persist(customer);
		manager.getTransaction().commit();
		
	}

	@Override
	public void deposit(String mobileNo, double amount) {
manager.getTransaction().begin();
		
		PsjCustomer cust =  manager.find(PsjCustomer.class, mobileNo);
		double amt = cust.getCustomerinitialBalance();
		amt = amt + amount;
		System.out.println(amt);
		cust.setCustomerinitialBalance(amt);
		
		manager.getTransaction().commit();
		
		PsjTransaction t = new PsjTransaction();
		t.setBalance(amt);
		t.setDebit(null);
		t.setCredit(String.valueOf(amount));
		t.setMobile_no(mobileNo);
		t.setName(cust.getCustomername());
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		t.setTrans(String.valueOf(dateFormat.format(date)));*/
		
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		
	}

	@Override
	public void withdraw(String mobileNo, double withdrawAmount) {
		manager.getTransaction().begin();
		boolean flag = false;
		PsjCustomer cust = manager.find(PsjCustomer.class, mobileNo);
		double amount = cust.getCustomerinitialBalance();
		if(!(amount-withdrawAmount > 500)){
			System.err.println("Insufficient Balance in you account.\nNo amount deducted from your account.\nPlease try again");
			cust.setCustomerinitialBalance(amount);
		}
		else{
			amount -= withdrawAmount;
			cust.setCustomerinitialBalance(amount);
			System.out.println("Amount Rs."+withdrawAmount+" withdrawed successfully");
			flag = true;			
		}
		manager.getTransaction().commit();
		
		if(flag){
			PsjTransaction t = new PsjTransaction();
			t.setBalance(amount);
			t.setDebit(String.valueOf(withdrawAmount));
			t.setCredit(null);
			t.setMobile_no(mobileNo);
			t.setName(cust.getCustomername());
			/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			t.setTrans(String.valueOf(dateFormat.format(date)));*/
			
			manager.getTransaction().begin();
			manager.persist(t);
			manager.getTransaction().commit();
		
	}
	}	

	@Override
	public double checkBalance(String mobileNo) {
		
		manager.getTransaction().begin();
		PsjCustomer cust = manager.find(PsjCustomer.class, mobileNo);
		double amount = cust.getCustomerinitialBalance();
		manager.getTransaction().commit();
		
		return amount;
	}

	@Override
	public void fundTransfer(String sender, String reciever, double amount) {
		manager.getTransaction().begin();
		boolean flag = false;
		PsjCustomer custSender = manager.find(PsjCustomer.class, sender);
		PsjCustomer custreciever = manager.find(PsjCustomer.class, reciever);
		
		double senderAmount = custSender.getCustomerinitialBalance();
		double recieverAmount = custreciever.getCustomerinitialBalance();
		
		if((senderAmount - amount) > 500){
			senderAmount -= amount;
			recieverAmount += amount;
			custreciever.setCustomerinitialBalance(recieverAmount);
			custSender.setCustomerinitialBalance(senderAmount);
			flag = true;
			System.out.println("Fund of Rs."+amount+" transferred successfully! from "+custSender.getCustomername()+" to "+custreciever.getCustomername());
		}else{
			
			System.err.println("Invalid amount! As transfer amount is greater than your account balance.");
		}
		
		manager.getTransaction().commit();
		
		if(flag){
			PsjTransaction tSender = new PsjTransaction();
			tSender.setBalance(senderAmount);
			tSender.setDebit(String.valueOf(amount));
			tSender.setCredit(null);
			tSender.setMobile_no(sender);
			tSender.setName(custSender.getCustomername());
			/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			tSender.setTrans(String.valueOf(dateFormat.format(date)));*/
			
			manager.getTransaction().begin();
			manager.persist(tSender);
			manager.getTransaction().commit();
			
			
			
			PsjTransaction tReciever = new PsjTransaction();
			tReciever.setBalance(recieverAmount);
			tReciever.setDebit(null);
			tReciever.setCredit(String.valueOf(amount));
			tReciever.setMobile_no(sender);
			tReciever.setName(custreciever.getCustomername());
			/*DateFormat dateFormatReciever = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date dateReciever = new Date();
			tReciever.setTrans(String.valueOf(dateFormatReciever.format(dateReciever)));*/
			
			manager.getTransaction().begin();
			manager.persist(tReciever);
			manager.getTransaction().commit();
			
			
		}
		
	}

	@Override
	public boolean validateAccount(String mobileNo) throws PsjException {
		PsjCustomer customer = manager.find(PsjCustomer.class, mobileNo);
		if(customer == null)
			return false;
		return true;
	}

	@Override
	public void getTransactionList(String mobileNo) {
		String sql = "select tr from Transaction tr where mobile_no ="+mobileNo;
		TypedQuery<PsjTransaction> query = manager.createQuery(sql, PsjTransaction.class);
		List<PsjTransaction> list = query.getResultList();
		System.out.println("Mobile No          Name           Credit        Debit            Transaction Id         Balance");
		for(PsjTransaction t : list){
			System.out.println(t.getMobile_no()+"          "+t.getName()+"          "+t.getCredit()+"           "+t.getDebit()+"        "+t.getTrans()+"          "+t.getBalance());
		}
	}
		
	}


