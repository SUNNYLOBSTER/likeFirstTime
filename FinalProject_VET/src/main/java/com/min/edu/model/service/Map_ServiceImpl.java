package com.min.edu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IMap_Dao;
import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.MediConn_VO;
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

	@Override
	public Users_VO map_hospDetail(String address) {
		log.info("&&&&& Map_ServiceImpl map_hospDetail &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",address);
		return dao.map_hospDetail(address);
	}

	@Override
	public Users_VO hosp_detail(String users_id) {
		log.info("&&&&& Map_ServiceImpl hosp_detail &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",users_id);
		return dao.hosp_detail(users_id);
	}

	@Override
	public List<AnimalConn_VO> hosp_anm(String hosp_id) {
		log.info("&&&&& Map_ServiceImpl hosp_anm &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",hosp_id);
		return dao.hosp_anm(hosp_id);
	}

	@Override
	public List<MediConn_VO> hosp_mediDept(String hosp_id) {
		log.info("&&&&& Map_ServiceImpl hosp_mediDept &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",hosp_id);
		return dao.hosp_mediDept(hosp_id);
	}

	@Override
	public String map_reqAddr(String addr) {
		log.info("&&&&& Map_ServiceImpl map_reqAddr &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",addr);
		return dao.map_reqAddr(addr);
	}

	
	
}
