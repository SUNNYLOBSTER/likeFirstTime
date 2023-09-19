package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.SchedBoard_VO;

public interface ISchedule_Dao {
	
	public List<SchedBoard_VO> selectAllSchedule(String sche_id);
	
	public SchedBoard_VO selectOneSchedule(Map<String, Object> map);
	
	public int modifySchedule(Map<String, Object> map);
	
	public int deleteSchedule(int sche_num);

}
