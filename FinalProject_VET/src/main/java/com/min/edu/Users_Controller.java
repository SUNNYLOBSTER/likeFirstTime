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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		try {
			Users_VO loginVo = service.loginUser(map);
			if(loginVo != null) {
				session.setAttribute("loginVo", loginVo);
				
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
	
}
