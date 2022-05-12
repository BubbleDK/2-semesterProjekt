package model;

public abstract class Person {
	private String name;
	private String address;
	private int zipCode;
	private int phoneNo;
	private String email;
	private String city;
	
	public Person(String name, String address, int zipCode, int phoneNo, String email, String city) {
		this.name = name;
		this.address = address;
		this.zipCode = zipCode;
		this.phoneNo = phoneNo;
		this.email = email;
		this.city = city;
		
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

}
