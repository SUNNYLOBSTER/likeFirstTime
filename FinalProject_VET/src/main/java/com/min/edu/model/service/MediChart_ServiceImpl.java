package com.min.edu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IMediChart_Dao;
import com.min.edu.vo.PetsInfo_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MediChart_ServiceImpl implements IMediChart_Service {
	
	@Autowired
	private IMediChart_Dao dao;
	
	@Override
	public int countPet(String id) {
		log.info("&&&&& MediChart_ServiceImpl countPet 전달받은 파라미터값 : {} &&&&&", id);
		return dao.countPet(id);
	}

	@Override
	public List<PetsInfo_VO> searchPet(String id) {
		log.info("&&&&& MediChart_ServiceImpl searchPet 전달받은 파라미터값 : {} &&&&&", id);
		return dao.searchPet(id);
	}

	@Override
	public int insertNewPet(PetsInfo_VO pvo) {
		log.info("&&&&& MediChart_ServiceImpl insertNewPet 전달받은 파라미터값 : {} &&&&&", pvo);
		return dao.insertNewPet(pvo);
	}
	


}
