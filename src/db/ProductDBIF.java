package db;

import model.Product;

public interface ProductDBIF {
	Product findByProductBarcode(String barcode);
}
