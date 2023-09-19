package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
}
