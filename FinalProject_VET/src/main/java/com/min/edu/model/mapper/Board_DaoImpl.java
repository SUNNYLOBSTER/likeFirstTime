package com.min.edu.model.mapper;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.QuestBoard_VO;

@Repository
public class Board_DaoImpl implements IBoard_Dao {
	
	private static final String NS = "com.min.edu.model.mapper.Board_DaoImpl.";
	
	@Autowired
	SqlSessionTemplate session;
	

	@Override
	public List<QuestBoard_VO> selectQuest() {
		return session.selectList(NS+"selectQuest");
	}


//	@Override
//	public List<QuestBoard_VO> selectCodeQuest(String qst_species) {
//		return null;
//	}
	
	
	

}
