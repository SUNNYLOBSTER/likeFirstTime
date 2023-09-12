package com.min.edu.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.PetsInfo_VO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MediChart_DaoImpl implements IMediChart_Dao {
	
	private static final String NS= "com.min.edu.model.mapper.MediChart_DaoImpl.";
	
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public int countPet(String id) {
		return session.selectOne(NS+"countPet",id);
	}

	@Override
	public List<PetsInfo_VO> searchPet(String id) {
		return session.selectList(NS+"searchPet",id);
	}

	@Override
	public int insertNewPet(PetsInfo_VO pvo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
