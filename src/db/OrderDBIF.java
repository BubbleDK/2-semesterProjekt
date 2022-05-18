package db;

import java.sql.SQLException;

import exceptions.DataAccessException;
import model.B2BOrder;

public interface OrderDBIF {
	public B2BOrder saveOrderToDB(B2BOrder order) throws DataAccessException;

	public B2BOrder findOrderBylogin(String giftNo) throws DataAccessException;

	public void saveChoice(int orderId, int productId, String giftNo) throws DataAccessException, SQLException;
}
