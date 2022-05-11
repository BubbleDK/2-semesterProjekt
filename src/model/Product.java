package model;

public class Product extends AbstractProduct{
	private String brand;

	public Product(String name, String barcode, String productDescription, int stock, double price, String brand) {
		super(name, barcode, productDescription, stock, price);
		this.brand = brand;
	}

	@Override
	public void addStock(int quantity) {
		setStock(getStock() + quantity);
		
	}

	@Override
	public void removeStock(int quantity) {
		setStock(getStock() - quantity);
	}

}
