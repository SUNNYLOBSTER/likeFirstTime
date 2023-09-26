package com.min.edu;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Mail_Controller {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMailTest() {
		log.info("&&&&& Mail_Controller 메일 보냅니다요 &&&&&");
		
		String subject = "[처음처럼] Email 인증번호 입니다.";
		String content = "메일 테스트 내용";
		String from = "likeFirstTime1010@gmail.com";
		String to = "beaver.kim0327@naver.com";
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			
			mailSender.send(mail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@PostMapping(path="/mailSend.do")
	@ResponseBody
	public String mailSend(String users_id, Model model) {
		log.info("&&&&& Mail_Controller 회원가입 페이지에서 이메일인증 버튼 클릭 {} &&&&&", users_id);
		System.out.println(users_id);
				
		//난수 생성
		int randomNum = RandomUtils.nextInt(100000, 1000000);
		System.out.println(randomNum);
		
		//메일보내기
		//제목
		String subject = "[퍼펫트케어] Email 인증번호 입니다.";
		//내용
		String content = "안녕하세요, 퍼펫트케어입니다. \n\n인증번호는 "+randomNum+"입니다.";
		//보내는 사람
		String from = "likeFirstTime1010@gmail.com";
		//받는 사람
		String to = users_id;
		
		//이메일 전송
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			
			mailSender.send(mail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//생성한 난수를 메일로 보낸 값과 비교하기 위해 ajax로 반환 
		String certNum = String.valueOf(randomNum);
		return certNum;
	}

}
