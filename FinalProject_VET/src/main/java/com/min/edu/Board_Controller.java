package com.min.edu;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import com.min.edu.model.service.IPayment_Service;
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
	private IPayment_Service service_pay;
	
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
	public String selectPartQuest(@RequestParam Map<String,Object> map, Model model) {
		
		log.info("{}",map);
		
		List<QuestBoard_VO> boards = service.selectPartQuest(map);
		log.info("{}",boards);
		model.addAttribute("questList", boards);
		
		
		return "qst_questBoard";
	}
	
	
	@GetMapping(value = "/writeQuestForm.do")
	public String moveWriteForm(HttpSession session, HttpServletResponse response) throws IOException {
		log.info("&&&&& Board_Controller 실행 moveWriteForm 이동 &&&&&");
		
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		
				try {
					if(loginVo != null) {
						session.setAttribute("loginVo", loginVo);
						session.setMaxInactiveInterval(1800);
						
						PrintWriter out;
					out = response.getWriter();
					out.flush();
					return "qst_writeQuestForm";
					}
				} catch (IOException e) {
					e.printStackTrace();
			}
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인 후 작성 가능합니다');location.href='./loginForm.do';</script>");
				out.flush();
				return null;
		
		
	}
	
//	게시글 작성
	@PostMapping(value = "/qst_writeQuest.do")
	public String writeQuest(@RequestParam Map<String, Object> map, HttpSession session, Model model, HttpServletResponse response) throws IOException {
		log.info("&&&&& Board_Controller 실행 qst_writeQuest 작동 &&&&&");
		log.info("{}", map);
		
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
				try {
					if(loginVo != null) {
						session.setAttribute("loginVo", loginVo);
						session.setMaxInactiveInterval(1800);
						
						PrintWriter out;
					out = response.getWriter();
					out.flush();
					return null;
					}
				} catch (IOException e) {
					e.printStackTrace();
			}
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인 정보가 없습니다');location.href='./loginForm.do';</script>");
				out.flush();
				
		String qst_id = loginVo.getUsers_id();
		
		String qst_title = (String) map.get("questTitle");
		String qst_content = (String) map.get("questContent");
		String escapedContent = StringEscapeUtils.escapeHtml4(qst_content);
		String qst_species = (String) map.get("qst_species");
		String qst_part = (String) map.get("qst_part");
		String qst_fast = (String) map.get("qst_fast") == null?"N":"Y";
		
		QuestBoard_VO insertVo = new QuestBoard_VO();
		
		insertVo.setQst_id(qst_id);
		insertVo.setQst_content(escapedContent);
		insertVo.setQst_species(qst_species);
		insertVo.setQst_part(qst_part);
		insertVo.setQst_title(qst_title);
		insertVo.setQst_fast(qst_fast);
		
		String quest = service.insertQuest(insertVo);
		service_pay.usePntOnBoard(qst_id);
		
		
		return "redirect:/questBoard.do?seq="+quest;
	}
	
//	게시글 작성 후 html태크 제거하여 출력
	@GetMapping(value = "/afterWriteQuest.do")
	public String selectOneBoard(String seq, Model model) {
		log.info("&&&&& Board_Controller 글 작성 후 상세페이지 이동, selectOneBoard 전달받은 값 : {}  &&&&&", seq);
		
		QuestBoard_VO vo = (QuestBoard_VO) service.selectOneBoard(seq);
		
		String unescapedContent = StringEscapeUtils.unescapeHtml4(vo.getQst_content());
		
		model.addAttribute("vo", vo);
		model.addAttribute("qst_content", unescapedContent);
		
		return "qst_questDetail";
	}
	
	
	
	
	
	
}










