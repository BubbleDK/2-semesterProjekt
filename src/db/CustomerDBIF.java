package db;

import exceptions.DataAccessException;
import model.B2BCustomer;

public interface CustomerDBIF {
	public B2BCustomer findB2BCustomer(int cvr) throws DataAccessException;
}
