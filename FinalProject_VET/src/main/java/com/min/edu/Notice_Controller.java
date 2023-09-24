package com.min.edu;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.edu.model.service.INotice_Service;
import com.min.edu.vo.NoticeBoard_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Notice_Controller {
	
	@Autowired
	private INotice_Service service;
	
	@GetMapping(path="/notice.do")
	public String notice (Model model, HttpSession session) {
		log.info("&&&&& Users_Controller 메인화면 -> 공지사항 페이지로 이동 &&&&&");
		List<NoticeBoard_VO> lists = service.selectNotice();
		Users_VO loginVo =  (Users_VO)session.getAttribute("loginVo");

		model.addAttribute("noticeLists", lists);
		session.setAttribute("loginVo", loginVo);
		return "noti_main";
	}

}
