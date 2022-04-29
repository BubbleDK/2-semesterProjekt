package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exceptions.DataAccessException;
import model.B2BOrder;

public class OrderDB implements OrderDBIF {
	private static final String INSERT_INTO_ORDERLINE_Q = "insert into kk_OrderLines (orderID, productID, quantity, type) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderLinePS;
	private static final String INSERT_INTO_ORDER_Q = "insert into kk_Orders (type, customerID, employeeID) values (?, ?, ?, ?)";
	private PreparedStatement insertOrderPS;
	private static final String FIND_CUSTOMERID_Q = "select personid from kk_B2BCustomer WHERE cvr = ?";
	private PreparedStatement findCustomerIDPS;
	
	public OrderDB() throws DataAccessException  {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertOrderLinePS = con.prepareStatement(INSERT_INTO_ORDERLINE_Q);
			insertOrderPS = con.prepareStatement(INSERT_INTO_ORDER_Q,PreparedStatement.RETURN_GENERATED_KEYS);
			findCustomerIDPS = con.prepareStatement(FIND_CUSTOMERID_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	
	//TODO der skal lige gøres noget i forhold til id'erne. O find employeeid. Den har ikke noget at søge efter.
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
//			findEmployeeIDPS.setInt(1,order.getEmployee().get)
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
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
		}
		return null;
	}

}
