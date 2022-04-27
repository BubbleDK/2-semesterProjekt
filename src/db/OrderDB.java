package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exceptions.DataAccessException;
import model.B2BOrder;

public class OrderDB implements OrderDBIF {
	private static final String INSERT_INTO_ORDERLINE_Q = "insert into kk_OrderLines (productID, quantity, type) values (?, ?, ?)";
	private PreparedStatement insertOrderLinePS;
	private static final String INSERT_INTO_ORDER_Q = "insert into kk_Orders (type, customerID, employeeID) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderPS;
	private static final String FIND_CUSTOMERID_Q = "select personid from kk_B2BCustomer WHERE cvr = ?";
	private PreparedStatement findCustomerIDPS;
	private static final String FIND_EMPLOYEEID_Q = "select personid from kk_Employee WHERE  = ?";
	private PreparedStatement findEmployeeIDPS;
	
	public OrderDB() throws DataAccessException  {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertOrderLinePS = con.prepareStatement(INSERT_INTO_ORDERLINE_Q);
			insertOrderPS = con.prepareStatement(INSERT_INTO_ORDER_Q);
			findCustomerIDPS = con.prepareStatement(FIND_CUSTOMERID_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	
	//Huske at denne også skal have save o Orderlines
	@Override
	public B2BOrder saveOrderToDB(B2BOrder order) {
		int customerID = -1;
		int employeeID = -1;
		try {
			findCustomerIDPS.setInt(1, order.getB2BCustomer().getCVR());
			ResultSet rsCustomer = findCustomerIDPS.executeQuery();
			if(rsCustomer.next()) {
				customerID = rsCustomer.getInt(1);
			}
//			findEmployeeIDPS.setInt(1,order.getEmployee().get)
			insertOrderPS.setString(1, "pack");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
