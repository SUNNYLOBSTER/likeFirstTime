package com.min.edu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IMap_Dao;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Map_ServiceImpl implements IMap_Service {

	@Autowired
	private IMap_Dao dao;

	@Override
	public List<Users_VO> hosp_user(String auth) {
		log.info("&&&&& Map_ServiceImpl hosp_user &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",auth);
		return dao.hosp_user(auth);
	}
	
}
