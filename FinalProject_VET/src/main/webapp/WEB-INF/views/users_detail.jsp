<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 상세조회</title>
</head>
<body>
<%@ include file="./navbar.jsp" %>
   <div class="navContainer">
<%--    ${lists} --%>
   
      <h3>내 정보</h3>
      <table>
         <tr>
            <th>ID</th>
            <td>${lists[0].users_id}</td>
         </tr>
         <tr>
            <th>이름</th>
            <td>${lists[0].users_name}</td>
         </tr>
         <tr>
            <th>전화번호</th>
            <td>${lists[0].users_tel}</td>
         </tr>
         <tr>
            <th>주소</th>
            <td>${lists[0].users_addr}</td>
         </tr>
         <tr>
            <th>가입일</th>
            <td>${lists[0].users_joindate}</td>
         </tr>
         <tr>
            <th>추가 연락처</th>
            <td>${lists[0].users_subtel}</td>
         </tr>
      </table>
      
      <hr>
      
      <input type="button" id="fixButton3" value="내 반려동물 보기" onclick="location.href='./selectPetsDetail.do'">
      <input type="button" id="fixButton2" value="내 정보 수정" onclick="location.href='./updateUser.do'">
      <input type="button" id="fixButton" value="회원탈퇴" onclick="location.href='./resignUser.do'">
   </div>
</body>
<%@ include file="./footer.jsp" %>
</html>