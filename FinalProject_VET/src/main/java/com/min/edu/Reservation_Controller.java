package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.min.edu.model.service.IReservation_Service;
import com.min.edu.vo.FullCalendar_VO;
import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Reservation_Controller {

   @Autowired
   private IReservation_Service service;
   
   @GetMapping(value = "/resrv_Select.do")
   public String resrv_Select() {
      log.info("&&&&& 홈 -> 진료예약 조회 &&&&&");
      return "hosp_resrvMNG";
   }
   
   @PostMapping(value = "/resrv_monthCount.do")
   @ResponseBody
   public Map<Object, Object> resrv_MonthCount(HttpSession session, String yyyy, Model model) {
      log.info("&&&&& Reservation_Controller resrv_monthCount 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", yyyy);
      // 받아올 값
      Users_VO hosp_id = (Users_VO) session.getAttribute("loginVo");
      
      Map<String, Object> map = new HashMap<String, Object>(){{
         put("yyyy",yyyy);
         put("RESRV_HOPS",hosp_id.getUsers_id());
      }};
      
      Map<Object, Object> resultMap = service.resrv_monthYNCount(map);
      
      return resultMap;
   }
   
   @GetMapping(value = "/fullCalendar.do")
   public void fullCalendar(HttpServletResponse response, HttpSession session) throws IOException {
      log.info("&&&&& Reservation_Controller fullCalendar 호출 &&&&&");
      Users_VO User_id = (Users_VO) session.getAttribute("loginVo");
      String hosp_id = User_id.getUsers_id();
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", hosp_id);
      
      List<FullCalendar_VO> resultList = new ArrayList<>();
      List<Reservation_VO> resrvList = service.resrv_ResrvLists(hosp_id);
      
      for (Reservation_VO rvo : resrvList) {
         FullCalendar_VO fvo = new FullCalendar_VO();
         fvo.setTitle(rvo.getResrv_name());
         fvo.setStart(rvo.getResrv_visit());
         fvo.setResrv_num(rvo.getResrv_num());
         resultList.add(fvo);
      }
      Gson gson = new Gson();
      String json = gson.toJson(resultList);
      PrintWriter out = response.getWriter();
      out.print(json);
      out.flush();
      out.close();
   }
   
   @GetMapping(value = "/resrv_detail.do")
   public String resrv_detail(String resrv_num, Model model) {
      log.info("&&&&& Reservation_Controller resrv_detail 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
      Reservation_VO rvo = service.resrv_detail(resrv_num);
      model.addAttribute("resrv_detail", rvo);
      return "resrv_detail";
   }
   
   @PostMapping(value = "/resrv_waitLists.do")
   @ResponseBody
   public String resrv_waitList(HttpSession session, String resrv_status) {
      log.info("&&&&& Reservation_Controller resrv_detail 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_status);
      
      Users_VO User_id = (Users_VO)session.getAttribute("loginVo");
      String hosp_id = User_id.getUsers_id();
      
      Map<String, Object> map = new HashMap<String, Object>(){{
         put("resrv_hops", hosp_id);
         put("resrv_status", resrv_status);
      }};
      List<Reservation_VO> lists = service.resrv_dayStatus(map);
      Gson gson = new Gson();
      String json = gson.toJson(lists);
      return json;
   }
   
   @PostMapping(value = "/resrv_confirm.do")
   @ResponseBody
   public String resrv_confirm(String resrv_num) {
      log.info("&&&&& Reservation_Controller resrv_confirm 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
      int n = service.resrv_updateToY(resrv_num);
      return (n>0)?"confirm":"false";
   }
   
   @PostMapping(value = "resrv_refuse.do")
   @ResponseBody
   public String resrv_refuse(String resrv_num) {
      log.info("&&&&& Reservation_Controller resrv_refuse 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
      int n = service.resrv_updateToN(resrv_num);
      return (n>0)?"resrv_refuse":"false";
   }
   
   @GetMapping(value = "resrv_requestPage.do")
   public String resrv_requestPage(HttpSession session, String resrv_hops, Model model){
      log.info("&&&&& 메인화면 -> 예약신청 페이지 이동 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_hops);
      Users_VO user_vo = (Users_VO) session.getAttribute("loginVo");
      session.setAttribute("resrv_hops", resrv_hops);
      Hospital_VO hosp_info = service.resrv_reqPage(resrv_hops);
      model.addAttribute("hosp_time", hosp_info.getHosp_time());
      model.addAttribute("user_vo", user_vo);
      
      
      return "resrv_requestPage";
   }
   
   @GetMapping(value = "resrv_requestAjax.do")
   @ResponseBody
   public void resrv_requestAjax(HttpServletResponse response, HttpSession session, Model model) throws IOException {
     
	   String resrv_hops = (String)session.getAttribute("resrv_hops");
       List<FullCalendar_VO> resultList = new ArrayList<>();
       
       List<Reservation_VO> rLists = service.resrv_reqCal(resrv_hops);
      
       Hospital_VO hosp_info = service.resrv_reqPage(resrv_hops); //불러오고
       System.out.println(rLists);
       // fvo에 운영시간 넣고 reqCal에서 클릭이벤트에서 해결
       for (Reservation_VO rvo : rLists) {
    	FullCalendar_VO fvo = new FullCalendar_VO();
		fvo.setStart(rvo.getResrv_visit());
		fvo.setResrv_num(rvo.getResrv_num());
		fvo.setHosp_time(hosp_info.getHosp_time());
		resultList.add(fvo);
       }
       Gson gson = new Gson();
       String json = gson.toJson(resultList);
       PrintWriter out = response.getWriter();
       out.print(json);
   }
}