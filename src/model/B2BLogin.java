package model;

import java.nio.charset.Charset;
import java.util.Random;

public class B2BLogin {
	
	private String email;
	private String giftNo;
	
	public B2BLogin(String email) {
		this.email = email;
	}
	
	
	
	private String createRandomGiftNo(String email) {
		getRandomString();
		
		
		
		return "Hej";
	}

	private String getRandomString() {
		byte[] array = new byte[256];
		new Random().nextBytes(array);
		int maxLength = 20;
		
		String randomString = new String(array, Charset.forName("UTF-8"));
		
		StringBuffer buffer = new StringBuffer();
		
		String AlphaNumericString = randomString.replaceAll("[^A-Za-z0-9]", "");
		
		for(int i = 0; i < AlphaNumericString.length(); i++) {
			
			if(Character.isLetter(AlphaNumericString.charAt(i)) && (maxLength > 0)
				|| Character.isDigit(AlphaNumericString.charAt(i)) && (maxLength > 0)){
				
				buffer.append(AlphaNumericString.charAt(i));
				maxLength--;
			}
		}
		
		this.giftNo = buffer.toString();
		
		return giftNo;		
	}



	public String getEmail() {
		return email;
	}

	public String getGiftNo() {
		return giftNo;
	}

}
