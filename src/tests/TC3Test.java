package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ctrl.CustomerCtrl;
import ctrl.OrderCtrl;
import db.DBConnection;
import exceptions.DataAccessException;
import model.AbstractProduct;

class TC3Test {

	
	

	@Test
	void testProductIsNull() throws DataAccessException {
		// Arrange
		Connection con = DBConnection.getInstance().getConnection();
		OrderCtrl orderCtrl = new OrderCtrl();
		CustomerCtrl customerCtrl = new CustomerCtrl();
		orderCtrl.registerB2BOrder("20-05-2022", 123456789);
		
		// Act
//		orderCtrl.addPackage("1");
		
		//Assert
		
		DataAccessException thrown = assertThrows(DataAccessException.class, () -> {
			orderCtrl.addPackage("1234563fafa46");
		}, "NullPointerException error was expected");
			
		assertEquals("DataAccessException error was expected", thrown.getMessage());


	}
	
}
