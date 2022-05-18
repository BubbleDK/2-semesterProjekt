package db;

import java.sql.SQLException;

import exceptions.DataAccessException;
import model.AbstractProduct;
import model.Pack;
import model.Product;

public interface ProductDBIF {
	Pack findByProductBarcode(String barcode) throws DataAccessException;
	
	Pack findByProductId(int id) throws DataAccessException;

	public int findProductIdByBarcode(String barcode) throws SQLException, DataAccessException;

	public void updateStockByBarcode(String barcode) throws SQLException;
}
