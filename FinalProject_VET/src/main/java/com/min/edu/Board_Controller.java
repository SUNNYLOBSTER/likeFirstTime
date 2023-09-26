package com.min.edu;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Board_Controller {
	
	@Autowired
	private IPayment_Service service_pay;

	@Autowired
	private IBoard_Service service;
	
	@GetMapping(value = "/questBoard.do")
	public String questBoard(HttpSession session, Model model) {
		log.info("&&&&& Board_Controller 실행 qst_questBoard 이동 &&&&&");
		List<QuestBoard_VO> lists = service.selectQuest();
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		
		
		model.addAttribute("questList", lists);
		
		return "qst_questBoard";
	}
	
	@GetMapping(value = "/questDetail.do")
	public String questDetail(HttpSession session, String seq, Model model) {
		log.info(seq);
		log.info("&&&&& Board_Controller 실행 qst_questDetail 이동 &&&&&");
		List<QuestBoard_VO> qstDetail = service.selectOneBoard(seq);
		List<ReplyBoard_VO> rpyList = service.selectReply(seq);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		
//		String auth = session.get
		
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
	
//	게시글 작성 후 html태그 제거하여 출력
	@GetMapping(value = "/afterWriteQuest.do")
	public String selectOneBoard(String seq, Model model) {
		log.info("&&&&& Board_Controller 글 작성 후 상세페이지 이동, selectOneBoard 전달받은 값 : {}  &&&&&", seq);
		
		QuestBoard_VO vo = (QuestBoard_VO) service.selectOneBoard(seq);
		
		String unescapedContent = StringEscapeUtils.unescapeHtml4(vo.getQst_content());
		
		model.addAttribute("vo", vo);
		model.addAttribute("qst_content", unescapedContent);
		
		return "qst_questDetail";
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
//		QuestBoard_VO vo = 

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
	
	
	
	
//	페이징
//	@PostMapping(value = "/page.do")
//	public Map<String, Object> page(@RequestParam(name="page") int selectPage,
//									HttpSession session,
//									Model model){
//		log.info("RestPageController 게시판 페이지 REST 처리를 위한 page.do : {}", selectPage);
//		log.info("RestPageController page.do 에 Session 확인 : {}, {}", session.getAttribute("loginVo"), session.getAttribute("row"));
//		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
//		Paging_VO pVo = (Paging_VO) session.getAttribute("row");
//		
//		// ---- 페이징 VO 객체 값 입력
//		pVo.setTotalCount(service.getAllBoardCount(new HashMap<String, Object>(){{
////			put("auth",loginVo.getAuth());
//		}}));
//		
//		pVo.setCountList(5);
//		pVo.setCountPage(5);
//		pVo.setTotalPage(pVo.getTotalCount());
//		pVo.setPage(selectPage);
//		pVo.setStagePage(selectPage);
//		pVo.setEndPage(pVo.getCountPage());
//		
//		// ---- 게시글의 페이징 처리된 리스트
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("first", pVo.getPage()*pVo.getCountList()-(pVo.getCountList()-1));
//		map.put("last", pVo.getPage()*pVo.getCountList());
////		map.put("auth", loginVo.getAuth()); 
//		List<QuestBoard_VO> lists = service.getAllBoardPage(map);
//		
//		// 반환되는 JSON 객체로 변환이 가능한 Map을 생성
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("lists", lists);
//		result.put("row", pVo);
//		result.put("memId", loginVo.getId());
		
//		return new HashMap<String, Object>(){{
//			put("check","return value");
//		}};
//		return result;
//	}
	
		
}