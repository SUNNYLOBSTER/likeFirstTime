package com.min.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class MediChartController {

	@Autowired
	private IMediChart_Service service;
	
	@GetMapping(value = "/selectAllChart.do")
	public String selectAllChart(Model model) {
		log.info("&&&&& MediChartController 메인화면 -> 전체진료기록페이지 &&&&&");
		
		String medi_id = "elsa@disney.com";
		
		List<PetsInfo_VO> allPets = service.searchPet(medi_id);
		model.addAttribute("allPets",allPets);
		
		List<MediChart_VO> allCharts = service.selectAllChart(medi_id);
		model.addAttribute("allCharts",allCharts);
		
		List<MediCode_VO> lists = new ArrayList<MediCode_VO>();
		
		List<MediCode_VO> mlists =  service.selectAllMediCode();
		for(int i=0; i<mlists.size();i++) {
			if (mlists.get(i).getMedi_code().length()==2) {
				
			}
		}
		
		
		MediCode_VO v0 = new MediCode_VO("00", "일반진료", "");
		MediCode_VO v1 = new MediCode_VO("01", "내과", "");
		MediCode_VO v2 = new MediCode_VO("02", "외과", "");
		MediCode_VO v3 = new MediCode_VO("03", "접종", "");
		lists.add(v0);
		lists.add(v1);
		lists.add(v2);
		lists.add(v3);
		
		model.addAttribute("codelists",lists);
		
		
		return "chart";
	}
	

	@PostMapping(value = "/listByCodeS.do")
	@ResponseBody
	public Map<String, Object> listByCodeS(String medicodeL){
		log.info("&&&&& MediChartController listByCodeS 전달받은 parameter 값 : {} &&&&&",medicodeL);
		
		// 서비스 실행 쿼리 결과
		
		Map<String, Object> mide = new HashMap<String, Object>();
		
		List<MediCode_VO> lists = new ArrayList<MediCode_VO>();
		MediCode_VO v1 = new MediCode_VO("011", "FF", "DD");
		MediCode_VO v2 = new MediCode_VO("012", "HH", "DD");
		MediCode_VO v3 = new MediCode_VO("013", "CC", "DD");
		lists.add(v1);
		lists.add(v2);
		lists.add(v3);
		
		mide.put("name", lists);
		
//		List<MediCode_VO> lists = service.selectAllMediCode();
//		if(medicodeL == lists.get(0).getMedi_name()) {
//			
//			
//		}
//		for (MediCode_VO m : lists) {
//			String code = m.getMedi_code();
//			System.out.println(code);
//		}
//		
//		if(medicodeL=="접종") {
//			String meditop = lists.get(3).getMedi_top();
//			System.out.println(meditop);
//		}
		
		return mide;
	}
	
	
	@GetMapping(value = "/selectPetChart.do")
	public String selectPetChart(String pet_name, Model model) {
		log.info("&&&&& MediChartController 전체진료기록 -> 반려동물별 진료기록페이지 &&&&&");
		log.info("&&&&& MediChartController selectPetChart 전달받은 parameter값 : {} &&&&&",pet_name);
		
		String pet_owner = "elsa@disney.com";
		
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
	
	
}
