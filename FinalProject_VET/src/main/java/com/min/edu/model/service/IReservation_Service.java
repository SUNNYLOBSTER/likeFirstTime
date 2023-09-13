package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;

public interface IReservation_Service {

	//월별 예약확정/예약취소 건수 조회
	public Map<Object, Object> resrv_monthYNCount(Map<String, Object> map);
	
	//해당 월에 등록된 예약자 명단
	public List<Reservation_VO> resrv_ResrvLists(String resrv_hops);
	
	//예약 상세정보를 조회
	public Reservation_VO resrv_detail(String resrv_num);
	
	//일별 예약상태에 따른 조회
	public List<Reservation_VO> resrv_dayStatus(Reservation_VO rvo);
	
	//진료예약 등록
	public int resrv_insert(Reservation_VO rvo);
	
	//예약대기(W)에서 예약확정(Y) 상태로 변경
	public int resrv_updateToY(String resrv_num);
		
	//예약확정(Y) 상태에서 예약취소(N) 상태로 변경
	public int resrv_updateToN(String resrv_num);
	
	//예약을 거절/취소한 경우 예약목록에서 삭제
	public int resrv_delete(String resrv_num);
	
	
}
