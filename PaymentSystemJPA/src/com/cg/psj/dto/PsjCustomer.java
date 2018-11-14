package com.cg.psj.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class PsjCustomer {

	@Column
	private String Customername;
	
	@Id
	@Column(name="mobile_no")
	private String CustomermobileNo;
	
	@Column
	private float Customerage;
	
	@Column(name="init_bal")
	private double CustomerinitialBalance;
	
	public PsjCustomer() {
		super();
	
	}

	public String getCustomername() {
		return Customername;
	}

	public void setCustomername(String customername) {
		Customername = customername;
	}

	public String getCustomermobileNo() {
		return CustomermobileNo;
	}

	public void setCustomermobileNo(String customermobileNo) {
		CustomermobileNo = customermobileNo;
	}

	public float getCustomerage() {
		return Customerage;
	}

	public void setCustomerage(float customerage) {
		Customerage = customerage;
	}

	public double getCustomerinitialBalance() {
		return CustomerinitialBalance;
	}

	public void setCustomerinitialBalance(double customerinitialBalance) {
		CustomerinitialBalance = customerinitialBalance;
	}

	public PsjCustomer(String customername, String customermobileNo, float customerage, double customerinitialBalance) {
		super();
		Customername = customername;
		CustomermobileNo = customermobileNo;
		Customerage = customerage;
		CustomerinitialBalance = customerinitialBalance;
	}
	
	
}