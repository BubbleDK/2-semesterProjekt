package model;

import java.util.LinkedList;
import java.util.List;

public class Pack extends AbstractProduct {
	
	private List<PackLine> packlines;

	public Pack(String name, String barcode, String productDescription, int stock, double price) {
		super(name, barcode, productDescription, stock, price);
		packlines = new LinkedList<>();
	}

	public Pack() {
		super();
	}
	
	@Override
	public void addStock(int quantity) {
		
	}
	
	@Override
	public void removeStock(int quantity) {
		
	}
}
