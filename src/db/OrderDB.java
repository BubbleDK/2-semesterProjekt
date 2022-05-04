package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.DataAccessException;
import model.B2BCustomer;
import model.B2BOrder;

public class OrderDB implements OrderDBIF {
	private B2BOrder currOrder;
	private static final String INSERT_INTO_ORDERLINE_Q = "insert into kk_OrderLines (orderID, productID, quantity, type) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderLinePS;
	private static final String INSERT_INTO_ORDER_Q = "insert into kk_Orders (type, customerID, employeeID) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderPS;
	private static final String FIND_CUSTOMERID_Q = "select personid from kk_B2BCustomer WHERE cvr = ?";
	private PreparedStatement findCustomerIDPS;
	private static final String FIND_BY_ORDERNO_Q = "SELECT * FROM kk_Orders WHERE OrderNo = ?";
	private static PreparedStatement findByOrderNoPS;
	
	public OrderDB() throws DataAccessException  {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertOrderLinePS = con.prepareStatement(INSERT_INTO_ORDERLINE_Q);
			insertOrderPS = con.prepareStatement(INSERT_INTO_ORDER_Q,PreparedStatement.RETURN_GENERATED_KEYS);
			findCustomerIDPS = con.prepareStatement(FIND_CUSTOMERID_Q);
			findByOrderNoPS = con.prepareStatement(FIND_BY_ORDERNO_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	

	@Override
	public B2BOrder saveOrderToDB(B2BOrder order) throws DataAccessException {
		int customerID = -1;
		int employeeID = 1;
		int productID = -1;
		try {
			findCustomerIDPS.setInt(1, order.getB2BCustomer().getCVR());
			ResultSet rsCustomer = findCustomerIDPS.executeQuery();
			if(rsCustomer.next()) {
				customerID = rsCustomer.getInt(1);
			}
			//insert into Order
			insertOrderPS.setString(1, "pack");
			insertOrderPS.setInt(2, customerID);
			insertOrderPS.setInt(3, employeeID);
			
			//Insert into orderlines
			int orderID = DBConnection.getInstance().executeInsertWithIdentity(insertOrderPS);
			for(int i = 0; i < order.getOrderLines().size(); i++) {
				insertOrderLinePS.setInt(1, orderID);
				insertOrderLinePS.setInt(2, productID);
				insertOrderLinePS.setInt(3, order.getOrderLines().get(i).getQuantity());
				insertOrderLinePS.setString(4, "pack");
				insertOrderLinePS.executeQuery();
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
		}
		return null;
	}
	//TODO: Ændre metode navn
	public B2BOrder findByOrderNo(int orderNo, String endDate, B2BCustomer c) throws DataAccessException {
		currOrder = null;
		try {
			findByOrderNoPS.setInt(1, orderNo);
			ResultSet rs = findByOrderNoPS.executeQuery();
			buildPackObject(rs, endDate, c);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return null;
	}
	
	private B2BOrder buildPackObject(ResultSet rs, String endDate, B2BCustomer c) throws DataAccessException {
		currOrder = new B2BOrder(endDate,c);
		try {
			//TODO:Hvordan bruger jeg settere på flere ting?
			if(rs.next()) {
				currOrder.setEndDate(rs.getString("endDate"));
				currOrder.setOrderLines((ArrayList) rs.getArray("OrderLines"));
				currOrder.setCustomer(rs.getString("customerID"));
				currOrder.setEmailGiftNo(rs.getString("price"));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currOrder;
	}

}
