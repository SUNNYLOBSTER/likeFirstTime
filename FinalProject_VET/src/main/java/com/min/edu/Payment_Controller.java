package com.min.edu;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.model.service.IPayment_Service;
import com.min.edu.vo.Payment_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Payment_Controller {
	
	@Autowired
	IPayment_Service service;
	
	
	@GetMapping(value = "/goPayment.do")
	public String goPayment() {
		log.info("&&&&& 결제버튼 -> 결제화면 &&&&&");
		return "payment_main";
	}

	
	@PostMapping(value = "/insertNewPayment.do")
	public String insertNewPayment(@RequestParam Map<String, Object> map) {
		log.info("&&&&& Payment_Controller 결제화면 -> 결제완료화면 &&&&&");
		log.info("&&&&& Payment_Controller insertNewPayment 전달받은 parameter값 : {} &&&&&",map);
		
		String pay_code = (String) map.get("pay_code");
		String pay_id = (String) map.get("pay_id");
		String amount = (String) map.get("pay_amount");
		int pay_amount = Integer.parseInt(amount);
		
		Map<String, Object> inMap = new HashMap<String, Object>(){{
			put("pay_code", pay_code);
			put("pay_id", pay_id);
			put("pay_amount", pay_amount);
		}};

		int n = service.insertNewPayment(inMap);
		
		return (n>0)?"payment_payment":"";
	}
}
