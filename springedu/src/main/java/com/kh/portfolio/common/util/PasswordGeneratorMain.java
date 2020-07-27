package com.kh.portfolio.common.util;

public class PasswordGeneratorMain {
	public static void main(String[] args) {
		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
				.useDigits(true)
				.useLower(true)
				.useUpper(true)
				.usePunctuation(true)
				.build();
				String password = passwordGenerator.generate(10); 
				System.out.println(password);
	}
}
