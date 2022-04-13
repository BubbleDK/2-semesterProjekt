package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.B2BCustomer;

public class CustomerDB implements CustomerDBIF {
	private B2BCustomer c;
	private static int FIND_CUSTOMER_BY_CVR;
	private static PreparedStatement findCustomer;

	public CustomerDB() {
		
	}
	
	
	@Override
	public B2BCustomer findCustomer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void buildObject(ResultSet rs) {
		
	}
}
