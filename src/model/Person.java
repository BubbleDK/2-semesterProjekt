package model;

public abstract class Person {
	private String name;
	private String address;
	private int zipCode;
	private int phoneNo;
	
	public Person(String name, String address, int zipCode, int phoneNo) {
		this.name = name;
		this.address = address;
		this.zipCode = zipCode;
		this.phoneNo = phoneNo;
		
	}
	
	public B2BCustomer findB2BCustomer(int cvr) {
		
		return null;
	}
}
