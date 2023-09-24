package com.min.edu;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Mail_Controller {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMailTest() {
		log.info("&&&&& Mail_Controller 메일 보냅니다요 &&&&&");
		
		String subject = "제목입니다";
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
	
	@GetMapping(path="/mailCheck.do")
	public void mailCheckGet(String email) {
		log.info("&&&&& Mail_Controller 회원가입 페이지에서 이메일인증 버튼 클릭 {} &&&&&", email);
		
	}

}
