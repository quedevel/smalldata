package org.zerock.security;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class EncoderTests {

	@Autowired
	private MemberMapper mapper;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	public void getMemberTest() {
		MemberVO vo = mapper.getMember("admin95");
		
		log.info(vo);
	}
	
	
	@Test
	public void insertAdminRole() {
		IntStream.range(90, 101).forEach(i ->{
			AuthVO vo = new AuthVO();
			vo.setUserid("admin"+i);
			vo.setAuth("ROLE_ADMIN");
			mapper.insertAuth(vo);
		});
	}
	
	
	
	@Test
	public void insertMemberRole() {
		IntStream.range(1, 101).forEach(i ->{
			AuthVO vo = new AuthVO();
			if(i<90) {
				vo.setUserid("user"+i);
			} else {
				vo.setUserid("admin"+i);
			}
			vo.setAuth("ROLE_MEMBER");
			mapper.insertAuth(vo);
		});
	}
	
	
	
	@Test
	public void insertDummyMember() {
		IntStream.range(1, 101).forEach(i->{
			MemberVO vo = new MemberVO();
			if(i < 90) {
				vo.setUserid("user"+i);
				vo.setUserpw(encoder.encode("user"+i));
				vo.setUsername("회원"+i);
			} else {
				vo.setUserid("admin"+i);
				vo.setUserpw(encoder.encode("admin"+i));
				vo.setUsername("관리자"+i);
			}
			mapper.insertMember(vo);
		});
	}
	
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
