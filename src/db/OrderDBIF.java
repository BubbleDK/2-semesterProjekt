package db;

import model.Order;

public interface OrderDBIF {
	public Order saveOrderToDB(Order order);
}
