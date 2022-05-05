package db;

import exceptions.DataAccessException;
import model.AbstractProduct;
import model.Product;

public interface ProductDBIF {
	AbstractProduct findByProductBarcode(String barcode) throws DataAccessException;
}
