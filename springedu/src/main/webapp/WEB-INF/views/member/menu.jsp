<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="url_modifyForm" value="${contextPath }/member/modifyForm.do"/>
<c:set var="url_chagePWForm" value="${contextPath }/member/chagePWForm.do" /> 
<c:set var="url_outMember" value="${contextPath }/member/outMemberForm.do"/>     
  <nav>
    <div class="container container-n">
      <ul>
        <li><a href="${url_modifyForm }">회원정보수정</a></li>
        <li><a href="${url_chagePWForm}">비밀번호수정</a></li>
        <li><a href="${url_outMember }">회원탈퇴</a></li>
      </ul>
    </div>
  </nav>