package org.jarvis.service;

import org.jarvis.domain.RepVO;
import org.jarvis.domain.ResVO;
import org.jarvis.dto.LocationDTO;
import org.jarvis.mapper.ResMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ResServiceTests {

	@Autowired
	ResMapper mapper;
	
	@Test
	public void test() {
		LocationDTO dto = new LocationDTO();
		dto.setLat(37.570444);
		dto.setLng(126.985320);
		
		log.info(mapper.resList(dto));
		
		
	}
	
	@Test
	public void test2() {
		RepVO vo = new RepVO();
		vo.setNo(9);
		vo.setScore(3);
		vo.setReply("가나다ㅁㅁㅁ");
		
		mapper.insertRep(vo);
	}
	
	@Test
	public void test3() {
		log.info(mapper.selectRes(9));
	}
	
	
}
