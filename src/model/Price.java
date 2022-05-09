package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Price {
	private double price;
	
	public Price(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
}
