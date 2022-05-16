package ctrl;

import db.OrderDB;
import db.OrderDBIF;
import exceptions.DataAccessException;
import model.AbstractProduct;
import model.B2BCustomer;
import model.B2BOrder;
import model.B2BOrderLine;
import model.Pack;

public class OrderCtrl {
	
	private ProductCtrl productCtrl;
	private CustomerCtrl customerCtrl;
	private B2BOrder o;
	private OrderDBIF orderDB;
	

	public OrderCtrl() throws DataAccessException {
		productCtrl = new ProductCtrl();
		customerCtrl = new CustomerCtrl();
	}
	
	public void registerB2BOrderChoice(String giftNo) {
		//TODO: Lav et join som viser Orders sammen med B2BCustomer eller Person og måske orderlines med?
		try {
			o = orderDB.findOrderBylogin(giftNo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public B2BOrder registerB2BOrder(String endDate, int cvr) throws DataAccessException {
		B2BCustomer c = customerCtrl.findB2BCustomer(cvr);
		o = new B2BOrder(endDate,c);
		return o;
	}
	
	public B2BOrderLine addPackage(String barcode) throws DataAccessException {
		boolean productAlreadyExists = false;
		Pack p = productCtrl.findPack(barcode);
		for(B2BOrderLine ol : o.getOrderLines()) {
			if(ol.getProduct().getBarcode().equals(p.getBarcode())) {
				productAlreadyExists = true;
			}
		}
		if(productAlreadyExists == false) {
			B2BOrderLine ol = new B2BOrderLine(p);
			o.addOrderLine(ol);
			return ol;
		}else{
			return null;
		}
	}
	
	public boolean addB2BLogin(String email) throws DataAccessException {
		return o.addB2BLogin(email);
	}
	
	public B2BOrder endOrder() throws DataAccessException {
		if(o.getOrderLines().size() > 0 && o.getEmailGiftNo().size() > 0) {
		orderDB = new OrderDB();
		orderDB.saveOrderToDB(o);
		}else {
			return null;
		}
		return o;
	}
	
	public B2BOrder getOrder() {
		return this.o;
	}
}
