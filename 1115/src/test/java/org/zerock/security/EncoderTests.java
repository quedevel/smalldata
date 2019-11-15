package org.zerock.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/security-context.xml")
@Log4j
public class EncoderTests {

	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	public void test2() {
		String text = "$2a$10$M6rBX0d.0rT1s7Mleu9MTeLG4bNlKTB0sefm6ls5.ZsfrC/VRF9mG";
		
		boolean result = encoder.matches("abcde", text);
		
		log.info(result);
		
		
	}
	
	
	
	@Test
	public void test1() {
		
		
		log.info(encoder);
		
		String text = "abcde";
		
		String en1 = encoder.encode(text);
		
		log.info(en1);
		
		String en2 = encoder.encode(text);
		
		log.info(en2);
		
		
	}
	
	
}
