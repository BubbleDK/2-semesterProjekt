package model;

public abstract class AbstractProduct {
	private String name;
	private String barcode;
	private String productDescription;
	protected int stock;
	private double price;
	public AbstractProduct(String name, String barcode, String productDescription, int stock, double price) {
		this.name = name;
		this.barcode = barcode;
		this.productDescription = productDescription;
		this.stock = stock;
		this.price = price;
	}
	
	public AbstractProduct() {
		
	}

	public abstract Product findProduct(String barcode);

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}

	public abstract void updateStock(int quantity);

	
	public abstract void removeStock(int quantity);
		
	}
	
