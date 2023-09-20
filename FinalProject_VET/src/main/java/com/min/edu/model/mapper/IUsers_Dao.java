package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Users_VO;

public interface IUsers_Dao {

	// 전체 회원 조회
	public List<Users_VO> selectAllUsers();

	// 권한별 회원조회
	public List<Users_VO> selectUsersAuth(String users_auth);

	//상태별 회원 조회
	public List<Users_VO> selectUsersStatus(String users_status);

	//아이디로 회원 검색
	public List<Users_VO> searchUsers(String keyword);

	//회원상세조회 - 일반사용자
	public List<Users_VO> selectUserDetail(String users_id);

	//회원상세조회 - 병원관계자
	public Users_VO selectHospitalDetail(String users_id);
	
	//로그인
	public Users_VO loginUser(Map<String, Object> map);
	
	//회원 가입(일반사용자)
	public int insertUser(Map<String, Object> map);
	
}
