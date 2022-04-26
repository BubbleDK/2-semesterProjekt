package model;

import java.util.LinkedList;
import java.util.List;

public class Pack extends AbstractProduct {
	
	private List<PackLine> pl;

	public Pack(String name, String barcode, String productDescription, int stock, double price) {
		super(name, barcode, productDescription, stock, price);
		pl = new LinkedList<>();
	}

	public Pack() {
	}
	
	public void addItem(AbstractProduct product, int quantity) {
		if (product != null && quantity > 0) {
			pl.add(new PackLine(product, quantity));
		}
	}
	@Override
	public void updateStock(int quantity) {
		for (int i = 0; i < pl.size(); i++) {
			pl.get(i).getPackLineProduct().updateStock(quantity * pl.get(i).getQuantity());
		}
	}
	
	@Override
	public void removeStock(int quantity) {
		for (int i = 0; i < pl.size(); i++) {
			pl.get(i).getPackLineProduct().removeStock(quantity * pl.get(i).getQuantity());
		}
	}

	@Override
	public Product findProduct(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
