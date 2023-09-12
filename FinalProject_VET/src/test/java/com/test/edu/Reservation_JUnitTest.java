package com.test.edu;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.mapper.IReservation_Dao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Reservation_JUnitTest {

	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private IReservation_Dao dao;
	
	
	@Before
	public void test() {
		assertNotNull(session);
	}
	
	@Test
	public void mapTest() {
		Map<Object, Object> map = dao.resrv_monthYNCount();
		map.get("lists");
		assertNotNull(map);
	}

}
