package model;

import java.util.ArrayList;

public abstract class AbstractProduct {
	private String name;
	private String barcode;
	private String productDescription;
	protected int stock;
	private double priceInsert;
	private ArrayList<Price> prices;
	
	public AbstractProduct(String name, String barcode, String productDescription, int stock, double priceInsert) {
		this.name = name;
		this.barcode = barcode;
		this.productDescription = productDescription;
		this.stock = stock;
		Price price = new Price(priceInsert);
		prices.add(price);
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
	
	public String getBarcode() {
		return this.barcode;
	}
		
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public double getPrice() {
		return prices.get(prices.size()-1).getPrice();
	}
	
	public double getPrice(String date) {
		for(Price p: prices) {
			if(p.getDate().toString().equals(date)) {
				return p.getPrice();
			}
		}
		return -1d;
	}

	public abstract void updateStock(int quantity);

	
	public abstract void removeStock(int quantity);

}
	
