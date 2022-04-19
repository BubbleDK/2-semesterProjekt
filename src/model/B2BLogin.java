package model;

public class B2BLogin {
	
	private String email;
	private String giftNo;
	
	public B2BLogin(String email) {
		this.email = email;
	}
	
	private void createRandomGiftNo() {
		
	}

	public String getEmail() {
		return email;
	}

	public String getGiftNo() {
		return giftNo;
	}

}
