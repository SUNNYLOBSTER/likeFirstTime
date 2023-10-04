package com.min.edu;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.min.edu.model.service.IBoard_Service;
import com.min.edu.model.service.IPayment_Service;
import com.min.edu.vo.Paging_VO;
import com.min.edu.vo.QuestBoard_VO;
import com.min.edu.vo.ReplyBoard_VO;
import com.min.edu.vo.Reservation_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Board_Controller {
	
	@Autowired
	private IPayment_Service service_pay;

	@Autowired
	private IBoard_Service service;
	
//	@GetMapping(value = "/questBoard.do")
//	public String questBoard(HttpSession session, Model model) {
//		log.info("&&&&& Board_Controller 실행 qst_questBoard 이동 &&&&&");
//		List<QuestBoard_VO> lists = service.selectQuest();
//		List<String> lists2 = new ArrayList<String>();
//		
//		for (int i = 0; i < lists.size(); i++) {
//			lists2.add(StringEscapeUtils.unescapeHtml4(lists.get(i).getQst_content()));
//			lists2.add(lists.get(i).getQst_seq());
//			lists2.add(lists.get(i).getQst_fast());
//		}
//		
//		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
//		model.addAttribute("lists2", lists2);
//		model.addAttribute("questList", lists);
//		
//		
//		return "qst_questBoard";
//	}
	
	
	
	
	@GetMapping(value = "/questDetail.do")
	public String questDetail(HttpSession session, String seq, Model model) {
		log.info(seq);
		log.info("&&&&& Board_Controller 실행 qst_questDetail 이동 &&&&&");
		List<QuestBoard_VO> qstDetail = service.selectOneBoard(seq);
		List<ReplyBoard_VO> rpyList = service.selectReply(seq);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		
	    String unescapedContent = StringEscapeUtils.unescapeHtml4(qstDetail.get(0).getQst_content());
		
	    model.addAttribute("loginVo", loginVo);
		model.addAttribute("qstDetail", qstDetail);
		model.addAttribute("content", unescapedContent);
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
		
//		List<QuestBoard_VO> boards = service.selectPartQuest(map);
//		log.info("{}",boards);
//		model.addAttribute("questList", boards);
		
		return "qst_questBoard";
	}
	
	@GetMapping(value = "/writeQuestForm.do")
	public String writeQuestForm(HttpSession session, HttpServletResponse response) throws IOException {
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
		log.info("&&&&& Board_Controller 실행 writeQuest 작동 &&&&&");
		log.info("{}", map);
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
				
		String qst_id = loginVo.getUsers_id();
		
		   int wholePnt =  service_pay.selectAllPnt(qst_id);
		   
		   String qst_content = (String) map.get("questContent");
		   String escapedContent = StringEscapeUtils.escapeHtml4(qst_content);
		   String qst_title = (String) map.get("questTitle");
		   String qst_species = (String) map.get("qst_species");
		   String qst_part = (String) map.get("qst_part");
		   String qst_fast = (String) map.get("qst_fast") == null?"N":"Y";
		   
		   if(wholePnt >= 500 && qst_fast == "Y") {
			   QuestBoard_VO insertVo = new QuestBoard_VO();
			   insertVo.setQst_id(qst_id);
			   insertVo.setQst_content(escapedContent);
			   insertVo.setQst_title(qst_title);
			   insertVo.setQst_species(qst_species);
			   insertVo.setQst_part(qst_part);
			   insertVo.setQst_fast(qst_fast);
			   
			   String quest = service.insertQuest(insertVo);
			   service_pay.usePntOnBoard(qst_id);
			   
			   return "redirect:/questDetail.do?seq="+quest;
			   
		   }else {
			   PrintWriter out = response.getWriter();
		       out.print("<script>alert('빠른문의 결제 포인트가 부족합니다. 포인트를 충전해주세요'); "
		       				+ "location.href='./goPayment.do';"
		       				+ "</script>");
		       out.flush();
		       out.close();
		       return null;
		   }
		
	}
	
	@GetMapping(value="/selectUsersBoard.do")
	public String selectUsersBoard(HttpSession session, Model model) {
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String qst_id = loginVo.getUsers_id();
		
		List<QuestBoard_VO> lists = service.selectUsersBoard(qst_id);
		model.addAttribute("lists",lists);		
		return "qst_oneUsersRegular";
	}
	
	@GetMapping(value = "/selectFastBoard.do")
	public String selectFastBoard(HttpSession session, Model model) {
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String qst_id = loginVo.getUsers_id();
		
		List<QuestBoard_VO> lists = service.selectFastBoard(qst_id);
		model.addAttribute("lists",lists);		
		
		return "qst_oneUsersFast";
	}
	
// 게시글 수정
	@GetMapping(value = "/qst_writeQuest.do")
	public String modifyQuest(String seq, HttpSession session, HttpServletResponse response) throws IOException {
		log.info("&&&&& Board_Controller 게시글 수정 페이지  이동 &&&&&");

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
		
//		return "qst_questModifyForm";
	}
	
//	@GetMapping(value = "/reportQuest.do")
//	public String reportQuest() {
//		
//		return "";
//	}
	
//	페이징
	@GetMapping(value = "/questBoard.do")
	public String boardList(Model model, HttpSession session, HttpServletRequest req,
			@RequestParam Map<String,Object> map) {
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String page = (String) map.get("page");
		log.info("BoardController 게시글 전체 값을 저장하여 이동하는 boardList");
		log.info("BoardController 게시글 조회 전달된 page : {}", page);
		
		Paging_VO pVo = new Paging_VO();
		List<String> lists2 = new ArrayList<String>();
		
		log.info("----------현재 페이지 : {} ", page);
		
		int selectPage = Integer.parseInt(page == null?"1":page);
		map.put("page", selectPage);
		log.info("----------선택된 페이지 : {} ", selectPage);
		
		pVo.setTotalCount(service.getAllBoardCount(map)); // 총 게시물의 갯수
		pVo.setCountList(5);	// 출력된 게시글의 갯수
		pVo.setCountPage(5);	// 화면에 몇개의 페이지를 보여줄건지 (페이지 그룹)
		pVo.setTotalPage(pVo.getTotalCount());	// 총 페이지의 갯수
		pVo.setPage(selectPage); // 화면에서 선택된 페이지 변호
		pVo.setStagePage(selectPage); // 페이지 그룹과 시작번호
		pVo.setEndPage(pVo.getCountPage()); // 끝번호 
		
		map.put("first", pVo.getPage()*pVo.getCountList() - (pVo.getCountList()-1));
		map.put("last", pVo.getPage()*pVo.getCountList());
		
		List<QuestBoard_VO> questList = service.getAllBoardPage(map);
		
		for (int i = 0; i < questList.size(); i++) {
			lists2.add(StringEscapeUtils.unescapeHtml4(questList.get(i).getQst_content()));
			lists2.add(questList.get(i).getQst_seq());
			lists2.add(questList.get(i).getQst_fast());
		}
		
		log.info("전달된 값 {}", questList);
		
		model.addAttribute("lists2", lists2);
		model.addAttribute("questList", questList);
		model.addAttribute("page", pVo);
	    log.info("queryString  :  " + req.getQueryString());
	    String queryString = req.getQueryString() == null ? "" : req.getQueryString();
	    
	    queryString = queryString.replaceFirst("page=[0-9]*", "");
	    log.info("queryString  :  " + queryString);
		model.addAttribute("queryString", queryString);
		
		return "qst_questBoard";
	}
	
		
}