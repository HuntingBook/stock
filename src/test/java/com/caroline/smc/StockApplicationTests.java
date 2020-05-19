package com.caroline.smc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class StockApplicationTests {
	
	@Test
	public void generatePassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("aq1sw2de");
		System.out.println("encode passw0rd:");
		System.out.println(encodedPassword);
	}

}
