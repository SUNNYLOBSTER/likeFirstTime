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
import com.min.edu.model.service.IBoard_Service;
import com.min.edu.vo.QuestBoard_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class Board_JUnitTest {
	
	@Autowired
	private IBoard_Dao dao;
	@Autowired
	private IBoard_Service service;
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
	
	@Test
	public void insertQuest() {
		QuestBoard_VO vo = new QuestBoard_VO();
		vo.setQst_id("carrot@gmail.com");
		vo.setQst_species("O");
		vo.setQst_part("08");
		vo.setQst_title("우리집 하늘다람쥐가 이상해요");
		vo.setQst_content("오늘 아침에 다람쥐가 뺨을 때리면서 깨우길래 이게 간이 배 밖으로 나왔구나 싶었어요. 야구방망이를 챙겨들고 날아가는 다람쥐를 얼른 따라갔는데, 화장실로 들어가더라고요. 그래서 집에 있던 친구에게 문 밖을 지키라고 당부하고선 화장실 문을 열었는데, 아니 제 하늘다람쥐가 세면대에서 배 밖으로 쏟아진 간을 씻고 있는 거에요. 너무 아파보였어요. 지금은 새장에 넣어둿는데 어떻게 해야할까요? 다시 줏어넣기에는 간이 너무 커다래요.");
		
		int n = service.insertQuest(vo);
		assertEquals(n, 1);
	}
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
}
