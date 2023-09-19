package com.min.edu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Map_Controller {

	@GetMapping(value = "map.do")
	public String map_vet() {
		return "map_main";
	}
	
}
