package tests;

import java.sql.Connection;

import ctrl.CustomerCtrl;
import ctrl.OrderCtrl;
import ctrl.ProductCtrl;
import db.DBConnection;
import db.ProductDB;
import exceptions.DataAccessException;
import model.AbstractProduct;
import model.B2BOrder;
import model.Pack;

public class TryMe {
public static void main(String[] args) throws DataAccessException {
	// Arrange
			Connection con = DBConnection.getInstance().getConnection();
			OrderCtrl orderCtrl = new OrderCtrl();
			CustomerCtrl customerCtrl = new CustomerCtrl();
			ProductCtrl productCtrl = new ProductCtrl();
			ProductDB productDB = new ProductDB();
			B2BOrder o = orderCtrl.registerB2BOrder("20-05-2022", 123456789);
			
			// Act
			orderCtrl.addPackage("err414124");
			Pack p = productCtrl.findPack("err414124");
			productDB.findByProductBarcode("err414124");
			System.out.println(o);
}
}
