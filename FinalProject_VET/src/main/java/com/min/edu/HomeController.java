package com.min.edu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping(value = "/home.do")
	public String home() {
		log.info("첫 페이지 호출 Controller");
		return "home";
	}
	
	public String homehome() {
		log.info("&&&&& git test &&&&&");
		log.info("&&&&& git test2 &&&&&");
		log.info("&&&&& git test3 &&&&&");
		log.info("&&&&& git test4 &&&&&");
		return "hone";
	}
	
}
