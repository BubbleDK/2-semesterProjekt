package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.DataAccessException;
import model.B2BOrder;

public class OrderDB implements OrderDBIF {
	private static final String INSERT_INTO_ORDERLINE_Q = "insert into kk_OrderLines (productID, quantity, type) values (?, ?, ?)";
	private PreparedStatement insertOrderLinePS;
	private static final String INSERT_INTO_ORDER_Q = "insert into kk_Orders (type, customerID, employeeID) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderPS;
	
	public OrderDB() throws DataAccessException  {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertOrderLinePS = con.prepareStatement(INSERT_INTO_ORDERLINE_Q);
			insertOrderPS = con.prepareStatement(INSERT_INTO_ORDER_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	
	//Huske at denne også skal have save o Orderlines
	@Override
	public B2BOrder saveOrderToDB(B2BOrder order) {
		try {
			insertOrderPS.setString(1, "pack");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
