package ctrl;

import java.sql.SQLException;
import java.util.List;

import db.OrderDB;
import db.OrderDBIF;
import db.ProductDB;
import exceptions.DataAccessException;
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
		orderDB = new OrderDB();
	}
	
	public B2BOrder registerB2BOrderChoice(String giftNo) throws DataAccessException, SQLException {
		int orderLineId = orderDB.findLoginByGiftNo(giftNo);
		System.out.println("ORDERLINEID::::" + orderLineId);
		if(orderLineId == 0) {
		o = orderDB.findOrderBylogin(giftNo);
		return o;
		}
		return null;
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
		
		orderDB.saveOrderToDB(o);
		}else {
			return null;
		}
		return o;
	}
	
	public B2BOrder getOrder() {
		return this.o;
	}
		
	public void choosePack(String barcode) {
		o.choosePack(barcode);
	}

	public void saveChoice(String giftNo, String barcodeCheck) throws DataAccessException, SQLException {
		String barcode = "";
		int productId = -1;
		int orderId = o.getOrderId();
		for(int i = 0; i < this.o.getOrderLines().size(); i++) {
			if(this.o.getOrderLines().get(i).getProduct().getBarcode().equals(barcodeCheck)) {
				barcode = barcodeCheck;
				productId = productCtrl.findProductIdByBarcode(barcode);
			}
		}
		orderDB.saveChoice(orderId, productId, giftNo, o.getOrderLines());
//		productCtrl.updateStock(productId);
	}
	
	
}
