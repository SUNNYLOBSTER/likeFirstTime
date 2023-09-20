package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.min.edu.model.service.ISchedule_Service;
import com.min.edu.vo.SchedBoard_VO;
import com.min.edu.vo.ScheduleCalendar_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Schedule_Controller {

	@Autowired
	private ISchedule_Service service;
	
	@GetMapping(value = "/selectAllSchedule.do")
	public String schedule_calendar() {
		 log.info("&&&&& 마이페이지 -> 일반사용자 일정조회 &&&&&");
		 
		 return "schedule_main";
	}
	
	@GetMapping(value = "/fullCalendar_sche.do")
	public void fullCalendar(HttpSession session,HttpServletResponse response) throws IOException {
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String sche_id = loginVo.getUsers_id();
		
		List<ScheduleCalendar_VO> resultlist = new ArrayList<>();
		
		List<SchedBoard_VO> scheduleList = service.selectAllSchedule(sche_id);
		for(int i=0; i< scheduleList.size(); i++) {
			ScheduleCalendar_VO svo = new ScheduleCalendar_VO();
			svo.setTitle(scheduleList.get(i).getSche_title());
			svo.setSche_num(scheduleList.get(i).getSche_num());
			svo.setStart(scheduleList.get(i).getSche_date());
			resultlist.add(svo);
		}

		Gson gson = new Gson();
		String json = gson.toJson(resultlist);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		
	}
	
	@GetMapping(value = "/selectOneSchedule.do")
	public SchedBoard_VO selectOneSchedule(String sche_num, Model model) {
		log.info("&&&&& 일정캘린더 -> 일정 상세조회 &&&&&");
		log.info("&&&&& Schedule_Controller selectOneSchedule 전달받은 parameter값 : {} &&&&&", sche_num);
		
		int n = Integer.parseInt(sche_num); 
		SchedBoard_VO svo = service.selectOneSchedule(n);
		model.addAttribute("svo",svo);
		return svo;
	}
	
	@PostMapping(value = "/insertNewSchedule.do")
	public String insertNewSchedule(@RequestParam Map<String, Object> map, HttpSession session) {
		log.info("&&&&& Schedule_Controller insertNewSchedule 전달받은 parameter값 : {} &&&&&", map);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String sche_id = loginVo.getUsers_id();
		
		String sche_title = (String) map.get("sche_title");
		String sche_date = (String) map.get("sche_date");
		String sche_content = (String) map.get("sche_content");
		String sche_hour = (String) map.get("sche_hour");
		String sche_minute = (String) map.get("sche_minute");
		
		if(sche_hour == null || sche_minute == null || sche_content == null) {
			sche_hour = "";
			sche_minute = "";
			sche_content = "";
		}
		SchedBoard_VO svo = new SchedBoard_VO(0, sche_id, sche_date, sche_title, sche_content, sche_hour, sche_minute);
		
		int n = service.insertNewSchedule(svo);
		
		
		return (n>0)?"redirect:/selectAllSchedule.do":"";
	}
}
