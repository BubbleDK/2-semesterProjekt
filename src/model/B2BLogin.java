package model;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.HashMap;

public class B2BLogin {

	private String giftNo;



//	private void createRandomGiftNo(String email) {
//		HashMap<String, String> emailGiftNo = new HashMap<String, String>();
//		emailGiftNo.put(email, getRandomString());
//		System.out.println(emailGiftNo);
//
//	}

	public String createGiftNo() {
		byte[] array = new byte[256];
		new Random().nextBytes(array);
		int maxLength = 20;

		String randomString = new String(array, Charset.forName("UTF-8"));

		StringBuffer buffer = new StringBuffer();

		String AlphaNumericString = randomString.replaceAll("[^A-Za-z0-9]", "");

		for (int i = 0; i < AlphaNumericString.length(); i++) {

			if (Character.isLetter(AlphaNumericString.charAt(i)) && (maxLength > 0)
					|| Character.isDigit(AlphaNumericString.charAt(i)) && (maxLength > 0)) {

				buffer.append(AlphaNumericString.charAt(i));
				maxLength--;
			}
		}

		this.giftNo = buffer.toString();

		return giftNo;
	}



	public String getEmail() {
		// TODO Skal vi ikke kunne gette en email på et object? Det skal helst kunne ske til GUI
		return null;
	}

	

}
