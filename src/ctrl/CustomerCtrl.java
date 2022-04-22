package ctrl;

import db.CustomerDB;
import exceptions.DataAccessException;
import model.B2BCustomer;

public class CustomerCtrl {
	private CustomerDB customerDB;

	public CustomerCtrl() {
		
	}

	public B2BCustomer findB2BCustomer(int cvr) throws DataAccessException {
		return customerDB.findB2BCustomer(cvr);
	}
	
	
}
