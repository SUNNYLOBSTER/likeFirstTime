package com.min.edu.model.mapper;


import java.util.List;
import java.util.Map;


import com.min.edu.vo.QuestBoard_VO;

public interface IBoard_Dao {
	
	public List<QuestBoard_VO> selectQuest();
	
	public List<QuestBoard_VO> selectCodeQuest(Map<String, Object> map);
	
	public List<QuestBoard_VO> selectPartQuest(Map<String, Object> map);

	public List<QuestBoard_VO> selectAllBoard();
	
	public List<QuestBoard_VO> selectReportBoard();
	
	public List<QuestBoard_VO> selectOneBoard(String seq);
	
	public int insertQuest();
	
	public int updateFastQuest();
	
	public int modifyQuest();
	
	public int reportQuest();
	
	public int deleteQuest();
	
	public int insertReply();
	
	public int modifyReply();
	
	public int deleteReply();
	
	public int reportReply();
	
	public int chooseReply();
	
	public int countReply(String id);
	
	public int countChosenReply(String id);
	
	public int calChoiceRate(String id);

	
	
	
	
	
	
	
	
	
	

}
