package com.min.edu.model.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Reservation_DaoImpl implements IReservation_Dao {

	@Autowired
	private SqlSessionTemplate session;
	
	private static final String NS = "com.min.edu.model.mapper.Reservation_DaoImpl.";

	@Override
	public Map<Object, Object> resrv_monthYNCount() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("lists", session.selectList(NS+"resrv_monthYNCount"));
		return map;
	}
	
}
