package db;

import java.sql.PreparedStatement;

import model.Order;

public class OrderDB implements OrderDBIF {
	private static final String INSERT_INTO_ORDERLINE_Q = "";
	private PreparedStatement insertOrderLinePS;
	private static final String INSERT_INTO_ORDER_Q = "";
	private PreparedStatement insertOrderPS;

	@Override
	public Order saveOrderToDB(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}
