package ctrl;

import db.OrderDB;
import db.OrderDBIF;
import exceptions.DataAccessException;
import model.AbstractProduct;
import model.B2BCustomer;
import model.B2BOrder;
import model.B2BOrderLine;

public class OrderCtrl {
	
	private ProductCtrl productCtrl;
	private CustomerCtrl customerCtrl;
	private B2BOrder o;
	private OrderDBIF orderDB;
	

	public OrderCtrl() throws DataAccessException {
		productCtrl = new ProductCtrl();
		customerCtrl = new CustomerCtrl();
	}

	
	public B2BOrder registerB2BOrder(String endDate, int cvr) throws DataAccessException {
		B2BCustomer c = customerCtrl.findB2BCustomer(cvr);
		o = new B2BOrder(endDate,c);
		return o;
	}
	
	public void addPackage(String barcode) throws DataAccessException {
		//TODO: check for gentagelser i produkter/ordrelinjer DER MÅ IKKE VÆRE FLERE ENS
		AbstractProduct p = productCtrl.findProduct(barcode);
		B2BOrderLine ol = new B2BOrderLine(p);
		o.addOrderLine(ol);
		
	}
	
	public void addB2BEmployee(String email) throws DataAccessException {
		o.addB2BEmployee(email);
	}
	
	public B2BOrder endOrder() throws DataAccessException {
		orderDB = new OrderDB();
		orderDB.saveOrderToDB(o);
		return o;
	}
}
