package org.jarvis.mapper;

import java.util.List;

import org.jarvis.domain.RepVO;
import org.jarvis.domain.ResVO;
import org.jarvis.dto.LocationDTO;

public interface ResMapper {
	
	public void insertRep(RepVO vo);
	public List<RepVO> repList(Integer no);
	public ResVO selectRes(Integer no);
	public List<ResVO> resList(LocationDTO dto);
}
