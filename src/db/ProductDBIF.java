package db;

import exceptions.DataAccessException;
import model.AbstractProduct;
import model.Pack;
import model.Product;

public interface ProductDBIF {
	Pack findByProductBarcode(String barcode) throws DataAccessException;
	
	Pack findByProductId(int id) throws DataAccessException;
}
