package com.min.edu.model.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;

@Repository
public class Reservation_DaoImpl implements IReservation_Dao {

	@Autowired
	private SqlSessionTemplate session;
	
	private static final String NS = "com.min.edu.model.mapper.Reservation_DaoImpl.";

	@Override
	public Map<Object, Object> resrv_monthYNCount(Map<String, Object> map) {
		Map<Object, Object> inMap = new HashMap<Object, Object>();
		inMap.put("lists", session.selectList(NS+"resrv_monthYNCount",map));
		return inMap;
	}

	@Override
	public List<Hospital_VO> resrv_monthResrvLists(Map<String, Object> map) {
		return session.selectList(NS+"resrv_monthResrvLists",map);
	}

	@Override
	public int resrv_dayYCount(Reservation_VO rvo) {
		return session.selectOne(NS+"resrv_dayYCount",rvo);
	}

	@Override
	public List<Reservation_VO> resrv_dayTimeLists(Reservation_VO rvo) {
		return session.selectList(NS+"resrv_dayTimeLists",rvo);
	}

	@Override
	public Reservation_VO resrv_detail(String resrv_num) {
		return session.selectOne(NS+"resrv_detail", resrv_num);
	}

	@Override
	public List<Reservation_VO> resrv_dayStatus(Reservation_VO rvo) {
		return session.selectList(NS+"resrv_dayStatus", rvo);
	}

	@Override
	public int resrv_insert(Reservation_VO rvo) {
		return session.insert(NS+"resrv_insert", rvo);
	}

	
}