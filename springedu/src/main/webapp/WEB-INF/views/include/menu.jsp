<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <nav>
    <div class="container container-n">
    	<fmt:bundle basename="resource.menu">
      <ul>
        <!-- 웹표준 -->
        <li><a href="${contextPath }/board/list"><fmt:message key="menu.webstd"/></a></li>
        <!-- 자바 -->
        <li><a href="#"><fmt:message key="menu.java"/></a></li>
        <!-- 안드로이드 -->
        <li><a href="#"><fmt:message key="menu.android"/></a></li>
        <!-- 데이터베이스 -->
        <li><a href="#"><fmt:message key="menu.db"/></a></li>
        <!-- 스프링 -->
        <li><a href="#"><fmt:message key="menu.spring"/></a></li>
      </ul>
      </fmt:bundle>
    </div>
  </nav>