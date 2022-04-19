package model;

public class B2BOrderLine {
	private Product p;
	private int quantity;
	public B2BOrderLine(Product p, int quantity) {
		this.p = p;
		this.quantity = quantity;
	}
	
	public void addLogin(String giftNo) {
		
	}
	
	public int getQuantity() {
		return quantity;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
