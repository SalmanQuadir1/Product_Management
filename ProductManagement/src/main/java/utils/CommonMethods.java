package utils;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

public class CommonMethods {

	@Autowired
	static ServletContext context;



	public static String generateRandomNumber() {
		int randomNumber = (int) (Math.random() * 99999) + 10000;
		String number = String.valueOf(randomNumber);
		return number;
	}

	public static int generateOTP() {

		int min = 100000;
		int max = 999999;
		int number = (int) (Math.random() * (max - min + 1) + min);

		return number;

	}

}


