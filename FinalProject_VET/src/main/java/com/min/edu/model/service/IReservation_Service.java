package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;

public interface IReservation_Service {

	//월별 예약확정/예약취소 건수 조회
	public Map<Object, Object> resrv_monthYNCount(Map<String, Object> map);
	
	//해당 월에 등록된 예약자 명단
	public List<Hospital_VO> resrv_monthResrvLists(Map<String, Object> map);
	
	//해당날짜에 등록된 확정예약 건수 조회
	public int resrv_dayYCount(Reservation_VO rvo);
	
	//해당날짜의 각 시간대에 예약확정된 예약자 조회
	public List<Reservation_VO> resrv_dayTimeLists(Reservation_VO rvo);

	//예약 상세정보를 조회
	public Reservation_VO resrv_detail(String resrv_num);
	
	//일별 예약상태에 따른 조회
	public List<Reservation_VO> resrv_dayStatus(Reservation_VO rvo);
	
	public int resrv_insert(Reservation_VO rvo);
	
}