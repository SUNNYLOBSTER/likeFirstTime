package com.min.edu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IReservation_Dao;
import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Reservation_ServiceImpl implements IReservation_Service {

	@Autowired
	private IReservation_Dao dao;

	@Override
	public Map<Object, Object> resrv_monthYNCount(Map<String, Object> map) {
		log.info("&&&&& Reservation_ServiceImpl resrv_monthYNCount 실행 &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.resrv_monthYNCount(map);
	}

	@Override
	public List<Hospital_VO> resrv_monthResrvLists(Map<String, Object> map) {
		log.info("&&&&& Reservation_ServiceImpl resrv_monthResrvLists &&&&&",map);
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", map);
		String date = (String) map.get("date");
		String[] dateParts = date.split("-");
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("yyyy", dateParts[0]);
		inMap.put("mm", dateParts[1]);
		inMap.put("resrv_hops", map.get("resrv_hops"));
		return dao.resrv_monthResrvLists(inMap);
	}

	@Override
	public int resrv_dayYCount(Reservation_VO rvo) {
		log.info("&&&&& Reservation_ServiceImpl resrv_dayYCount &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", rvo);
		return dao.resrv_dayYCount(rvo);
	}

	@Override
	public List<Reservation_VO> resrv_dayTimeLists(Reservation_VO rvo) {
		log.info("&&&&& Reservation_ServiceImpl resrv_dayTimeLists &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", rvo);
		return dao.resrv_dayTimeLists(rvo);
	}

	@Override
	public Reservation_VO resrv_detail(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_detail &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_detail(resrv_num);
	}

	@Override
	public List<Reservation_VO> resrv_dayStatus(Reservation_VO rvo) {
		log.info("&&&&& Reservation_ServiceImpl resrv_dayStatus &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", rvo);
		return dao.resrv_dayStatus(rvo);
	}

	@Override
	public int resrv_insert(Reservation_VO rvo) {
		log.info("&&&&& Reservation_ServiceImpl resrv_insert &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", rvo);
		return dao.resrv_insert(rvo);
	}

	@Override
	public int resrv_updateToY(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_updateToY &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_updateToY(resrv_num);
	}

	@Override
	public int resrv_updateToN(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_updateToN &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_updateToN(resrv_num);
	}

	@Override
	public int resrv_delete(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_delete &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_delete(resrv_num);
	}

	@Override
	public List<Reservation_VO> resrv_test(String resrv_hops) {
		log.info("&&&&& Reservation_ServiceImpl resrv_test &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_hops);
		return dao.resrv_test(resrv_hops);
	}
	
	
	
}
