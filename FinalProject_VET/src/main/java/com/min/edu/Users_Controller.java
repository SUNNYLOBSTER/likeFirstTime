package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		return "users_loginForm";
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
		
		return "users_adminPage";
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
		return "users_selectUserDetail";
			
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
		return "users_insertUsersStepOne";
	}
	
	@GetMapping(path = "/insertStepTwo.do")
	public String insertStepTwo() {
		log.info("&&&&& Users_Controller insertUsers->insertStepTwo 페이지 이동 &&&&&");
		return "users_insertUsersStepTwo";
	}
	
	
	//아이디 중복검사
	@GetMapping(path="/duplication.do")
	public String duplication() {
		log.info("&&&&& Users_Controller duplication 아이디 중복검사 팝업 오픈 &&&&&");
		return "users_duplication";
	}
	
	
	//아이디(이메일) 중복검사 처리를 위한 rest 처리
	@PostMapping(path="/duplicationAjax.do")
	@ResponseBody
	public String duplicationAjax (String checkEmail) {
		log.info("&&&&& Users_Controller duplicationAjax 아이디 중복 확인 {} &&&&&", checkEmail);
		int n = service.duplicationId(checkEmail);
		return (n>0)?"true":"false";
	}
	
	//회원가입
	@PostMapping(path = "/signUp.do")
	public String insertUsersTwo(@RequestParam Map<String, Object> map, Model model) {
		log.info("&&&&& Users_Controller insertStepTwo 회원가입 후 insertStepThree 페이지 이동 &&&&&");
		
		int n = service.insertUser(map);
		model.addAttribute("signUpVo",map);
		return (n>=1)?"users_insertUsersStepThree":"redirect:/main.do";
		
	}
	
	//회원정보 추가입력(일반사용자)
	@PostMapping(path="/addUserInfo.do")
	public String addInfo(@RequestParam  Map<String, Object> map,
							HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller insertStepThree 추가정보 입력 후 메인페이지 이동  &&&&&");
		response.setContentType("text/html; charset=UTF-8");
		
		String addr = (String)map.get("addr");
		String addrDetail = (String)map.get("addrDetail");
		
		String users_addr = addr+" "+addrDetail;
		System.out.println(users_addr);
		
		map.put("users_addr", users_addr);
		
		boolean isc = service.addInfo(map);
		
		if(isc==true) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('추가정보 등록이 완료되었습니다.');location.href='./main.do';</script>");
			out.flush();
			return null;
		} else {
			return "addInfo";
		}
		
		
	}
	
	//회원가입 페이지 이동 (병원관계자)
	@GetMapping(path = "/insertHospStepTwo.do")
	public String insertHospStepTwo() {
		log.info("&&&&& Users_Controller insertUsers->users_insertHospStepTwo 병원 관계자 페이지 이동 &&&&&");
		return "users_insertHospStepTwo";
	}
	
	//회원가입(병원관계자)
	@PostMapping(path = "/signUpHosp.do")
	public String insertHospTwo(@RequestParam Map<String, Object> map, Model model) {
		log.info("&&&&& Users_Controller insertStepTwo 회원가입 후 insertStepThree 페이지 이동 {} &&&&&", map);
		
		System.out.println(map);
		
		String hosp_id = (String)map.get("users_id");
		map.put("hosp_id", hosp_id);
		
		String hosp_name = (String)map.get("users_name");
		map.put("hosp_name", hosp_name);
		
		String addr = (String)map.get("addr");
		String addrDetail = (String)map.get("addrDetail");
		
		String users_addr = addr+" "+addrDetail;
		System.out.println(users_addr);
		
		map.put("users_addr", users_addr);
		
		String openTime = (String)map.get("hosp_openTime");
		String closeTime = (String)map.get("hosp_closeTime");
		
		System.out.println("여는시간 : " + openTime+ "닫는 시간 : " + closeTime);
		
		String hosp_time = "{\"open\":\""+openTime+"\", \"close\":\""+closeTime+"\"}";
		map.put("hosp_time", hosp_time);
		
		String hosp_off = (String)map.get("chk");
		map.put("hosp_off", hosp_off);
				
		System.out.println(map);
		
		boolean isc = service.insertHosp(map);
		model.addAttribute("signUpVo",map);
		
		return (isc==true)?"users_insertHospStepThree":"redirect:/insertHospStepTwo.do";
			
		}

	//회원 탈퇴 페이지 이동
	@GetMapping(path = "/resignUser.do")
	public String resignUserPage(HttpSession session, Model model) {
		log.info("&&&&& Users_Controller 회원 탈퇴 페이지로 이동 {} &&&&&");
		Users_VO loginVo = (Users_VO)session.getAttribute("users_id");
		model.addAttribute("loginVo", loginVo);
		return "users_resignUser";
	}
	
	
	//회원 탈퇴
	@PostMapping(path = "/resignUser.do")
	@ResponseBody
	public String resignUser(@RequestParam Map<String, Object> map, HttpSession session,
							 HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller 회원 탈퇴 페이지로 이동 {} &&&&&", map);
		response.setContentType("text/html; charset=UTF-8");
		
		String users_id = (String)map.get("users_id");
		String users_pw = (String)map.get("users_pw");
		System.out.println(users_id +"   "+ users_pw);

		Users_VO vo = new Users_VO();
		vo.setUsers_id(users_id);
		vo.setUsers_pw(users_pw);
	
		int n = service.resignUser(vo);
		
		if(n == 1 ) {
			session.invalidate();
			PrintWriter out = response.getWriter();
			out.println("<script>alert('탈퇴 완료되었습니다.');location.href='./main.do';</script>");
			out.flush();
			return null;
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 값을 입력하셨습니다. 다시 입력해주세요.');location.href='./resignUser.do';</script>");
			out.flush();
			return null;
		}
		
	}


}

