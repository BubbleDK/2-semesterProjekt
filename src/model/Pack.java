package model;

import java.util.LinkedList;
import java.util.List;

import model.PackLine;

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
	public Product findProduct(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

}
