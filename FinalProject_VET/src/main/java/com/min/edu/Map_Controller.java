package com.min.edu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.min.edu.model.service.IMap_Service;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Map_Controller {

	@Autowired
	private IMap_Service service;
	
	@GetMapping(value = "/map.do")
	public String map_vet() {
		log.info("&&&&& 헤더 -> 동물병원찾기 페이지 이동 &&&&&");
		return "map_main";
	}
	
	@PostMapping(value = "/getHospUser.do")
	@ResponseBody
	public String getHospUser(String users_auth) {
		log.info("&&&&& Map_Controller getHospUser &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", users_auth);
		List<Users_VO> list = service.hosp_user(users_auth);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	@GetMapping(value = "/map_hospDetail.do")
	public String map_hospDetail(String address,String placeName, Model model) {
		log.info("&&&&& Map_Controller getHospUser &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", address);
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", placeName);
		Users_VO user_vo = service.map_hospDetail(address.substring(3));
		System.out.println(user_vo.getHospital_vo().get(0).getHosp_time());
		String hosp_time = user_vo.getHospital_vo().get(0).getHosp_time();
		model.addAttribute("hosp_detail", user_vo);
		model.addAttribute("hosp_time", hosp_time);
		model.addAttribute("address", address);
		model.addAttribute("placeName", placeName);
		return "hosp_detail";
	}
	
}
