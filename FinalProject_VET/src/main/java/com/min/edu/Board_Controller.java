package com.min.edu;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.edu.model.mapper.IBoard_Dao;
import com.min.edu.vo.QuestBoard_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Board_Controller {

	@Autowired
	private IBoard_Dao dao;
	
	@GetMapping(value = "/questBoard.do")
	public String questBoard(Model model) {
		log.info("&&&&& Board_Controller 실행 questBoard 이동 &&&&&");
		List<QuestBoard_VO> lists = dao.selectQuest();
		model.addAttribute("qstVo", lists);
		return "questBoard";
	}
	
	@GetMapping(value = "/questDetail.do")
	public String questDetail() {
		log.info("&&&&& Board_Controller 실행 questDetail 이동 &&&&&");
		return "questDetail";
	}
	
	
}
