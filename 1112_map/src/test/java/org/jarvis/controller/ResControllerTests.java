package org.jarvis.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jarvis.dto.LocationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class ResControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mm;
	
	@Before
	public void setting(){
		log.info("aaaaaaaaaaaaaaaaaaaaaaaa");
		this.mm = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	
	@Test
	public void listTest() throws Exception{
		LocationDTO dto = new LocationDTO();
		dto.setLat(37.570444);
		dto.setLng(126.985320);
		
		String js = new Gson().toJson(dto);
		log.info("json   "+js);
		
		log.info(
		mm.perform(
				post("/res/list")
				.contentType(MediaType.APPLICATION_JSON)
				.content(js)
				).andExpect(status().is(200))
		);
	}
}
