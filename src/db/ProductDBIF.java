package db;

import expections.DataAccessException;
import model.Product;

public interface ProductDBIF {
	Product findByProductBarcode(String barcode) throws DataAccessException;
}
