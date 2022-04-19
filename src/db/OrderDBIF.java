package db;

import model.B2BOrder;

public interface OrderDBIF {
	public B2BOrder saveOrderToDB(B2BOrder order);
}
