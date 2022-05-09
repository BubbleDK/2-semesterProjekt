package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Price {
	
	private LocalDate date;
	private double price;
	
	public Price(double price){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dateString = LocalDate.now().format(formatter);
		this.date = LocalDate.parse(dateString);
		this.price = price;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public double getPrice() {
		return this.price;
	}
	
}
