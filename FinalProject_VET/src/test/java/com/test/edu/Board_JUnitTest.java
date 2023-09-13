package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.min.edu.model.mapper.IBoard_Dao;
import com.min.edu.vo.QuestBoard_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class Board_JUnitTest {
	
	@Autowired
	private IBoard_Dao dao;
	@Autowired
	private ApplicationContext context;
	

//	@Before
	public void db_Test() {
		SqlSessionTemplate session = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		assertNotNull(session);
	}
	
//	@Test
	public void selectQuest() {
		List<QuestBoard_VO> lists = dao.selectQuest();
		assertNotNull(lists);
	}
	
//	@Test
	public void selectCodeQuest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qst_species", "D");
		List<QuestBoard_VO> lists = dao.selectCodeQuest(map);
		assertNotNull(lists);
	}
	
//	@Test
	public void selectPartQuest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qst_species", "D");
		map.put("qst_part", "10");
		List<QuestBoard_VO> lists = dao.selectPartQuest(map);
		assertNotNull(lists);
	}
	
//	@Test
	public void selectAllBoard() {
		List<QuestBoard_VO> lists = dao.selectAllBoard();
		assertNotNull(lists);
	}
	
//	@Test
	public void selectReportBoard() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qst_status", "R");
		map.put("rpy_status", "R");
		List<QuestBoard_VO> lists = dao.selectReportBoard();
		assertNotNull(lists);
	}
	
//	@Test
	public void selectOneBoard() {
		List<QuestBoard_VO> lists = dao.selectOneBoard("Q2");
		assertNotNull(lists);
	}
	
	
	
	
	
	
	
	


	
	
	
	
	
}
