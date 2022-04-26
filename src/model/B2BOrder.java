package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class B2BOrder {
	private int CVR;
	private LocalDate endDate;
	private List<B2BOrderLine> orderLines;
	private B2BCustomer c;
	private List<String> B2BLogin;

	public B2BOrder(String endDateString,B2BCustomer c) {
		this.c = c;
		endDate = LocalDate.parse(endDateString,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
	public B2BOrderLine addOrderLine(B2BOrderLine ol) {
		return null;
	}
	
	public void registerB2BLogin(String email) {
		
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
}
