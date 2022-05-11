package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import db.OrderDBIF;

public class B2BOrder {
	private LocalDate endDate;
	private List<B2BOrderLine> orderLines;
	private B2BCustomer c;
	private HashMap<String, String> emailGiftNo;
	private int orderNo;

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
	
	public int getOrderNo() {
		return this.orderNo;
	}

	public boolean addB2BEmployee(String email) {
		boolean res = false;
		if(email != "") {
			if(getEmailGiftNo().size() > 0) {
				for(Map.Entry<String, String> entry : emailGiftNo.entrySet()) {
					if(!entry.getKey().equals(email)) {
						B2BLogin b2bLogin = new B2BLogin();
						emailGiftNo.put(email, b2bLogin.createGiftNo());
						res = true;
					}else {
						return false;
					}
				}
			}else {
				B2BLogin b2bLogin = new B2BLogin();
				emailGiftNo.put(email, b2bLogin.createGiftNo());
				res = true;
			}
		}
		return res;
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
	//TODO: ÆNDRE!
	public int newOrderNo() {
		orderNo = new Random().nextInt(1000000);
		return orderNo;
	}
	
	public HashMap<String, String> getEmailGiftNo() {
		return this.emailGiftNo;
	}
}
