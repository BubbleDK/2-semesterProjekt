package tests;

import java.sql.Connection;

import ctrl.CustomerCtrl;
import ctrl.OrderCtrl;
import db.DBConnection;
import exceptions.DataAccessException;
import model.B2BOrder;

public class TryMe {
public static void main(String[] args) throws DataAccessException {
	// Arrange
			Connection con = DBConnection.getInstance().getConnection();
			OrderCtrl orderCtrl = new OrderCtrl();
			CustomerCtrl customerCtrl = new CustomerCtrl();
			B2BOrder o = orderCtrl.registerB2BOrder("20-05-2022", 123456789);
			
			// Act
			orderCtrl.addPackage(null);
			System.out.println(o);
}
}
