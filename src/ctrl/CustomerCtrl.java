package ctrl;

import db.CustomerDB;
import model.B2BCustomer;

public class CustomerCtrl {
	private CustomerDB customerDB;

	public CustomerCtrl() {
		
	}

	public B2BCustomer findB2BCustomer(int CVR) {
		return customerDB.findB2BCustomer();
	}
	
	
}
