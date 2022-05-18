package db;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataAccessException;
import model.B2BOrder;
import model.B2BOrderLine;

public interface OrderDBIF {
	public B2BOrder saveOrderToDB(B2BOrder order) throws DataAccessException;

	public B2BOrder findOrderBylogin(String giftNo) throws DataAccessException;

	public void saveChoice(int orderId, int productId, String giftNo, List<B2BOrderLine> orderLines) throws DataAccessException, SQLException;

	public int findLoginByGiftNo(String giftNo) throws SQLException;
}
