package ctrl;

import db.ProductDB;
import model.Product;

public class ProductCtrl {

	private ProductDB productDB;

	public ProductCtrl() {
		this.productDB = new ProductDB();
	}
	
	public Product findProduct(String barcode) {
		Product product = productDB.findByProductBarcode(barcode);
		return product;
	}
}
