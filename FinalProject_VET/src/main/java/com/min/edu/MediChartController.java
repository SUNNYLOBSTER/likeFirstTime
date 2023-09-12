package com.min.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.edu.model.service.IMediChart_Service;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.PetsInfo_VO;


@Controller
public class MediChartController {

	@Autowired
	private IMediChart_Service service;
	
	@GetMapping(value = "/selectAllChart.do")
	public String selectAllChart(Model model) {
		String medi_id = "elsa@disney.com";
		
		List<PetsInfo_VO> allPets = service.searchPet(medi_id);
		model.addAttribute("allPets",allPets);
		
		List<MediChart_VO> allCharts = service.selectAllChart(medi_id);
		model.addAttribute("allCharts",allCharts);
		
		return "chart";
	}
	
	@GetMapping(value = "/selectPetChart.do")
	public String selectPetChart(String pet_name, Model model) {
		String medi_id = "elsa@disney.com";
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("medi_id", medi_id);
			put("pet_name", pet_name);
		}};
		
		List<MediChart_VO> allPetChart = service.selectPetChart(map);
		model.addAttribute("allPetChart",allPetChart);
		
		return "petChart";
	}
	
}
