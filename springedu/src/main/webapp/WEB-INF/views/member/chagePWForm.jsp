<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>
<title>비밀번호변경</title>
<link rel="stylesheet" href="${contextPath }/css/main.css">
<style>
  /* 메뉴 영역 */
  nav{}
  nav > .container-n{ height:50px; background-color: #9e00c5; }
  nav > .container-n > ul{ display:flex; list-style-type: none; height:50px; width:500px; margin: 0px auto; line-height: 50px; }
  nav > .container-n > ul > li { width:100px; text-align: center; font-size:0.8rem; }
  nav > .container-n > ul > li > a{ color:white; text-decoration: none; font-weight: bold; }
  nav > .container-n > ul > li > a:hover{ text-decoration: underline; }
	
	main > .container > .content > section {
		padding: 50px;
		display:flex;
		justify-content: center;
	}
	
	main .container .content form{
		width: 200px;
		margin: 50px auto;

	}
	main .container .content form h1 {
		margin: 20px;
	}
	main .container .content form ul {
		list-style: none;
	}
	main .container .content form li {
		margin: 5px auto;
	}
	main .container .content form button{
		width:160px;
		padding:3px;
		background-color: lightgreen;
		border: none;
		outline: none;
	}
	
</style>
</head>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp" %>

  <!-- header -->
  <%--@ include file="/header.jsp" --%>

  <!-- 메뉴 -->
  <%@ include file="/WEB-INF/views/member/menu.jsp" %>
	  
	<main>
		<div class="container">
			<div class="content">
				<section>
					<form method="post" action="${contextPath}/member/chagePW">
					 <ul>
						<li><label for="id">아이디</label></li>					 
						<li><input type="text" name="id" readonly="readonly" value="${sessionScope.member.id}"></li>
						<li><label for="prepw">현재 비밀번호</label></li>
						<li><input type="password" name="prepw" id="prepw" placeholder="현재 비밀번호를 입력하세요" required="required"></li>
						<li><span class="errmsg">${requestScope.svr_msg }</span></li>
						<li><label for="postpw">변경할 비밀번호</label></li>
						<li><input type="password" name="postpw" id="postpw" placeholder="변경할 비밀번호를 입력하세요" required="required"></li>
						<li><span class="errmsg"></span></li>
						<li><button>비밀번호변경</button></li>
						</ul>
					</form>	
				</section>
			</div>
		</div>
	</main>

  <!-- 푸터 -->
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>  
</body>
</html>  