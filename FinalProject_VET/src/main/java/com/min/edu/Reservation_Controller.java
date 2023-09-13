package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.min.edu.model.service.IReservation_Service;
import com.min.edu.vo.FullCalendar_VO;
import com.min.edu.vo.Reservation_VO;

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
	public void fullCalendar(HttpServletResponse response) throws IOException {
		List<FullCalendar_VO> resultList = new ArrayList<>();
		
		List<Reservation_VO> resrvList = service.resrv_test("gana@naver.com");
		for (Reservation_VO rvo : resrvList) {
			FullCalendar_VO fvo = new FullCalendar_VO();
			fvo.setTitle(rvo.getResrv_name());
			fvo.setStart(rvo.getResrv_visit());
			resultList.add(fvo);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(resultList);
		PrintWriter out = response.getWriter();
		
		out.print(json);
		out.flush();
		out.close();
		log.info("&&&&& json 호출 :{}",json);
	}
	
}
