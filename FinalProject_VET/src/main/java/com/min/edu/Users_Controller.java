package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Users_Controller {

	@Autowired
	private IUsers_Service service;
	
	@GetMapping(path = "/loginForm.do")
	public String loginForm() {
		log.info("&&&&& Users_Controller 메인화면 -> 로그인페이지 &&&&&");
		return "loginForm";
	}
	
	@RequestMapping(path = "/login.do")
	public String login(@RequestParam Map<String, Object> map, HttpSession session,
									  HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller login 로그인 처리 {} &&&&&", map);
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			Users_VO loginVo = service.loginUser(map);
			if(loginVo != null) {
				session.setAttribute("loginVo", loginVo);
				session.setMaxInactiveInterval(1800);
				
				PrintWriter out;
				out = response.getWriter();
				out.println("<script>alert('로그인 성공');location.href='./main.do';</script>");
				out.flush();
				return null;
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그인 정보가 없습니다');location.href='./loginForm.do';</script>");
		out.flush();
		return null;
	}
	
	@GetMapping(path="/main.do")
	public String main() {
		log.info("&&&&& Users_Controller 로그인 성공 -> 메인페이지 &&&&&");
		return "main";
	}
	
	@GetMapping(path="/logout.do")
	public String logout(HttpSession session) {
		log.info("&&&&& Users_Controller 로그아웃 호출 -> 로그인페이지 &&&&&");
		session.invalidate();	
		return "redirect:/loginForm.do";
	}
	
	@GetMapping(path="/adminPage.do")
	public String adminUserList(Model model) {
		log.info("&&&&& Users_Controller 메인페이지 -> 관리자페이지 &&&&&");
		
		List<Users_VO> lists = service.selectAllUsers();
		model.addAttribute("listsVo", lists);
		
		return "adminPage";
	}
	
	@GetMapping(path="/selectUserDetail.do")
	public String selectUserDetail (@RequestParam("users_id") String id,
									HttpSession session, Model model) {
		log.info("&&&&& Users_Controller 관리자페이지 -> 회원상세조회페이지 {} {} &&&&&", session.getAttribute("loginVo"));
		
		List<Users_VO> usersDetail = service.selectUserDetail(id);
		Users_VO hospDetail = service.selectHospitalDetail(id);
		
		String auth = usersDetail.get(0).getUsers_auth();

		if(auth.equals("A")||auth.equals("U")) {
			model.addAttribute("usersDetail", usersDetail);
			return null;
		
		} else if (auth.equals("A")||auth.equals("H")){
			model.addAttribute("hospDetail", hospDetail);
			return null;
		}
		
		return "selectUserDetail";
			
	}
	
	@PostMapping(path="/adminPage.do", produces="application/text; charset=UTF-8;")
	@ResponseBody
	public String searchUser (String keyword, HttpSession session,
							  HttpServletResponse response){
		log.info("&&&&& Users_Controller 관리자페이지 권한별 조회, 상태별 조회, 회원 검색 ajax 처리 {} {} &&&&&", session.getAttribute("loginVo"), keyword);
		response.setContentType("text/html; charset=UTF-8");
		
		List<Users_VO> searchList =  service.searchUsers(keyword);
		
		if(searchList.size()==0) {
				return "";
			} else {
				Gson gson = new Gson();
				String searchUserList = gson.toJson(searchList);
				return searchUserList;
			}
		}
	
	@PostMapping(path="/adminPageAuth.do", produces="application/text; charset=UTF-8;")
	@ResponseBody
	public String selectAuth(String auth, HttpSession session,
							 HttpServletResponse response) {
		log.info("&&&&& Users_Controller 관리자페이지 권한별 조회 ajax처리 {} &&&&&", auth);
		response.setContentType("text/html; charset=UTF-8;");
		
		System.out.println(auth);
		
		List<Users_VO> selectAuth = service.selectUsersAuth(auth);
		
		Gson gson = new Gson();
		String selectAuthList = gson.toJson(selectAuth);
		
		return selectAuthList;
		
	}
	
	@PostMapping(path="/adminPageStatus.do", produces="application/text; charset=UTF-8;")
	@ResponseBody
	public String selectStatus(String status, HttpSession session,
							   HttpServletResponse response) {
		log.info("&&&&& Users_Controller 관리자페이지 권한별 조회 ajax처리 {} &&&&&", status);
		response.setContentType("text/html; charset=UTF-8;");
		
		System.out.println(status);
		
		List<Users_VO> selectStatus = service.selectUsersStatus(status);
		
		Gson gson = new Gson();
		String selectStatusList = gson.toJson(selectStatus);
		
		return selectStatusList;
	}
	
	@GetMapping(path = "/insertUsers.do")
	public String insertUsers() {
		log.info("&&&&& Users_Controller loginForm ->  insertUsers 페이지 이동 &&&&&");
		return "insertUsersStepOne";
	}
}

