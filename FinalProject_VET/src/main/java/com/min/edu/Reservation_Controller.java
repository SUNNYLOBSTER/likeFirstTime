package com.min.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.edu.model.service.IReservation_Service;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Reservation_Controller {

	@Autowired
	private IReservation_Service service;
	
	@GetMapping(value = "/resrv_Select.do")
	public String resrv_Select() {
		log.info("&&&&& 홈 -> 진료예약 조회 &&&&&");
		return "hosp_resrvMNG";
	}
	
	@GetMapping(value = "/resrv_monthCount.do")
	public String resrv_MonthCount(Model model) {
		// 받아올 값
		String hosp_id = "gana@naver.com";
		String yyyy = "2023";
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("yyyy",yyyy);
			put("RESRV_HOPS",hosp_id);
		}};
		
		Map<Object, Object> resultMap = service.resrv_monthYNCount(map);
		model.addAttribute("monthYLists", resultMap.get("lists"));
		
		return "monthYLists";
	}
	
	@GetMapping(value = "/fullCalendar.do")
	public void fullCalendar(HttpServletResponse response) {
	}
	
}
