package org.jarvis.service;

import java.util.List;

import org.jarvis.domain.RepVO;
import org.jarvis.domain.ResVO;
import org.jarvis.dto.LocationDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ResSerivce {

	public void insertRep(RepVO vo);
	public List<RepVO> repList(Integer no);
	public ResVO selectRes(Integer no);
	public List<ResVO> resList(LocationDTO dto);
	
}
