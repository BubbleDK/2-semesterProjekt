package db;

import model.Product;

public interface ProductDBIF {

	public Product findByProductID(int productID);
}
