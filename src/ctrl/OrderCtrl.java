package ctrl;

import java.time.LocalDate;

import model.B2BCustomer;
import model.B2BOrder;

public class OrderCtrl {
	
	private ProductCtrl productCtrl;

	public void OrderCtrl() {
		productCtrl = new ProductCtrl();
	}

	
	public void registerB2BOrder(LocalDate endDate, int cvr, B2BCustomer c) {
		B2BOrder o = new B2BOrder(c);
	}
	
	public void addPackage(String barcode, int quantity) {
		productCtrl.findProduct(barcode);
		
		
	}
	
	public void addB2BEmployee(String email) {
		
		
	}
	
	public B2BOrder endOrder() {
		
		return null;
	}
}
