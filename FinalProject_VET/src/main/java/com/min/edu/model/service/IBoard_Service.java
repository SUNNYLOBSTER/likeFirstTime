package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.QuestBoard_VO;
import com.min.edu.vo.ReplyBoard_VO;

public interface IBoard_Service {
	
	public List<QuestBoard_VO> selectQuest();
	
	public List<QuestBoard_VO> selectCodeQuest(Map<String, Object> map);
	
	public List<QuestBoard_VO> selectPartQuest(Map<String, Object> map);

	public List<QuestBoard_VO> selectAllBoard();
	
	public List<QuestBoard_VO> selectReportBoard();
	
	public List<QuestBoard_VO> selectOneBoard(String seq);
	
	public int insertQuest(QuestBoard_VO vo);
	
	public int updateFastQuest(String seq);
	
	public int modifyQuest(Map<String, Object> map);
	
	public int reportQuest(String seq);
	
	public int deleteQuest(String seq);
	
	public int insertReply(ReplyBoard_VO vo);
	
	public int modifyReply(Map<String, Object> map);
	
	public int deleteReply(String seq);
	
	public int reportReply(String seq);
	
	public int chooseReply(String seq);
	
	public int countReply(String id);
	
	public int countChosenReply(String id);
	
	public int calChoiceRate(String id);
	

}
