<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>에러 페이지(DataAccessException)</title>
</head>
<body>
<h3>에러 페이지</h3>
<p>애플리케이션에 오류가 발생했습니다. 관리자에게 문의하세요</p>
<p>오류 페이지 : ${url }</p>
<p>예외유형 : ${exception.message }</p>
<!-- 
<c:forEach items="${exception.stackTrace }" var="ex">
	${ex }
</c:forEach>
-->
</body>
</html>