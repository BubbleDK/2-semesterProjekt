package ctrl;

import db.CustomerDB;
import db.CustomerDBIF;
import exceptions.DataAccessException;
import model.B2BCustomer;

public class CustomerCtrl {
	private CustomerDBIF customerDB;

	public CustomerCtrl() throws DataAccessException {
		customerDB = new CustomerDB();
	}

	public B2BCustomer findB2BCustomer(int cvr) throws DataAccessException {
		return customerDB.findB2BCustomer(cvr);
	}
	
	
}
