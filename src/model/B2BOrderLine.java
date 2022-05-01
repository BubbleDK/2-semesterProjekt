package model;

public class B2BOrderLine {
	private AbstractProduct product;
	private int quantity;
	public B2BOrderLine(AbstractProduct product) {
		this.product = product;
		this.quantity = 0;
	}
	
	public void addLogin(String giftNo) {
		
	}
	
	public int getQuantity() {
		return quantity;
	}

	public AbstractProduct getProduct() {
		return product;
	}

	public void setP(AbstractProduct product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
