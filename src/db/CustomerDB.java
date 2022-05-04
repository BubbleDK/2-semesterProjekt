package db;

import exceptions.DataAccessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.B2BCustomer;

public class CustomerDB implements CustomerDBIF {
	private B2BCustomer currCustomer;
	private static final String FIND_CUSTOMER_BY_CVR = "SELECT * FROM kk_B2BCustomer WHERE cvr = ?";
	private static PreparedStatement findCustomer;

	public CustomerDB() {
		try {
			findCustomer = DBConnection.getInstance().getConnection().prepareStatement(FIND_CUSTOMER_BY_CVR);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public B2BCustomer findB2BCustomer(int cvr) throws exceptions.DataAccessException {
		B2BCustomer currCustomer = null;
		try {
			findCustomer.setInt(1, cvr);
			ResultSet rs = findCustomer.executeQuery();
			currCustomer = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
//			e.printStackTrace();
		}
		return currCustomer;
	}
	//TODO husk at lave et view som kan trækkes info ud fra til customerobjekter
	private B2BCustomer buildObject(ResultSet rs) {
		currCustomer = new B2BCustomer();
		try {
			if(rs.next()) {
				currCustomer.setCvr(rs.getInt("cvr"));
				currCustomer.setCompanyName(rs.getString("companyName"));
				System.out.println(currCustomer.getCVR());
				System.out.println(currCustomer.getCompanyName());
//				currCustomer.setName(rs.getString("name"));
//				currCustomer.setAddress(rs.getString("address"));
//				currCustomer.setZipCode(rs.getInt("zipcode"));
//				currCustomer.setPhoneNo(rs.getInt("phoneno"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(currCustomer);
		return currCustomer;
	}
}
