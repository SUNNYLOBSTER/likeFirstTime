package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IMediChart_Dao;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.PetsInfo_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MediChart_ServiceImpl implements IMediChart_Service {
	
	@Autowired
	private IMediChart_Dao dao;
	
	@Override
	public int countPet(String id) {
		log.info("&&&&& MediChart_ServiceImpl countPet 전달받은 파라미터값 : {} &&&&&", id);
		return dao.countPet(id);
	}

	@Override
	public List<PetsInfo_VO> searchPet(String id) {
		log.info("&&&&& MediChart_ServiceImpl searchPet 전달받은 파라미터값 : {} &&&&&", id);
		return dao.searchPet(id);
	}

	@Override
	public int insertNewPet(PetsInfo_VO pvo) {
		log.info("&&&&& MediChart_ServiceImpl insertNewPet 전달받은 파라미터값 : {} &&&&&", pvo);
		return dao.insertNewPet(pvo);
	}

	@Override
	public int deletePet(int pet_seq) {
		log.info("&&&&& MediChart_ServiceImpl deletePet 전달받은 파라미터값 : {} &&&&&", pet_seq);
		return dao.deletePet(pet_seq);
	}

	@Override
	public int insertNewChart(MediChart_VO mvo) {
		log.info("&&&&& MediChart_ServiceImpl insertNewChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", mvo);
		return dao.insertNewChart(mvo);
	}

	@Override
	public List<MediChart_VO> selectAllChart(String medi_id) {
		log.info("&&&&& MediChart_ServiceImpl selectAllChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_id);
		return dao.selectAllChart(medi_id);
	}

	@Override
	public List<MediChart_VO> selectPetChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectAllChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectPetChart(map);
	}

	@Override
	public List<MediChart_VO> selectLChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectLChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectLChart(map);
	}

	@Override
	public List<MediChart_VO> selectSChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectSChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectSChart(map);
	}

	@Override
	public MediChart_VO selectOneChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectOneChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectOneChart(map);
	}

	@Override
	public int modifyChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl modifyChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.modifyChart(map);
	}

	@Override
	public int deleteChart(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl deleteChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_num);
		return dao.deleteChart(medi_num);
	}
	


}
