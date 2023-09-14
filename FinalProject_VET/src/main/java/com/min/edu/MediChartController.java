package com.min.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.model.service.IMediChart_Service;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
import com.min.edu.vo.PetsInfo_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class MediChartController {

	@Autowired
	private IMediChart_Service service;
	
	
	@GetMapping(value = "/selectAllChart.do")
	public String selectAllChart(HttpSession session, Model model) {
		log.info("&&&&& MediChartController 메인화면 -> 전체진료기록페이지 &&&&&");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		List<PetsInfo_VO> allPets = service.searchPet(pet_owner);
		model.addAttribute("allPets",allPets);
		
		List<PetsInfo_VO> allCharts = service.selectAllChart(pet_owner);
		model.addAttribute("allCharts",allCharts);
		
		List<MediCode_VO> lists = new ArrayList<MediCode_VO>();
		
		List<MediCode_VO> mlists =  service.selectAllMediCode();
		
		for(int i=0; i< mlists.size();i++) {
			if (mlists.get(i).getMedi_code().length() == 2) {
				lists.add(mlists.get(i));
			}
		}
		model.addAttribute("codelists",lists);
		
		return "chart";
	}
	

	@PostMapping(value = "/listByCodeS.do")
	@ResponseBody
	public Map<String, Object> listByCodeS(String medicodeL){
		log.info("&&&&& MediChartController listByCodeS 전달받은 parameter 값 : {} &&&&&",medicodeL);
		
		// 서비스 실행 쿼리 결과
		List<MediCode_VO> mlists = service.selectAllMediCode();
		List<MediCode_VO> list = new ArrayList<MediCode_VO>();
		
		for (int i=0; i < mlists.size(); i++) {
			if(mlists.get(i).getMedi_top().equals(medicodeL)) {
					list.add(mlists.get(i));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		return map;
	}
	
	
	@GetMapping(value = "/selectPetChart.do")
	public String selectPetChart(HttpSession session, String pet_name, Model model) {
		log.info("&&&&& MediChartController 전체진료기록 -> 반려동물별 진료기록페이지 &&&&&");
		log.info("&&&&& MediChartController selectPetChart 전달받은 parameter값 : {} &&&&&",pet_name);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("pet_owner", pet_owner);
			put("pet_name", pet_name);
		}};
		
		List<MediChart_VO> allPetChart = service.selectPetChart(map);
		model.addAttribute("allPetChart",allPetChart);
		
		return "petChart";
	}
	
	
	@GetMapping(value = "/insertNewChartForm.do")
	public String insertNewChartForm(String mpet_seq , Model model) {
		log.info("&&&&& MediChartController 반려동물별 진료기록 -> 새 진료기록 작성페이지 &&&&&");
		return "insertNewChartForm";
	}
	
	
	@PostMapping(value = "/selectSChart.do")
	@ResponseBody
	public Map<String, Object> selectSChart(HttpSession session, String medi_l, String medi_s){
		log.info("&&&&& MediChartController selectSChart 전달받은 parameter값 : {} {}&&&&&",medi_l,medi_s);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("medi_l", medi_l);
			put("medi_s", medi_s);
			put("pet_owner", pet_owner);
		}};
		List<PetsInfo_VO> slists = service.selectSChart(map);
		List<PetsInfo_VO> lists = new ArrayList<PetsInfo_VO>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		
		for(int i=0; i<slists.size();i++ ) {
			lists.add(slists.get(i));
		}
		map2.put("lists", lists);
	
		return map2;
	}
	
	@PostMapping(value = "/selectOneChart.do")
	@ResponseBody
	public PetsInfo_VO selectOneChart(HttpSession session, String medi_num){
		log.info("&&&&& MediChartController selectOneChart 전달받은 parameter값 : {}&&&&&",medi_num);
		
		PetsInfo_VO pvo =  service.selectOneChart(medi_num);
		
		return pvo;
	}

	
}
