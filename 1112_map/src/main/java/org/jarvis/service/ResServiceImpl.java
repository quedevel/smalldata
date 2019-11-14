package org.jarvis.service;

import java.util.List;

import org.jarvis.domain.RepVO;
import org.jarvis.domain.ResVO;
import org.jarvis.dto.LocationDTO;
import org.jarvis.mapper.ResMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ResServiceImpl implements ResSerivce {

	@Autowired
	ResMapper mapper;
	
	@Override
	public void insertRep(RepVO vo) {
		mapper.insertRep(vo);
	}

	@Override
	public List<RepVO> repList(Integer no) {
		return mapper.repList(no);
	}

	@Override
	public ResVO selectRes(Integer no) {
		ResVO vo = mapper.selectRes(no);
		vo.setReps(mapper.repList(no));
		return vo;
	}

	@Override
	public List<ResVO> resList(LocationDTO dto) {
		log.info("serviceImpl - -=- =- =- =-=  "+dto);
		List<ResVO> result = mapper.resList(dto);
		for (ResVO vo: result) {
			vo.setReps(mapper.repList(vo.getNo()));
		}
		log.info(result);
		return result;
	}

}
