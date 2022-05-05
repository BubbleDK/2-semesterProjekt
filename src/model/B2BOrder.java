package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.OrderDBIF;

public class B2BOrder {
	private LocalDate endDate;
	private List<B2BOrderLine> orderLines;
	private B2BCustomer c;
	private HashMap<String, String> emailGiftNo;


	public B2BOrder(String endDateString,B2BCustomer c) {
		this.c = c;
		orderLines = new ArrayList<>();
		endDate = LocalDate.parse(endDateString,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		emailGiftNo = new HashMap<String, String>();
	}
	
	public B2BOrder() {
		
	}
	
	public B2BOrderLine addOrderLine(B2BOrderLine ol) {
		orderLines.add(ol);
		return ol;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public List<B2BOrderLine> getOrderLines(){
		return orderLines;
	}
	
	public B2BCustomer getB2BCustomer() {
		return c;
	}

	public void addB2BEmployee(String email) {
		B2BLogin b2bLogin = new B2BLogin();
		emailGiftNo.put(email, b2bLogin.createGiftNo());
	}
	//TODO: Skal alle disse settere være der?
	public void setEndDate(String endDateString) {
		endDate = LocalDate.parse(endDateString,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
	public void setOrderLines(B2BOrderLine PackLine) {
		orderLines.add(PackLine);
	}
	
	public void setCustomer(B2BCustomer c) {
		this.c = c;
	}
	
	public void setEmailGiftNo(HashMap emailGiftNo) {
		emailGiftNo = new HashMap<String,String>();
	}
}
