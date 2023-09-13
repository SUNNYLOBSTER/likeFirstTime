package com.test.edu;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.mapper.IBoard_Dao;
import com.min.edu.vo.QuestBoard_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Board_JUnitTest {
	
	@Autowired
	private IBoard_Dao dao;
	@Autowired
	private ApplicationContext context;
	

	@Test
	public void db_Test() {
		SqlSessionTemplate session = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		assertNotNull(session);
	}
	
//	@Test
//	public void selectQuest() {
//		List<QuestBoard_VO> lists = dao.selectQuest();
//		assertNotNull(lists);
//	}
	


	
	
	
	
	
}
