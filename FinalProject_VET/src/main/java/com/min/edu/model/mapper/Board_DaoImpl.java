package com.min.edu.model.mapper;


import java.util.List;
import java.util.Map;

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

	@Override
	public List<QuestBoard_VO> selectCodeQuest(Map<String, Object> map) {
		return session.selectList(NS+"selectCodeQuest", map);
	}

	@Override
	public List<QuestBoard_VO> selectPartQuest(Map<String, Object> map) {
		return session.selectList(NS+"selectPartQuest", map);
	}

	@Override
	public List<QuestBoard_VO> selectAllBoard() {
		return session.selectList(NS+"selectAllBoard");
	}

	@Override
	public List<QuestBoard_VO> selectOneBoard(String seq) {
		return session.selectList(NS+"selectOneBoard", seq);
	}
	
	@Override
	public List<QuestBoard_VO> selectReportBoard() {
		return session.selectList(NS+"selectReportBoard");
	}
	
	@Override
	public int insertQuest() {
		
		return session.insert(NS+"insertQuest");
	}

	@Override
	public int updateFastQuest() {
		
		return session.update(NS+"updateFastQuest");
	}

	@Override
	public int modifyQuest() {
		
		return session.update(NS+"modifyQuest");
	}

	@Override
	public int reportQuest() {
		
		return session.update(NS+"reportQuest");
	}

	@Override
	public int deleteQuest() {
		
		return session.update(NS+"deleteQuest");
	}

	@Override
	public int insertReply() {
		return session.insert(NS+"insertReply");
	}

	@Override
	public int modifyReply() {
		return session.update(NS+"modifyReply");
	}

	@Override
	public int deleteReply() {
		
		return session.update(NS+"deleteReply");
	}

	@Override
	public int reportReply() {
		return session.update(NS+"reportReply");
	}

	@Override
	public int chooseReply() {
		
		return session.update(NS+"chooseReply");
	}

	@Override
	public int countReply(String id) {
		
		return session.selectOne(NS+"countReply", id);
	}

	@Override
	public int countChosenReply(String id) {
		return session.selectOne(NS+"countChosenReply", id);
	}

	@Override
	public int calChoiceRate(String id) {
		return session.selectOne(NS+"calChoiceRate", id);
	}


	
	
}
