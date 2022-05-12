package model;

public class B2BCustomer extends Person {
	private String companyName;
	private int cvr;
	
	public B2BCustomer(String name, String address, int zipCode, int phoneNo, String email) {
		super(name, address, zipCode, phoneNo, email);
	}

	public B2BCustomer() {
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCvr(int cvr) {
		this.cvr = cvr;
	}

	public int getCVR() {
		return cvr;
	}
	
	public String getCompanyName() {
		return companyName;
	}
}
