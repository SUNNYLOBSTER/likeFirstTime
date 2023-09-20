package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Payment_VO;

public interface IPayment_Service {
	
	public List<Payment_VO> selectAllPayment(String pay_id);
	
	public Payment_VO selectOnePayment(String pay_num);
	
	public int insertNewPayment(Map<String, Object> map);
	
	//결제완료시 결제금액만큼 포인트 적립 transaction
	public int insertPaymentPoint(Map<String, Object> map);
	
	public int canclePayment(String pay_num);
	
	public int insertNewPnt(Map<String, Object> map);
	
	public int usePntOnResrv(String pnt_id);
	
	public int usePntOnBoard(String pnt_id);
	
	public int selectAllPnt(String pnt_id);

}
