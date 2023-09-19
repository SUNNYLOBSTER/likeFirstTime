package com.min.edu.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.Users_VO;

@Repository
public class Map_DaoImpl implements IMap_Dao {

	@Autowired
	private SqlSessionTemplate session;
	
	private static final String NS = "com.min.edu.model.mapper.Map_DaoImpl.";

	@Override
	public List<Users_VO> hosp_user(String auth) {
		return session.selectList(NS+"hosp_user", auth);
	}

	@Override
	public Users_VO map_hospDetail(String address) {
		return session.selectOne(NS+"map_hospDetail",address);
	}
	
}
