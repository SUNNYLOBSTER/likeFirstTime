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
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		Map<String, Object> petMap = new HashMap<String, Object>();
		
		userMap.put("users_addr","주소입니다.");
		userMap.put("users_subtel","01236664555");
		userMap.put("users_id","soi_yeoa@naver.com");

		petMap.put("pet_owner", "soi_yeoa@naver.com");
		petMap.put("pet_name", "쪼푸");
		petMap.put("pet_bday", "20220731");
		petMap.put("pet_species", "D");
		petMap.put("pet_gender", "M");
		petMap.put("pet_neut", "N");
		petMap.put("pet_report", "기여운 우리 쪼푸");
		
		boolean isc = service.addInfo(userMap, petMap);
		assertTrue(isc);
		
	}

}
