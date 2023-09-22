package com.min.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.edu.model.service.IBookMark_Service;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookMark_Controller {
	
	@Autowired
	IBookMark_Service service;
	
	@GetMapping(value = "/insertNewBookmark.do")
	public String insertNewBookmark(String bm_hospid) {
		log.info("&&&&& BookMark_Controller insertNewBookmark 전달받은 파라미터 값 : {} &&&&&", bm_hospid);
		
		return "";
	}

}
