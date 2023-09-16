package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.model.mapper.IMediChart_Dao;
import com.min.edu.vo.FileBoard_VO;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
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
	
//	@Transactional(readOnly = true)
	@Override
	public String insertNewChart(MediChart_VO mvo) {
		log.info("&&&&& MediChart_ServiceImpl insertNewChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", mvo);
		int n = dao.insertNewChart(mvo);
//		fvo.setF_ref(mvo.getMedi_num());
//		int f = dao.fileUpload(fvo);
		String m = (n>0)?dao.getMaxSeq():"";
//		String m = (n>0 && f>=0)?dao.getMaxSeq():"";
		return m;
	}

	@Override
	public List<PetsInfo_VO> selectAllChart(String medi_id) {
		log.info("&&&&& MediChart_ServiceImpl selectAllChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_id);
		return dao.selectAllChart(medi_id);
	}

	@Override
	public List<PetsInfo_VO> selectPetChart(Map<String, Object> map) {
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
	public List<PetsInfo_VO> selectSChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectSChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectSChart(map);
	}

	@Override
	public PetsInfo_VO selectOneChart(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl selectOneChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_num);
		return dao.selectOneChart(medi_num);
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

	@Override
	public List<MediCode_VO> selectAllMediCode() {
		log.info("&&&&& MediChart_ServiceImpl selectAllMediCode &&&&&");
		return dao.selectAllMediCode();
	}

	@Override
	public MediCode_VO searchMediName(String medi_code) {
		log.info("&&&&& MediChart_ServiceImpl searchMediName &&&&&");
		return dao.searchMediName(medi_code);
	}

	@Override
	public String getDetail(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl getDetail &&&&&");
		return dao.getDetail(medi_num);
	}


	


}
