package ctrl;

import java.time.LocalDate;

import model.B2BCustomer;
import model.B2BOrder;

public class OrderCtrl {
	
	public void OrderCtrl() {
		
	}

	
	public void registerB2BOrder(LocalDate endDate, int cvr, B2BCustomer c) {
		
		B2BOrder o = new B2BOrder(c);
	}
	
	public void addPackage(String barcode, int quantity) {
		
		
		
	}
	
	public void addB2BEmployee(String email) {
		
		
	}
	
	public B2BOrder endOrder() {
		
		return null;
	}
}
