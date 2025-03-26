package com.synergy.backend.global.util.random;

import java.util.Random;

public class RandomCodeGenerator {

	public static String lowercaseAlphaNumericCode() {
		String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder code = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			code.append(characters.charAt(random.nextInt(characters.length())));
		}
		return code.toString();
	}
}
