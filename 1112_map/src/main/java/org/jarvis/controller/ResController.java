package org.jarvis.controller;

import java.util.List;

import javax.validation.Valid;

import org.jarvis.domain.RepVO;
import org.jarvis.domain.ResVO;
import org.jarvis.dto.LocationDTO;
import org.jarvis.service.ResSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/res")
@Log4j
public class ResController {

	@Setter(onMethod_ = { @Autowired })
	private ResSerivce service;

	@CrossOrigin
	@PostMapping("/list")
	public ResponseEntity<List<ResVO>> getResList(@RequestBody LocationDTO dto) {
		log.info(dto);
		return new ResponseEntity<List<ResVO>>(service.resList(dto), HttpStatus.OK);
	}

//	@GetMapping("/{no}")
//	public ResponseEntity<ResVO> getRes(@PathVariable("no") Integer no){
//		log.info("=============================================");
//		return new ResponseEntity<ResVO>(service.selectRes(no), HttpStatus.OK);
//	}

	@CrossOrigin
	@GetMapping("/{no}")
	public ResponseEntity<List<RepVO>> getReps(@PathVariable("no") Integer no) {
		return new ResponseEntity<List<RepVO>>(service.repList(no), HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("/register")
	public ResponseEntity<String> repRegister(@RequestBody @Valid RepVO vo) {
		service.insertRep(vo);
		return new ResponseEntity<String>("메세지가 입력 되었습니다.",HttpStatus.CREATED);
	}
}
