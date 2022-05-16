package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class B2BOrder {
	private LocalDate endDate;
	private String dateText;
	private List<B2BOrderLine> orderLines;
	private B2BCustomer c;
	private HashMap<String, String> emailGiftNo;
	private int orderNo;

	public B2BOrder(String endDateString,B2BCustomer c) {
		this.c = c;
		
		orderLines = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.endDate = LocalDate.parse(endDateString,formatter);
		dateText = endDate.format(formatter);
		emailGiftNo = new HashMap<String, String>();
	}
	
	public B2BOrder() {
		
	}
	
	public B2BOrderLine addOrderLine(B2BOrderLine ol) {
		orderLines.add(ol);
		return ol;
	}
	
	public String getEndDate() {
		return dateText;
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

	public synchronized boolean addB2BLogin(String email) {
		boolean res = false;
		if(email != "") {
			if(getEmailGiftNo().size() > 0) {
				for(Map.Entry<String, String> entry : emailGiftNo.entrySet()) {
					if(!entry.getKey().equals(email)) {
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
			if(res) {
				B2BLogin b2bLogin = new B2BLogin();
				emailGiftNo.put(email, b2bLogin.createGiftNo());
			}
		}
		return res;
	}
	
	
	//TODO: Skal alle disse settere være der?
	public void setEndDate(String endDateString) {
		endDate = LocalDate.parse(endDateString,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
	public void setOrderLines(B2BOrderLine orderLine) {
		orderLines.add(orderLine);
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

	public B2BOrderLine choosePack(Pack p) {
		return this.addOrderLine(new B2BOrderLine(p));
	}
}
