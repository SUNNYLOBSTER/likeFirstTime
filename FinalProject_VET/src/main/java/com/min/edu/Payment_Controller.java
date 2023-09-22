package com.min.edu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.min.edu.model.service.IPayment_Service;
import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.Payment_VO;
import com.min.edu.vo.Point_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;

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
		
		@SuppressWarnings("serial")
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
	
	@PostMapping(value = "/selectOnePayment.do")
	@ResponseBody
	public Payment_VO selectOnePayment(String pay_num){
		log.info("&&&&& Payment_Controller insertNewPayment 전달받은 parameter값 : {} &&&&&",pay_num);
		Payment_VO pvo = service.selectOnePayment(pay_num);
		
		return pvo;
	}
	
	@GetMapping(value = "/goCancel.do")
	public String goCancel(String pay_num, Model model) {
		log.info("&&&&& Payment_Controller 결제화면 or 마이페이지 -> 결제내역 전체조회 &&&&&");
		log.info("&&&&& Payment_Controller insertNewPayment 전달받은 parameter값 : {} &&&&&",pay_num);
		
		Payment_VO pvo = service.selectOnePayment(pay_num);
		model.addAttribute("pvo",pvo);
		return "payment_cancel";
	}
	
	@PostMapping(value = "/cancelPayment.do")
	@ResponseBody
	public String cancelPayment(String merchant_uid, String cancel_request_amount) throws IOException {
		log.info("&&&&& Payment_Controller cancelPayment 전달받은 parameter값 : {} {}&&&&&",merchant_uid,cancel_request_amount);
		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		
//		JsonObject body = new JsonObject();
//		body.addProperty("imp_key", "imp40440345");
//		body.addProperty("imp_secret", "A7knp2NqgAVHcjwhHoAqTSqldQsaDnByW7ShqoJPZYtQfXdlmRpeqJsLkQpubXKxzR2bSlXV8V0Q64tu");
//		
//		HttpEntity<JsonObject> entity = new HttpEntity<JsonObject>(body,headers);
//		ResponseEntity<JSObject> token = restTemplate.postForEntity("https://api.iamport.kr/users/getToken", entity, JSObject.class);
//		
//		System.out.println(token+"fullToken");
//		System.out.println(token.getStatusCode()+"getToken");
//		System.out.println(token.getStatusCodeValue()+"getValToken");
//		System.out.println(token.getBody()+"bodyToken");
//		System.out.println(token.getBody().getMember("response")+"bodyToken");
		
		HttpURLConnection conn = null;
		String access_token = null;
		URL url = new URL("https://api.iamport.kr/users/getToken");
		conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		
		JsonObject obj = new JsonObject();
		obj.addProperty("imp_key", "imp40440345");
		obj.addProperty("imp_secret", "A7knp2NqgAVHcjwhHoAqTSqldQsaDnByW7ShqoJPZYtQfXdlmRpeqJsLkQpubXKxzR2bSlXV8V0Q64tu");
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(obj.toString());
		bw.flush();
		bw.close();
		
		int result = 0;
		int responseCode = conn.getResponseCode();
		System.out.println("######응답코드는?" + responseCode);
		
		return null;
	}
	
}
