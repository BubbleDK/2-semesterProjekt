package db;

import exceptions.DataAccessException;
import model.B2BCustomer;

interface CustomerDBIF {
	public B2BCustomer findB2BCustomer(int cvr) throws DataAccessException;
}
