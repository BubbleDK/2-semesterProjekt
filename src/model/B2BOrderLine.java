package model;

public class B2BOrderLine {
	private Pack product;
	private int quantity;
	public B2BOrderLine(Pack product) {
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

	public Pack getProduct() {
		return product;
	}

	public void setProduct(Pack product) {
		this.product = product;
	}
}
