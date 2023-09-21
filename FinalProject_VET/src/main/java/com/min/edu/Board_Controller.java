package com.min.edu;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
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
import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.AnimalCode_VO;
import com.min.edu.vo.AnimalPart_VO;
import com.min.edu.vo.QuestBoard_VO;
import com.min.edu.vo.ReplyBoard_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Board_Controller {

	@Autowired
	private IBoard_Service service;
	@Autowired
	private IUsers_Service service2;
	
	@GetMapping(value = "/questBoard.do")
	public String questBoard(Model model) {
		log.info("&&&&& Board_Controller 실행 qst_questBoard 이동 &&&&&");
		List<QuestBoard_VO> lists = service.selectQuest();
		
		model.addAttribute("questList", lists);
		
		return "qst_questBoard";
	}
	
	@GetMapping(value = "/questDetail.do")
	public String questDetail(String seq, Model model) {
		log.info(seq);
		log.info("&&&&& Board_Controller 실행 qst_questDetail 이동 &&&&&");
		List<QuestBoard_VO> qstDetail = service.selectOneBoard(seq);
		List<ReplyBoard_VO> rpyList = service.selectReply(seq);
		model.addAttribute("qstDetail", qstDetail);
		model.addAttribute("rpyList", rpyList);
		
		return "qst_questDetail";
	}

	@GetMapping(value = "/chooseReply.do")
	public String choiceReply(String seq) {
		log.info("&&&&& Board_Controller choiceReply 실행  &&&&&");
		log.info("&&&&& Board_Controller choiceReply 전달받은 seq값 : {}  &&&&&", seq);
	
		int choice = service.chooseReply(seq);
	
		return choice>0?"redirect:/questBoard.do":"redirect:/questBoard.do";
	}
	
	@GetMapping(value = "/selectPartQuest.do")
	public String selectPartQuest(
		    @RequestParam("qst_species") String qst_species,
		    @RequestParam("qst_part") String qst_part,
		    @RequestParam("qst_content") String qst_content) {
		log.info("&&&&& Board_Controller selectPartQuest 실행 &&&&&");
		log.info("&&&&& Board_Controller selectPartQuest 전달받은 값 : {} &&&&&", qst_species, qst_part, qst_content);
		
		return "redirect:/questBoard.do";
	}
	
	
	@GetMapping(value = "/writeQuestForm.do")
	public String moveWriteForm() {
		log.info("&&&&& Board_Controller 실행 moveWriteForm 이동 &&&&&");
		
		return "qst_writeQuestForm";
	}
	
	@PostMapping(value = "/qst_writeQuest.do")
	public String writeQuest(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		log.info("&&&&& Board_Controller 실행 qst_writeQuest 작동 &&&&&");
		
		Users_VO loginVO = (Users_VO) session.getAttribute("loginVo");
		String qst_id = loginVO.getUsers_id();
		
		String qst_title = (String) map.get("qst_title");
		String qst_content = (String) map.get("qst_content");
		String escapedContent = StringEscapeUtils.escapeHtml4(qst_content);
		String qst_code = (String) map.get("qst_species");
		String qst_part = (String) map.get("qst_part");
		String qst_fast = (String) map.get("qst_fast");
		
		QuestBoard_VO insertVo = new QuestBoard_VO();
		
		insertVo.setQst_id(qst_id);
		insertVo.setQst_content(escapedContent);
		insertVo.setQst_species(qst_code);
		insertVo.setQst_part(qst_part);
		insertVo.setQst_title(qst_title);
		insertVo.setQst_fast(qst_fast);
		
		String quest = service.insertQuest(insertVo);
		
		return "redirect:/questBoard.do";
		
		
		
		
		
//		대분류, 소분류, 우선답변게시글 제목, 내용, 파일업로드 
		
		
	}
	
	
	
	
	
	
}










