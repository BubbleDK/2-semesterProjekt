package ctrl;

import java.time.LocalDate;

import model.B2BCustomer;
import model.Order;

public class OrderCtrl {
	
	public void OrderCtrl() {
		
	}

	
	public void registerB2BOrder(LocalDate endDate, int cvr, B2BCustomer c) {
		
		Order o = new Order(c);
	}
	
	public void addPackage(String barcode, int quantity) {
		
		
		
	}
	
	public void addB2BEmployee(String email) {
		
		
	}
	
	public Order endOrder() {
		
		return null;
	}
}
