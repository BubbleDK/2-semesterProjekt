package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import exceptions.DataAccessException;
import model.AbstractProduct;
import model.B2BCustomer;
import model.B2BOrder;
import model.B2BOrderLine;

public class OrderDB implements OrderDBIF {
	private B2BOrder currOrder;
	private CustomerDBIF customerDB;
	private HashMap<String, String> emailGiftNo;
	private static final String INSERT_INTO_ORDERLINE_Q = "insert into kk_OrderLines (orderID, productID, quantity, type) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderLinePS;
	private static final String INSERT_INTO_ORDER_Q = "insert into kk_Orders (type, orderNo, customerID, employeeID, enddate) values (?, ?, ?, ?, ?)";
	private PreparedStatement insertOrderPS;
	private static final String INSERT_INTO_B2BLOGIN_Q = "insert into kk_B2BLogin (giftNO, email, orderID) values (?, ?, ?)";
	private PreparedStatement insertB2bLoginPS;
	private static final String FIND_CUSTOMERID_Q = "select * from kk_B2BCustomer WHERE cvr = ?";
	private PreparedStatement findCustomerIDPS;
	private static final String FIND_PRODUCTID_Q = "select id from kk_AbstractProduct WHERE barcode = ?";
	private PreparedStatement findProductIDPS;
	private static final String FIND_BY_ORDERNO_Q = "SELECT * FROM kk_Orders WHERE OrderNo = ?";
	private static PreparedStatement findByOrderNoPS;
	private static final String FIND_BY_LOGIN_Q = "SELECT * FROM kk_Orders INNER JOIN kk_B2BLogin ON kk_orders.id = kk_B2BLogin.orderid and kk_B2BLogin.giftNo = ?"
			+ "INNER JOIN kk_OrderLines ON kk_OrderLines.orderID = kk_Orders.id";
	private static PreparedStatement findOrderByLoginPS;
	
	public OrderDB() throws DataAccessException  {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertOrderLinePS = con.prepareStatement(INSERT_INTO_ORDERLINE_Q);
			insertOrderPS = con.prepareStatement(INSERT_INTO_ORDER_Q,PreparedStatement.RETURN_GENERATED_KEYS);
			findCustomerIDPS = con.prepareStatement(FIND_CUSTOMERID_Q);
			findByOrderNoPS = con.prepareStatement(FIND_BY_ORDERNO_Q);
			findProductIDPS = con.prepareStatement(FIND_PRODUCTID_Q);
			insertB2bLoginPS = con.prepareStatement(INSERT_INTO_B2BLOGIN_Q);
			findOrderByLoginPS = con.prepareStatement(FIND_BY_LOGIN_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	@Override
	public B2BOrder saveOrderToDB(B2BOrder order) throws DataAccessException {
		int customerID = -1;
		int orderNo = 0;
		int employeeID = 1;
		int productID = -1;
		try {
			findCustomerIDPS.setInt(1, order.getB2BCustomer().getCVR());
			ResultSet rsCustomer = findCustomerIDPS.executeQuery();
			if(rsCustomer.next()) {
				customerID = rsCustomer.getInt(1);
			}
			//insert into Order
			insertOrderPS.setString(1, "B2B");
			orderNo = order.newOrderNo();
			insertOrderPS.setInt(2, orderNo);
			insertOrderPS.setInt(3, customerID);
			insertOrderPS.setInt(4, employeeID);
			insertOrderPS.setString(5, order.getEndDate());
			
			//Insert into orderlines
			int orderID = DBConnection.getInstance().executeInsertWithIdentity(insertOrderPS);
			for(int i = 0; i < order.getOrderLines().size(); i++) {
				findProductIDPS.setString(1, order.getOrderLines().get(i).getProduct().getBarcode());
				ResultSet rsProduct = findProductIDPS.executeQuery();
				if(rsProduct.next()) {
					productID = rsProduct.getInt(1);
				}
				insertOrderLinePS.setInt(1, orderID);
				insertOrderLinePS.setInt(2, productID);
				insertOrderLinePS.setInt(3, order.getOrderLines().get(i).getQuantity());
				insertOrderLinePS.setString(4, "pack");
				insertOrderLinePS.executeUpdate();
			}
			// Save b2b login
			for (String login : order.getEmailGiftNo().keySet()) {
				insertB2bLoginPS.setString(1, order.getEmailGiftNo().get(login));
				insertB2bLoginPS.setString(2, login);
				insertB2bLoginPS.setInt(3, orderID);
				insertB2bLoginPS.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
		}
		return order;
	}
	//TODO: Skal denne returnere NULL? Skal currOrder bruges?
	public B2BOrder findByOrderNo(int orderNo) throws DataAccessException {
		currOrder = null;
		try {
			findByOrderNoPS.setInt(1, orderNo);
			ResultSet rs = findByOrderNoPS.executeQuery();
			buildOrderObject(rs);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return null;
	}
	
	@Override
	public B2BOrder findOrderBylogin(String giftNo) throws DataAccessException {
		currOrder = null;
		try {
			findOrderByLoginPS.setString(1, giftNo);
			ResultSet rs = findOrderByLoginPS.executeQuery();
			if(rs.next()) {
			currOrder = buildOrderObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return currOrder;
	}

	private B2BOrder buildOrderObject(ResultSet rs) throws DataAccessException {
		currOrder = new B2BOrder();
		try {
				currOrder.setEndDate(rs.getString("endDate"));
				currOrder.setCustomer(customerDB.findB2BCustomerByID(rs.getInt("customerID")));
				currOrder.setEmailGiftNo(buildEmailGiftObject(rs));
				currOrder.setOrderLines(buildOrderLineObject(rs));
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currOrder;
	}
	
	private HashMap<String,String> buildEmailGiftObject(ResultSet rs) throws DataAccessException {
		emailGiftNo = new HashMap<String, String>();
		
		try {
			if(rs.next()) {
				emailGiftNo.put(rs.getString("Email"), rs.getString("GiftNo"));
			}
		}catch(SQLException e){
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return null;
	}

	public B2BOrderLine buildOrderLineObject(ResultSet rs) throws DataAccessException {
		B2BOrderLine currOrderLine = new B2BOrderLine();
		try {
			if(rs.next()) {
				currOrderLine.setProduct((AbstractProduct) rs.getObject("product"));
				currOrderLine.setQuantity(rs.getInt("quantity"));
				
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
//			e.printStackTrace();
		}
		return currOrderLine;
	}
}
