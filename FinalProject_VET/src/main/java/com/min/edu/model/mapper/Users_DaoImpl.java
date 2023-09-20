package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.Users_VO;

@Repository
public class Users_DaoImpl implements IUsers_Dao {
	
	private static final String NS = "com.min.edu.model.mapper.Users_DaoImpl.";
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Users_VO> selectAllUsers() {
		return session.selectList(NS+"selectAllUsers");
	}

	@Override
	public List<Users_VO> selectUsersAuth(String users_auth) {
		return session.selectList(NS+"selectUsersAuth", users_auth);
	}

	@Override
	public List<Users_VO> selectUsersStatus(String users_status) {
		return session.selectList(NS+"selectUsersStatus", users_status);
	}

	@Override
	public List<Users_VO> searchUsers(String keyword) {
		return session.selectList(NS+"searchUsers", keyword);
	}

	@Override
	public List<Users_VO> selectUserDetail(String users_id) {
		return session.selectList(NS+"selectUserDetail", users_id);
	}

	@Override
	public Users_VO selectHospitalDetail(String users_id) {
		return session.selectOne(NS+"selectHospitalDetail",users_id);
	}

	@Override
	public Users_VO loginUser(Map<String, Object> map) {
		return session.selectOne(NS+"loginUser", map);
	}

	@Override
	public int insertUser(Map<String, Object> map) {
		return session.insert(NS+"insertUser", map);
	}

	
	
}
