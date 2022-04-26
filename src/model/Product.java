package model;

public class Product extends AbstractProduct{
	private String brand;

	public Product(String name, String barcode, String productDescription, int stock, double price, String brand) {
		super(name, barcode, productDescription, stock, price);
		this.brand = brand;
	}

	@Override
	public Product findProduct(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStock(int quantity) {
		this.stock += quantity;
		
	}

	@Override
	public void removeStock(int quantity) {
		this.stock -= quantity;
		
	}

}
