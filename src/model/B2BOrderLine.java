package model;

public class B2BOrderLine {
	private AbstractProduct product;
	private int quantity;
	public B2BOrderLine(AbstractProduct product) {
		this.product = product;
		this.quantity = 0;
	}
	
	public B2BOrderLine() {
		
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public AbstractProduct getProduct() {
		return product;
	}

	public void setProduct(AbstractProduct product) {
		this.product = product;
	}
}
