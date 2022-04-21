package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Product;

public class ProductDB implements ProductDBIF {
	private Product currProduct;
	private static final String FIND_BY_BARCODE_Q = "";
	private static PreparedStatement findByBarcodePS;

	@Override
	public Product findByProductBarcode(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Product buildPackObject(ResultSet rs, String barcode) {
		Product res = null;
		
		return res;
	}

}
