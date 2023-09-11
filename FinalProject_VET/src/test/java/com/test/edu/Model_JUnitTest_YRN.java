package com.test.edu;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.min.edu.model.service.IMediChart_Service;
import com.min.edu.vo.PetsInfo_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class Model_JUnitTest_YRN {

	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private IMediChart_Service service;
	
//	@Test
	public void countPet() {
		String id = "elsa@disney.com";
		int n = service.countPet(id);
		assertEquals(n, 2);
	}
//	@Test
	public void searchPet() {
		String id = "elsa@disney.com";
		List<PetsInfo_VO> lists = service.searchPet(id);
		assertNotNull(lists);
	}
	
//	@Test
//	public void insertNewPet() {
//		PetsInfo_VO pvo = new PetsInfo_VO(null, null, null, null, null, null, null, null, null);
//	}

}
