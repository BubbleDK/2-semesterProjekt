package db;

import exceptions.DataAccessException;
import model.B2BOrder;

public interface OrderDBIF {
	public B2BOrder saveOrderToDB(B2BOrder order) throws DataAccessException;

	public B2BOrder findOrderByCompanyName(String companyName) throws DataAccessException;
}
