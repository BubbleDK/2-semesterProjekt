package model;

public class B2BOrderLine {
	private AbstractProduct p;
	private int quantity;
	public B2BOrderLine(AbstractProduct p) {
		this.p = p;
		this.quantity = 0;
	}
	
	public void addLogin(String giftNo) {
		
	}
	
	public int getQuantity() {
		return quantity;
	}

	public AbstractProduct getP() {
		return p;
	}

	public void setP(AbstractProduct p) {
		this.p = p;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
