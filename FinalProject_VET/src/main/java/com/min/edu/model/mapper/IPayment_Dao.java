package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Payment_VO;

public interface IPayment_Dao {
	
	public List<Payment_VO> selectAllPayment(String pay_id);
	
	public Payment_VO selectOnePayment(String pay_num);
	
	public int insertNewPayment(Map<String, Object> map);
	
	public int canclePayment(String pay_num);
	
	public int insertNewPnt(Map<String, Object> map);
	
	public int usePntOnResrv(String pnt_id);
	
	public int usePntOnBoard(String pnt_id);
	
	public int selectAllPnt(String pnt_id);

}
