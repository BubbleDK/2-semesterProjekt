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
	
	public void addItem(AbstractProduct product, int quantity) {
		if (product != null && quantity > 0) {
			packlines.add(new PackLine(product, quantity));
		}
	}
	@Override
	public void updateStock(int quantity) {
		for (int i = 0; i < packlines.size(); i++) {
			packlines.get(i).getPackLineProduct().updateStock(quantity * packlines.get(i).getQuantity());
		}
	}
	
	@Override
	public void removeStock(int quantity) {
		for (int i = 0; i < packlines.size(); i++) {
			packlines.get(i).getPackLineProduct().removeStock(quantity * packlines.get(i).getQuantity());
		}
	}
}
