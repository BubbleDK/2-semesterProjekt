package ctrl;

import java.time.LocalDate;

import exceptions.DataAccessException;
import model.B2BCustomer;
import model.B2BOrder;
import model.Person;

public class OrderCtrl {
	
	private ProductCtrl productCtrl;
	private CustomerCtrl customerCtrl;

	public void OrderCtrl() {
		productCtrl = new ProductCtrl();
	}

	
	public void registerB2BOrder(LocalDate endDate, int cvr, B2BCustomer c) {
		B2BOrder o = new B2BOrder(c);
	}
	
	public void addPackage(String barcode, int quantity) throws DataAccessException {
		productCtrl.findProduct(barcode);
		
		
	}
	
	public void addB2BCustomer(int cvr) throws DataAccessException {
		customerCtrl.findB2BCustomer(cvr);
		
	}
	
	public B2BOrder endOrder() {
		
		return null;
	}
}
