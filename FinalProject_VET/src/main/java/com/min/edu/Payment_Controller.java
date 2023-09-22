package com.min.edu;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.model.service.IPayment_Service;
import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.Payment_VO;
import com.min.edu.vo.Point_VO;
import com.min.edu.vo.Users_VO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

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
	public String cancelPayment(String merchant_uid, String cancel_request_amount, String imp_uid, HttpSession session) throws IOException {
		log.info("&&&&& Payment_Controller cancelPayment 전달받은 parameter값 : {} {} {}&&&&&",merchant_uid,cancel_request_amount,imp_uid);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pnt_id = loginVo.getUsers_id();
		int pnt_point = Integer.parseInt(cancel_request_amount)*(-1);
		
		CancelData cancelData = new CancelData(merchant_uid, true);
		
		try {
		    IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancelData);
		    System.out.println(payment_response);

		    Payment canceledPayment = payment_response.getResponse();
		    if (canceledPayment != null) {
		        System.out.println("결제 성공 취소 : " + canceledPayment.getStatus());
		        service.canclePayment(merchant_uid);
		        @SuppressWarnings("serial")
		        Map<String, Object> map = new HashMap<String, Object>(){{
		            put("pnt_id", pnt_id);
		            put("pnt_point", pnt_point);
		        }};
		        service.insertNewPnt(map);
		    } else {
		        System.out.println("결제 취소 응답에서 취소된 결제 정보를 가져올 수 없습니다.");
		    }
		} catch (IamportResponseException e) {
		    System.out.println("결제취소 실패");
		    System.out.println(e.getMessage());

		    switch (e.getHttpStatusCode()) {
		        case 401:
		            break;
		        case 500:
		            break;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

		
		return "redirect:/selectAllPayment.do";
	}
	
	IamportClient client;
	
	public Payment_Controller() {
		this.client = this.getTestClient();
	}
	
	IamportClient getTestClient() {
		
		String test_api_key = "2602544483040872";
		String test_api_secret = "A7knp2NqgAVHcjwhHoAqTSqldQsaDnByW7ShqoJPZYtQfXdlmRpeqJsLkQpubXKxzR2bSlXV8V0Q64tu";
		return new IamportClient(test_api_key, test_api_secret);
	}
	
	void getToken() {
		try {
			IamportResponse<AccessToken> auth_response = client.getAuth();
			assertNotNull(auth_response.getResponse());
			assertNotNull(auth_response.getResponse().getToken());
			
			System.out.println("get token : "+ auth_response.getResponse().getToken());
		} catch (IamportResponseException e) {
				System.out.println(e.getMessage());
				switch (e.getHttpStatusCode()) {
				case 401:  System.out.println("401");break;					
				case 500 :  System.out.println("500");break;
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}