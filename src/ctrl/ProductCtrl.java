package ctrl;

import db.ProductDB;
import db.ProductDBIF;
import exceptions.DataAccessException;
import model.AbstractProduct;
import model.Product;

public class ProductCtrl {

	private ProductDBIF productDB;

	public ProductCtrl() throws DataAccessException {
		this.productDB = new ProductDB();
	}
	
	public AbstractProduct findProduct(String barcode) throws DataAccessException {
		AbstractProduct product = productDB.findByProductBarcode(barcode);
		return product;
	}
}
