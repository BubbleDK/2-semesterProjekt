package db;

import java.sql.PreparedStatement;

import model.B2BOrder;

public class OrderDB implements OrderDBIF {
	private static final String INSERT_INTO_ORDERLINE_Q = "";
	private PreparedStatement insertOrderLinePS;
	private static final String INSERT_INTO_ORDER_Q = "";
	private PreparedStatement insertOrderPS;

	@Override
	public B2BOrder saveOrderToDB(B2BOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

}
