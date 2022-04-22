package ctrl;

import db.ProductDB;
import expections.DataAccessException;
import model.Product;

public class ProductCtrl {

	private ProductDB productDB;

	public ProductCtrl() {
		this.productDB = new ProductDB();
	}
	
	public Product findProduct(String barcode) throws DataAccessException {
		Product product = productDB.findByProductBarcode(barcode);
		return product;
	}
}
