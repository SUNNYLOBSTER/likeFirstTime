package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.model.mapper.IUsers_Dao;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Users_ServiceImpl implements IUsers_Service {

	@Autowired
	private IUsers_Dao dao;

	@Override
	public List<Users_VO> selectAllUsers() {
		log.info("&&&&& Users_ServiceImpl selectAllUsers &&&&&");
		return dao.selectAllUsers();
	}

	@Override
	public List<Users_VO> selectUsersAuth(String users_auth) {
		log.info("&&&&& Users_ServiceImpl selectUsersAuth 전달받은 파라미터 값 : {} &&&&&", users_auth);
		return dao.selectUsersAuth(users_auth);
	}

	@Override
	public List<Users_VO> selectUsersStatus(String users_status) {
		log.info("&&&&& Users_ServiceImpl selectUsersStatus 전달받은 파라미터 값 : {} &&&&&", users_status);
		return dao.selectUsersStatus(users_status);
	}

	@Override
	public List<Users_VO> searchUsers(String keyword) {
		log.info("&&&&& Users_ServiceImpl searchUsers 전달받은 파라미터 값 : {} &&&&&", keyword);
		return dao.searchUsers(keyword);
	}

	@Override
	public List<Users_VO> selectUserDetail(String users_id) {
		log.info("&&&&& Users_ServiceImpl selectUserDetail 전달받은 파라미터 값 : {} &&&&&", users_id);
		return dao.selectUserDetail(users_id);
	}

	@Override
	public Users_VO selectHospitalDetail(String users_id) {
		log.info("&&&&& Users_ServiceImpl selectHospitalDetail 전달받은 파라미터 값 : {} &&&&&", users_id);
		return dao.selectHospitalDetail(users_id);
	}

	@Override
	public Users_VO loginUser(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl loginUser 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.loginUser(map);
	}

	@Override
	public int insertUser(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl insertUser 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.insertUser(map);
	}
	
	@Override
	public int duplicationId(String email) {
		log.info("&&&&& Users_ServiceImpl duplicationId 전달받은 파라미터 값 : {} &&&&&", email);
		return dao.duplicationId(email);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean addInfo(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl addInfo 추가정보입력 : {} &&&&&", map);
		int n = dao.addInfoUser(map);
		int m = dao.insertPetInfo(map);
		return (n+m)>0?true:false;
	}

	@Transactional(readOnly = true)
	@Override
	public boolean insertHosp(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl insertHosp 병원회원가입 : {} &&&&&", map);
		int n = dao.insertHospInfo(map);
		int m = dao.insertHospInfoDetail(map);
		return (n+m)>0?true:false;
	}
	
	
	
}
