package com.min.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.model.service.IPayment_Service;
import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.Payment_VO;
import com.min.edu.vo.Point_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Payment_Controller {
	
	@Autowired
	IPayment_Service service;
	
	@Autowired
	private IUsers_Service user_service;
	
	
	@GetMapping(value = "/goPayment.do")
	public String goPayment(HttpSession session, Model model) {
		log.info("&&&&& 결제버튼 -> 결제화면 &&&&&");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pay_id = loginVo.getUsers_id();
		
		List<Users_VO> uvo = user_service.selectUserDetail(pay_id);
		String buyer_tel = uvo.get(0).getUsers_tel();
		String buyer_addr = uvo.get(0).getUsers_addr();
		String buyer_name = uvo.get(0).getUsers_name();
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("buyer_tel", buyer_tel);
			put("buyer_addr", buyer_addr);
			put("buyer_name", buyer_name);
			put("buyer_email", pay_id);
		}};
		
		model.addAttribute("buyer_info",map);
		
		return "payment_main";
	}

	
	@PostMapping(value = "/insertNewPayment.do")
	@ResponseBody
	public String insertNewPayment(@RequestParam Map<String, Object> map) {
		log.info("&&&&& Payment_Controller insertNewPayment 전달받은 parameter값 : {} &&&&&",map);
		
		String pay_id = (String) map.get("pay_id");
		String amount = (String) map.get("pay_amount");
		int pay_amount = Integer.parseInt(amount);
		String pay_code = (String) map.get("pay_code");
		String merchant_uid = (String) map.get("merchant_uid");
		System.out.println("**************"+merchant_uid);
		
		@SuppressWarnings("serial")
		Map<String, Object> payMap = new HashMap<String, Object>(){{
			put("pay_id", pay_id);
			put("pay_amount", pay_amount);
			put("pay_code",pay_code);
			put("merchant_uid",merchant_uid);
		}};
		
		@SuppressWarnings("serial")
		Map<String, Object> pointMap = new HashMap<String, Object>(){{
			put("pnt_id", pay_id);
			put("pnt_point",pay_amount);
		}};
						
		int n = service.insertNewPayment(payMap);
		int m = service.insertNewPnt(pointMap);
		
		return ((n>0)&&(m>0))?"true":"false";
	}
	
	@GetMapping(value = "/selectAllPayment.do")
	public String selectAllPayment(HttpSession session, Model model) {
		log.info("&&&&& Payment_Controller 결제화면 or 마이페이지 -> 결제내역 전체조회 &&&&&");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pay_id = loginVo.getUsers_id();
		
		List<Payment_VO> lists = service.selectAllPayment(pay_id);
		model.addAttribute("lists",lists);
		
		return "payment_list";
	}
	
	@GetMapping(value = "/selectPntList.do")
	public String selectPntList(HttpSession session, Model model) {
		log.info("&&&&& Payment_Controller 마이페이지 -> 포인트내역 전체조회 &&&&&");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pnt_id = loginVo.getUsers_id();
		
		List<Point_VO> lists = service.selectPntList(pnt_id);
		model.addAttribute("lists",lists);
		
		int point = service.selectAllPnt(pnt_id);
		model.addAttribute("point",point);
		
		return "payment_pointList";
	}
	
	
}
