package com.min.edu;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		List<QuestBoard_VO> qstDetail = service.selectOneBoard(seq);
		List<ReplyBoard_VO> rpyList = service.selectReply(seq);
		model.addAttribute("qstDetail", qstDetail);
		model.addAttribute("rpyList", rpyList);
		return "questDetail";
	}

	@GetMapping(value = "/chooseReply.do")
	public String choiceReply(String seq) {
	log.info("&&&&& Board_Controller choiceReply 실행  &&&&&");
	log.info("&&&&& Board_Controller choiceReply 전달받은 seq값 : {}  &&&&&", seq);
	
	int choice = service.chooseReply(seq);
	
		return choice>0?"redirect:/questBoard.do":"redirect:/questBoard.do";
//		return choice>0?"redirect:/questDetail.do?seq="+seq:"redirect:/questDetail.do";
	}
	
	
	@GetMapping(value = "/writeQuestForm.do")
	public String moveWriteForm() {
		log.info("&&&&& Board_Controller 실행 moveWriteForm 이동 &&&&&");
		return "writeQuest";
	}
	
	@PostMapping(value = "/writeQuest.do")
	@ResponseBody
	public String writeQuest() {
		log.info("&&&&& Board_Controller 실행 writeQuest &&&&&");
		return null;
	}
	
	
	
	
	
	
}










