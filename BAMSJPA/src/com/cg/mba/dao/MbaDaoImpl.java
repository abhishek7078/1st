package com.cg.mba.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.cg.mba.dto.MbaCustomer;
import com.cg.mba.dto.MbaTransaction;
import com.cg.mba.exception.MbaException;

public class MbaDaoImpl implements IMbaDao {

	
	EntityManager manager;
	
	
	
	
	public MbaDaoImpl() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
		manager = emf.createEntityManager();
	}

	@Override
	public void createAccount(MbaCustomer customer) {
		manager.getTransaction().begin();
		manager.persist(customer);
		manager.getTransaction().commit();
		
	}

	@Override
	public void deposit(String mobileNo, double amount) {
manager.getTransaction().begin();
		
		MbaCustomer cust =  manager.find(MbaCustomer.class, mobileNo);
		double amt = cust.getInitialBalance();
		amt = amt + amount;
		System.out.println(amt);
		cust.setInitialBalance(amt);
		
		manager.getTransaction().commit();
		
		MbaTransaction t = new MbaTransaction();
		t.setBalance(amt);
		t.setDebit(null);
		t.setCredit(String.valueOf(amount));
		t.setMobile_no(mobileNo);
		t.setName(cust.getName());
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
		MbaCustomer cust = manager.find(MbaCustomer.class, mobileNo);
		double amount = cust.getInitialBalance();
		if(!(amount-withdrawAmount > 500)){
			System.err.println("Insufficient Balance in you account.\nNo amount deducted from your account.\nPlease try again");
			cust.setInitialBalance(amount);
		}
		else{
			amount -= withdrawAmount;
			cust.setInitialBalance(amount);
			System.out.println("Amount Rs."+withdrawAmount+" withdrawed successfully");
			flag = true;			
		}
		manager.getTransaction().commit();
		
		if(flag){
			MbaTransaction t = new MbaTransaction();
			t.setBalance(amount);
			t.setDebit(String.valueOf(withdrawAmount));
			t.setCredit(null);
			t.setMobile_no(mobileNo);
			t.setName(cust.getName());
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
		MbaCustomer cust = manager.find(MbaCustomer.class, mobileNo);
		double amount = cust.getInitialBalance();
		manager.getTransaction().commit();
		
		return amount;
	}

	@Override
	public void fundTransfer(String sender, String reciever, double amount) {
		manager.getTransaction().begin();
		boolean flag = false;
		MbaCustomer custSender = manager.find(MbaCustomer.class, sender);
		MbaCustomer custreciever = manager.find(MbaCustomer.class, reciever);
		
		double senderAmount = custSender.getInitialBalance();
		double recieverAmount = custreciever.getInitialBalance();
		
		if((senderAmount - amount) > 500){
			senderAmount -= amount;
			recieverAmount += amount;
			custreciever.setInitialBalance(recieverAmount);
			custSender.setInitialBalance(senderAmount);
			flag = true;
			System.out.println("Fund of Rs."+amount+" transferred successfully! from "+custSender.getName()+" to "+custreciever.getName());
		}else{
			
			System.err.println("Invalid amount! As transfer amount is greater than your account balance.");
		}
		
		manager.getTransaction().commit();
		
		if(flag){
			MbaTransaction tSender = new MbaTransaction();
			tSender.setBalance(senderAmount);
			tSender.setDebit(String.valueOf(amount));
			tSender.setCredit(null);
			tSender.setMobile_no(sender);
			tSender.setName(custSender.getName());
			/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			tSender.setTrans(String.valueOf(dateFormat.format(date)));*/
			
			manager.getTransaction().begin();
			manager.persist(tSender);
			manager.getTransaction().commit();
			
			
			
			MbaTransaction tReciever = new MbaTransaction();
			tReciever.setBalance(recieverAmount);
			tReciever.setDebit(null);
			tReciever.setCredit(String.valueOf(amount));
			tReciever.setMobile_no(sender);
			tReciever.setName(custreciever.getName());
			/*DateFormat dateFormatReciever = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date dateReciever = new Date();
			tReciever.setTrans(String.valueOf(dateFormatReciever.format(dateReciever)));*/
			
			manager.getTransaction().begin();
			manager.persist(tReciever);
			manager.getTransaction().commit();
			
			
		}
		
	}

	@Override
	public boolean validateAccount(String mobileNo) throws MbaException {
		MbaCustomer customer = manager.find(MbaCustomer.class, mobileNo);
		if(customer == null)
			return false;
		return true;
	}

	@Override
	public void getTransactionList(String mobileNo) {
		String sql = "select tr from Transaction tr where mobile_no ="+mobileNo;
		TypedQuery<MbaTransaction> query = manager.createQuery(sql, MbaTransaction.class);
		List<MbaTransaction> list = query.getResultList();
		System.out.println("Mobile No          Name           Credit        Debit            Transaction Id         Balance");
		for(MbaTransaction t : list){
			System.out.println(t.getMobile_no()+"          "+t.getName()+"          "+t.getCredit()+"           "+t.getDebit()+"        "+t.getTrans()+"          "+t.getBalance());
		}
	}
		
	}


