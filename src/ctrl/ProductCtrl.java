package ctrl;

import db.ProductDB;
import db.ProductDBIF;
import exceptions.DataAccessException;
import model.AbstractProduct;
import model.Pack;
import model.Product;

public class ProductCtrl {

	private ProductDBIF productDB;

	public ProductCtrl() throws DataAccessException {
		this.productDB = new ProductDB();
	}
	
	public Pack findPack(String barcode) throws DataAccessException {
		Pack p = productDB.findByProductBarcode(barcode);
		return p;
	}
	
	
}
