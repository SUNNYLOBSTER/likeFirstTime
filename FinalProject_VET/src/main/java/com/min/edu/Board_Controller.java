package com.min.edu;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.model.mapper.IBoard_Dao;
import com.min.edu.model.service.IBoard_Service;
import com.min.edu.vo.QuestBoard_VO;
import com.min.edu.vo.ReplyBoard_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Board_Controller {

	@Autowired
	private IBoard_Dao dao;
	@Autowired
	private IBoard_Service service;
	
	@GetMapping(value = "/questBoard.do")
	public String questBoard(Model model) {
		log.info("&&&&& Board_Controller 실행 questBoard 이동 &&&&&");
		List<QuestBoard_VO> lists = dao.selectQuest();
		model.addAttribute("qstVo", lists);
		return "questBoard";
	}
	
	@GetMapping(value = "/questDetail.do")
	public String questDetail(String seq, Model model) {
		log.info(seq);
		log.info("&&&&& Board_Controller 실행 questDetail 이동 &&&&&");
		List<QuestBoard_VO> lists = service.selectOneBoard(seq);
		model.addAttribute("lists", lists);
		return "questDetail";
	}
	
	@GetMapping(value = "/writeQuest.do")
	public String moveWriteForm() {
		log.info("&&&&& Board_Controller 실행 moveWriteForm 이동 &&&&&");
		return "writeQuest";
	}
	
}
