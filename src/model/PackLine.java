package model;

public class PackLine {
	private AbstractProduct p;
	private int quantity;

	public PackLine(AbstractProduct p, int quantity) {
		this.p = p;
		this.quantity = quantity;
	}
	
	public AbstractProduct getPackLineProduct() {
		return p;
		
	}

	public int getQuantity() {
		
		return quantity;
	}

}
