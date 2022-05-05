package ctrl;

import db.ProductDB;
import db.ProductDBIF;
import exceptions.DataAccessException;
import model.Product;

public class ProductCtrl {

	private ProductDBIF productDB;

	public ProductCtrl() throws DataAccessException {
		this.productDB = new ProductDB();
	}
	
	public Product findProduct(String barcode) throws DataAccessException {
		Product product = productDB.findByProductBarcode(barcode);
		return product;
	}
}
