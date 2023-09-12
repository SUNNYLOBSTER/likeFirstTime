package com.min.edu.model.service;

import java.util.List;

import com.min.edu.vo.PetsInfo_VO;

public interface IMediChart_Service {
	
	public int countPet(String id);
	
	public List<PetsInfo_VO> searchPet(String id);
	
	public int insertNewPet(PetsInfo_VO pvo);

}
