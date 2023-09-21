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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.min.edu.model.service.IReservation_Service;
import com.min.edu.model.service.ISchedule_Service;
import com.min.edu.vo.FullCalendar_VO;
import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Paging_VO;
import com.min.edu.vo.Reservation_VO;
import com.min.edu.vo.ResrvList_VO;
import com.min.edu.vo.SchedBoard_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Reservation_Controller {

   @Autowired
   private IReservation_Service service;
   
   @Autowired
   private ISchedule_Service sche_service;
   
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
      
      @SuppressWarnings("serial")
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
   
   @GetMapping(value = "/resrv_detailAjax.do")
   @ResponseBody
   public String resrv_detailAjax(String resrv_num, Model model) {
	   log.info("&&&&& Reservation_Controller resrv_detail 호출 &&&&&");
	   log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
	   Reservation_VO rvo = service.resrv_detail(resrv_num);
	   Gson gson = new Gson();
	   String json = gson.toJson(rvo);
	   return json;
   }
   
   @PostMapping(value = "/resrv_waitLists.do")
   @ResponseBody
   public String resrv_waitList(HttpSession session, String resrv_status) {
      log.info("&&&&& Reservation_Controller resrv_detail 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_status);
      
      Users_VO User_id = (Users_VO)session.getAttribute("loginVo");
      String hosp_id = User_id.getUsers_id();
      
    @SuppressWarnings("serial")
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
      Reservation_VO rvo = service.resrv_detail(resrv_num);
      String sche_id = rvo.getResrv_userid();
      String sche_date = rvo.getResrv_visit();
      String sche_title = "진료예약";
      String sche_content = rvo.getResrv_memo();
      String sche_hour = rvo.getResrv_time();
      String sche_minute = "00";
      System.out.println("#############"+sche_id);
      System.out.println("#############"+sche_date);
      System.out.println("#############"+sche_hour);
      SchedBoard_VO svo = new SchedBoard_VO();
      svo.setSche_id(sche_id);
      svo.setSche_date(sche_date);
      svo.setSche_title(sche_title);
      svo.setSche_content(sche_content);
      svo.setSche_hour(sche_hour);
      svo.setSche_minute(sche_minute);
      sche_service.insertNewSchedule(svo);
      return (n>0)?"confirm":"false";
   }
   
   @PostMapping(value = "/resrv_refuse.do")
   @ResponseBody
   public String resrv_refuse(String resrv_num, String user_id) {
      log.info("&&&&& Reservation_Controller resrv_refuse 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", user_id);
      int n = service.resrv_delete(resrv_num);
      return (n>0)?"resrv_refuse":"false";
   }
   
   @GetMapping(value = "/resrv_requestPage.do")
   public String resrv_requestPage(HttpSession session, HttpServletResponse response, String resrv_hops, Model model) throws IOException{
      log.info("&&&&& 예약신청 페이지 이동 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_hops);
      response.setContentType("text/html; charset=UTF-8;");
      Users_VO user_vo = (Users_VO) session.getAttribute("loginVo");
      System.out.println("#####"+user_vo);
      session.setAttribute("resrv_hops", resrv_hops);
      String hosp_name = service.hosp_name(resrv_hops);
      model.addAttribute("hosp_name", hosp_name);
      PrintWriter out = response.getWriter();
      if(user_vo != null) {
          if(resrv_hops != null) {
        	  Hospital_VO hosp_info = service.resrv_reqPage(resrv_hops);
        	  if(hosp_info != null) {
        		  model.addAttribute("hosp_time", hosp_info.getHosp_time());
                  model.addAttribute("user_vo", user_vo);
                  return "resrv_requestPage";  
        	  }else {
        		  Hospital_VO hosp_runTime = service.hosp_runTime(resrv_hops);
//        		  Gson gson = new Gson();
//        		  String hosp_run = gson.toJson(hosp_runTime.getHosp_time());
        		  model.addAttribute("hosp_runTime", hosp_runTime.getHosp_time());
        		  model.addAttribute("user_vo", user_vo);
        		  return "resrv_requestPage";
        	  }
               
          }else {
        	  out.print("<script>alert('병원을 다시 선택해주세요');location.href='./map.do'</script>");
        	  out.flush();
        	  out.close();
        	  return null;
          }
      }else {
    	  out.print("<script>alert('로그인 후 이용해주세요');location.href='./loginForm.do'</script>");
    	  out.flush();
    	  out.close();
    	  return null;
      }
      
      
   }
   
   @GetMapping(value = "/resrv_requestAjax.do")
   @ResponseBody
   public void resrv_requestAjax(HttpServletResponse response, HttpSession session, Model model) throws IOException {
     
	   String resrv_hops = (String)session.getAttribute("resrv_hops");
	   System.out.println("###############"+resrv_hops);
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
       out.flush();
       out.close();
   }
   
   @PostMapping(value = "/resrv_insert.do")
   public String resrv_insert(@RequestParam Map<String, Object> resrv_map) {
	   log.info("&&&&& Reservation_Controller resrv_insert &&&&&");
	   log.info("&&&&& 전달받은 파라미터 {} &&&&&", resrv_map);
	   String hosp = (String)resrv_map.get("resrv_hops");
	   String visit = (String)resrv_map.get("resrv_visit");
	   String time = ((String)resrv_map.get("resrv_time")).substring(0,2);
	   String name = (String)resrv_map.get("resrv_name");
	   String tel = (String)resrv_map.get("resrv_tel");
	   String memo = (String)resrv_map.get("resrv_memo");
	   String user_id = (String)resrv_map.get("resrv_userid");
	   Reservation_VO resrv_vo = new Reservation_VO();
	   resrv_vo.setResrv_hops(hosp);
	   resrv_vo.setResrv_visit(visit);
	   resrv_vo.setResrv_time(time);
	   resrv_vo.setResrv_name(name);
	   resrv_vo.setResrv_tel(tel);
	   resrv_vo.setResrv_memo(memo);
	   resrv_vo.setResrv_userid(user_id);
	   String resrv_num = service.resrv_insert(resrv_vo);
	   return "redirect:/resrv_detail.do?resrv_num="+resrv_num;
   }
   
   @GetMapping(value = "/resrv_recordList.do")
   public String resrv_recordList(String resrv_userid, Model model) {
	   log.info("&&&&& Reservation_Controller resrv_recordList &&&&&");
	   log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&",resrv_userid);

	   @SuppressWarnings("serial")
	Map<String, Object> map = new HashMap<String, Object>(){{
		   put("resrv_userid",resrv_userid);
		   put("first","1");
		   put("last","5");
	   }};
	   List<Reservation_VO> list = service.resrv_recordList(map);
	   List<ResrvList_VO> hosp_lits = new ArrayList<ResrvList_VO>();
	   for (int i = 0; i < list.size(); i++) {
		   ResrvList_VO rvo = new ResrvList_VO();
		   String hospName = service.hosp_name(list.get(i).getResrv_hops());
		   rvo.setResrv_num(list.get(i).getResrv_num());
		   rvo.setResrv_hops(hospName);
		   rvo.setResrv_visit(list.get(i).getResrv_visit());
		   rvo.setResrv_time(list.get(i).getResrv_time());
		   rvo.setResrv_name(list.get(i).getResrv_name());
		   rvo.setResrv_status(list.get(i).getResrv_status());
		   rvo.setResrv_memo(list.get(i).getResrv_memo());
		   hosp_lits.add(rvo);
	   }
//	   model.addAttribute("resrv_recordList", list);
	   model.addAttribute("hosp_lits", hosp_lits);
	   return "user_resrvRecord";
   }
   
   @PostMapping(value = "/resrv_detailModify.do")
   @ResponseBody
   public String resrv_detailModify(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
	   log.info("&&&&& Reservation_Controller resrv_detailModify &&&&&");
	   log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&",map);
	   response.setContentType("text/html; charset=UTF-8;");
	  
	   Reservation_VO rvo = new Reservation_VO();
	   rvo.setResrv_num((String) map.get("resrv_num"));
	   rvo.setResrv_visit((String)map.get("resrv_visit"));
	   String resrv_time = (String)map.get("resrv_time");
	   rvo.setResrv_time(resrv_time.substring(0,2));
	   rvo.setResrv_name((String)map.get("resrv_name"));
	   rvo.setResrv_tel((String)map.get("resrv_tel"));
	   if(map.get("resrv_memo") != null) {
		   rvo.setResrv_memo((String)map.get("resrv_memo"));
	   }else {
		   rvo.setResrv_memo("");
	   }
	   int n = service.resrv_detailModify(rvo);
	   if(n>0) {
		   return "true";
	   }else {
		   PrintWriter out = response.getWriter();
		   out.print("<script>수정 실패했습니다.</script>");
		   out.flush();
		   out.close();
		   return "redirect:/resrv_Select.do'";
	   }
   }
}