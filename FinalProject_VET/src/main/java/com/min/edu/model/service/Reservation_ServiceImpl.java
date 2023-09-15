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
	public List<Reservation_VO> resrv_ResrvLists(String resrv_hops) {
		log.info("&&&&& Reservation_ServiceImpl resrv_ResrvLists &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_hops);
		return dao.resrv_ResrvLists(resrv_hops);
	}

	@Override
	public Reservation_VO resrv_detail(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_detail &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_detail(resrv_num);
	}

	@Override
	public List<Reservation_VO> resrv_dayStatus(Map<String, Object> map) {
		log.info("&&&&& Reservation_ServiceImpl resrv_dayStatus &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.resrv_dayStatus(map);
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
	public Hospital_VO resrv_reqPage(String hosp_id) {
		log.info("&&&&& Reservation_ServiceImpl resrv_reqPage &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", hosp_id);
		return dao.resrv_reqPage(hosp_id);
	}

	@Override
	public List<Reservation_VO> resrv_reqCal(String resrv_hops) {
		log.info("&&&&& Reservation_ServiceImpl resrv_reqCal &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_hops);
		return dao.resrv_reqCal(resrv_hops);
	}

	
}
