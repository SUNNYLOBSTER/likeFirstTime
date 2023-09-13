package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
import com.min.edu.vo.PetsInfo_VO;

public interface IMediChart_Dao {

	public int countPet(String id);
	
	public List<PetsInfo_VO> searchPet(String id);
	
	public int insertNewPet(PetsInfo_VO pvo);
	
	public int deletePet(int pet_seq);
	
	public int insertNewChart(MediChart_VO mvo);
	
	public List<PetsInfo_VO> selectAllChart(String pet_owner);
	
	public List<MediChart_VO> selectPetChart(Map<String, Object> map);
	
	public List<MediChart_VO> selectLChart(Map<String, Object> map);
	
	public List<PetsInfo_VO> selectSChart(Map<String,Object> map);
	
	public MediChart_VO selectOneChart(Map<String, Object> map);
	
	public int modifyChart(Map<String, Object> map);
	
	public int deleteChart(String medi_num);
	
	public List<MediCode_VO> selectAllMediCode();
	
}
