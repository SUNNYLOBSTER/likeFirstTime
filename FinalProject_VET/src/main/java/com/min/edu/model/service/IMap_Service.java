package com.min.edu.model.service;

import java.util.List;

import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.MediConn_VO;
import com.min.edu.vo.Users_VO;

public interface IMap_Service {

	//Map에 전달될 병원회원 리스트
	public List<Users_VO> hosp_user(String auth);
	
	//Map 마커 -> 상세조회
	public Users_VO map_hospDetail(String address);
	
	//병원 상세조회
	public Users_VO hosp_detail(String users_id);
	
	//병원 상세조회 진료동물
	public List<AnimalConn_VO> hosp_anm(String hosp_id);
	
	//병원 상세조회 진료과목
	public List<MediConn_VO> hosp_mediDept(String hosp_id);

	//지도의 마커찍힌 주소 가져와서 병원의 아이디 호출
	public String map_reqAddr(String addr);
}
