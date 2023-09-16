package com.min.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@PostMapping(value = "/selectPetChart.do")
	@ResponseBody
	public Map<String, Object> selectPetChart(HttpSession session, String pet_seq) {
		log.info("&&&&& MediChartController 전체진료기록 -> 반려동물별 진료기록페이지 &&&&&");
		log.info("&&&&& MediChartController selectPetChart 전달받은 parameter값 : {} &&&&&",pet_seq);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("pet_owner", pet_owner);
			put("pet_seq", pet_seq);
		}};
		
		List<PetsInfo_VO> selectPetChart = service.selectPetChart(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		
		map2.put("detail", selectPetChart);
		
		return map2;
	}
	
	
	@GetMapping(value = "/insertNewChartForm.do")
	public String insertNewChartForm(HttpSession session, Model model) {
		log.info("&&&&& MediChartController 반려동물별 진료기록 -> 새 진료기록 작성페이지 &&&&&");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		List<PetsInfo_VO> pvo = service.searchPet(pet_owner);
		List<PetsInfo_VO> petList = new ArrayList<PetsInfo_VO>();
		if(!pvo.isEmpty()) {
			for(int i=0; i<pvo.size();i++) {
				petList.add(pvo.get(i));
			}
		}
		model.addAttribute("petList", petList);
		
		return "insertNewChartForm";
	}
	
	@PostMapping(value = "/insertNewChart.do")
	public String insertNewChart(@RequestParam Map<String, Object> map, HttpSession session , Model model) {
		log.info("&&&&& MediChartController 새 진료기록 작성페이지 -> 상세페이지 &&&&&");
		log.info("&&&&& MediChartController selectSChart 전달받은 parameter값 : {}",map);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		String medi_content = (String) map.get("medi_content");
		String escapedContent = StringEscapeUtils.escapeHtml4(medi_content);
		String medi_visit = (String) map.get("medi_visit");
		String mpet_seq = (String)map.get("petName");
		String medi_title = (String)map.get("medi_title");
		String medi_l = (String) map.get("codeL");
		String medi_s = (String) map.get("codeS");
		
		service.selectAllMediCode();
		
		
		return "detailChart";
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
	
	@GetMapping(value = "/selectOneChart.do")
	public String selectOneChart(String medi_num, Model model){
		log.info("&&&&& MediChartController selectOneChart 전달받은 parameter값 : {}&&&&&",medi_num);
		
		PetsInfo_VO pvo =  service.selectOneChart(medi_num);
		model.addAttribute("pvo",pvo);
		
		return "detail";
	}

	
}
