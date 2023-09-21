package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.Users_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Users_JUnitTest {
	
	@Autowired
	private IUsers_Service service;
	
	@Test
	public void test() {

//		List<Users_VO> lists = service.selectAllUsers();

//		String auth = "U";
//		List<Users_VO> lists = service.selectUsersAuth(auth);

//		String status = "N";
//		List<Users_VO> lists = service.selectUsersStatus(status);
		
//		String keyword = "d";
//		List<Users_VO> lists = service.searchUsers(keyword);
		
//		String userId = "elsa@disney.com";
//		List<Users_VO> lists = service.selectUserDetail(userId);

//		String hospitalId = "gunu@naver.com";
//		Users_VO vo = service.selectHospitalDetail(hospitalId);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("users_id", "elsa@disney.com");
//		map.put("users_pw", "elsa1234");
//		Users_VO vo = service.loginUser(map);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("users_id", "ariel1234@disney.com");
//		map.put("users_pw", "ariel1234");
//		map.put("users_name", "박아리엘");
//		map.put("users_tel", "0318268826");
		
		
//		int n = service.duplicationId("elsa@disney.com");
//		assertEquals(1, n);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("users_id","chara@naver.com");
		map.put("users_pw","chara1234");
		map.put("users_name","차라동물병원");
		map.put("users_addr","경기도 양주시 백석읍 꿈나무로299 동화2차상가 1층");
		map.put("users_tel","0314568978");
		map.put("users_crn","7895645789");

		map.put("hosp_id", "chara@naver.com");
		map.put("hosp_name", "차라동물병원");
		map.put("hosp_time", "{\"open\":\"09\", \"close\":\"18\"}");
		map.put("hosp_off", "월");
		map.put("hosp_park", "Y");
		map.put("hosp_etc", "고양이 전문 병원 입니다.");
		map.put("hosp_mng", "G");
		
		boolean isc = service.insertHosp(map);
		assertTrue(isc);
		
	}

}
