package com.min.edu.model.service;

import java.util.List;

import com.min.edu.vo.Users_VO;

public interface IMap_Service {

	//Map에 전달될 병원회원 리스트
	public List<Users_VO> hosp_user(String auth);
	
}
