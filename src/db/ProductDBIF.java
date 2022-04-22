package db;

import exceptions.DataAccessException;
import model.Product;

public interface ProductDBIF {
	Product findByProductBarcode(String barcode) throws DataAccessException;
}
