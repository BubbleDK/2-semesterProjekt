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
	private CustomerDB CDB;
	private HashMap<String, String> EGN;
	private static final String INSERT_INTO_ORDERLINE_Q = "insert into kk_OrderLines (orderID, productID, quantity, type) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderLinePS;
	private static final String INSERT_INTO_ORDER_Q = "insert into kk_Orders (type, orderNo, customerID, employeeID) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderPS;
	private static final String INSERT_INTO_B2BLOGIN_Q = "insert into kk_B2BLogin (giftNO, email, orderID) values (?, ?, ?)";
	private PreparedStatement insertB2bLoginPS;
	private static final String FIND_CUSTOMERID_Q = "select * from kk_B2BCustomer WHERE cvr = ?";
	private PreparedStatement findCustomerIDPS;
	private static final String FIND_PRODUCTID_Q = "select id from kk_AbstractProduct WHERE barcode = ?";
	private PreparedStatement findProductIDPS;
	private static final String FIND_BY_ORDERNO_Q = "SELECT * FROM kk_Orders WHERE OrderNo = ?";
	private static PreparedStatement findByOrderNoPS;
	private static final String FIND_BY_COMPANYNAME_Q = "SELECT * FROM kk_Orders RIGHT OUTER JOIN kk_B2BCustomer ON kk_orders.customerID = kk_B2BCustomer.id and kk_B2BCustomer.companyName = ?";
	private static PreparedStatement findOrderByCompanyNamePS;
	
	public OrderDB() throws DataAccessException  {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertOrderLinePS = con.prepareStatement(INSERT_INTO_ORDERLINE_Q);
			insertOrderPS = con.prepareStatement(INSERT_INTO_ORDER_Q,PreparedStatement.RETURN_GENERATED_KEYS);
			findCustomerIDPS = con.prepareStatement(FIND_CUSTOMERID_Q);
			findByOrderNoPS = con.prepareStatement(FIND_BY_ORDERNO_Q);
			findProductIDPS = con.prepareStatement(FIND_PRODUCTID_Q);
			insertB2bLoginPS = con.prepareStatement(INSERT_INTO_B2BLOGIN_Q);
			findOrderByCompanyNamePS = con.prepareStatement(FIND_BY_COMPANYNAME_Q);
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
			//TODO: tilføj endDate
			
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
//			int orderLinesID = DBConnection.getInstance().executeInsertWithIdentity(insertOrderLinePS);
			// Save b2b login
			//TODO Skal vi have sat orderlines her? Er det ikke først når man logger ind og vælger noget? Executeinsertwithidentity overover mangler reutngeneratedkeys under prepare statement
			for (String login : order.getEmailGiftNo().keySet()) {
				insertB2bLoginPS.setString(1, order.getEmailGiftNo().get(login));
				insertB2bLoginPS.setString(2, login);
				insertB2bLoginPS.setInt(3, orderID);
//				insertB2bLoginPS.setInt(4, orderLinesID);
				insertB2bLoginPS.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
		}
		return order;
	}
	
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
	
	public B2BOrder findOrderByCompanyName(String companyName) throws DataAccessException {
		currOrder = null;
		try {
			//TODO: Søg i et join hvor companyName = companyName og hvor orders.customerID = customer.id
			findOrderByCompanyNamePS.setString(1, companyName);
			ResultSet rs = findOrderByCompanyNamePS.executeQuery();
			currOrder = buildOrderObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return currOrder;
	}


	private B2BOrder buildOrderObject(ResultSet rs) throws DataAccessException {
		currOrder = new B2BOrder();
		try {
			if(rs.next()) {
				currOrder.setEndDate(rs.getString("endDate"));
				currOrder.setOrderLines(buildOrderLineObject(rs));
				currOrder.setCustomer(CDB.findB2BCustomer(rs.getInt("customerID")));
				currOrder.setEmailGiftNo(buildEmailGiftObject(rs));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currOrder;
	}
	
	private HashMap<String,String> buildEmailGiftObject(ResultSet rs) throws DataAccessException {
		EGN = new HashMap<String, String>();
		
		try {
			if(rs.next()) {
				EGN.put(rs.getString("Email"), rs.getString("GiftNo"));
			}
		}catch(SQLException e){
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		
		return null;
	}

	public B2BOrderLine buildOrderLineObject(ResultSet rs) throws DataAccessException {
		B2BOrderLine currOL = new B2BOrderLine();
		try {
			if(rs.next()) {
				currOL.setProduct((AbstractProduct) rs.getObject("product"));
				currOL.setQuantity(rs.getInt("quantity"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
//			e.printStackTrace();
		}
		return currOL;
	}

}
