<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>

<title>게시글 작성</title>
<link rel="stylesheet" href="${contextPath }/css/main.css">
</head>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp" %>

  <!-- header -->
  <%@ include file="/WEB-INF/views/include/header.jsp" %>

  <!-- 메뉴 -->
  <%@ include file="/WEB-INF/views/include/menu.jsp" %>

  <!-- 본문 -->
  <main>
    <div class="container">
      <div class="content">  
	게시글 작성
			</div>
		</div>
	</main>
  <!-- 푸터 -->
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>  

</body>
</html>