<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<c:set var="url_login" value="${contextPath }/loginForm"/>  
<c:set var="url_logout" value="${contextPath }/logout"/>  
<c:set var="url_myPage" value="${contextPath }/member/myPage"/>   
<%-- ${sessionScope.member } --%>  
<style>
	.uppermost .container.container-upm{
		display:flex;
		justify-content:space-between;
	}
	.uppermost .container.container-upm #logo {
		font-size : 2.3em;
	}
</style>       
  <div class="uppermost">
    <fmt:bundle basename="resource.menu">
  	<!-- 로그인 전 -->
  	<c:if test="${empty sessionScope.member}">
    <div class="container container-upm">
    	<p><a href="${contextPath }"><i id="logo" class="fab fa-pied-piper-alt"></i></a></p>
      <p><a href="${url_login }"><fmt:message key="menu.login"/></a><span> | </span><a href="${contextPath }/member/joinForm"><fmt:message key="menu.sign-in"/></a></p>
    </div>
    </c:if>
    <!-- 로그인 후  -->
    <c:if test="${!empty sessionScope.member}">
    <div class="container container-upm">
    	<p><a href="${contextPath }"><i id="logo" class="fab fa-pied-piper-alt"></i></a></p>
      <p><a href="${url_logout }"><fmt:message key="menu.logout"/></a><span> | </span>
      	 <a href="${url_myPage}">${sessionScope.member.nickname }<fmt:message key="menu.nickname"/></a>
      </p>      
    </div>
    </c:if>
    </fmt:bundle>
  </div>