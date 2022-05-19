package model;

import java.util.ArrayList;

public abstract class AbstractProduct {
	private String name;
	private String barcode;
	private String productDescription;
	private int stock;
	private ArrayList<Price> prices;
	
	public AbstractProduct(String name, String barcode, String productDescription, int stock, double priceInsert) {
		this.name = name;
		this.barcode = barcode;
		this.productDescription = productDescription;
		this.stock = stock;
		Price price = new Price(priceInsert);
		this.prices = new ArrayList<Price>();
		this.prices.add(price);
	}
	
	public AbstractProduct() {
		this.prices = new ArrayList<Price>();
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getBarcode() {
		return this.barcode;
	}
		
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getStock() {
		return stock;
	}
	
	public double getPrice() {
		return prices.get(prices.size()-1).getPrice();
	}
	
	public void setPrice(Price price) {
		this.prices.add(price);
	}
	
	public abstract void addStock(int quantity);

	
	public abstract void removeStock(int quantity);

}
	
