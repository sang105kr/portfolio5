<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>
<title>내정보</title>
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
	main .container .content form input[type=submit]{
		width:160px;
		padding:3px;
		background-color: lightgreen;
		border: none;
		outline: none;
	}

</style>
<script>
	const modifyBtn_f = (event) => {
		event.preventDefault();
		console.log('modifyBtn 클릭!');
		const pwTag 		= document.getElementById("pw");
		const errmsg_pw = document.getElementById("errmsg_pw");
		if(!pwTag.value.trim()) {
			errmsg_pw.textContent = '비밀번호를 입력하세요';
			pwTag.select();
			return;
		}
		document.getElementById('modifyForm').submit();
	}

	const init = () => {
		const modifyBtn = document.getElementById("modifyBtn"); 
		modifyBtn.addEventListener("click",modifyBtn_f);
	}

	window.addEventListener("load",init);

</script>		

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
					<form id="modifyForm" method="post"
							action="${contextPath}/member/modify">
							<ul>
								<li><label for="id">아이디</label></li>
								<li><input type="text" name="id" id="id" value='${sessionScope.member.id }' readonly="readonly"/></li>
								<li><label for="pw">비밀번호</label></li>
								<li><input type="password" name="pw" id="pw"/></li>
								<li><span class="errmsg" id="errmsg_pw">${requestScope.svr_msg }</span></li>								
								<li><label for="tel">전화번호</label></li>
								<li><input type="tel" name="tel" id="tel" value='${sessionScope.member.tel }'/></li>
								<li><label for="nickname">별칭</label></li>
								<li><input type="text" name="nickname" id="nickname" value='${sessionScope.member.nickname }'/></li>
								<li><label>성별</label></li>
								<li><input type="radio" name="gender" value="남" id="men" ${sessionScope.member.gender == "남" ? 'checked="checked"' : '' }/><label
									for="men">남자</label> <input type="radio" name="gender"
									value="여" id="women" ${sessionScope.member.gender == "여" ? 'checked="checked"' : '' }/><label for="women">여자</label>
								</li>
								<li><label for="region">지역</label></li>
								<li><select name="region" id="region">
										<option value="">== 선 택==</option>
										<option value="서울" ${sessionScope.member.region == "서울" ? 'selected="selected"' : '' }>서울</option>
										<option value="부산" ${sessionScope.member.region == "부산" ? 'selected="selected"' : '' }>부산</option>
										<option value="대구" ${sessionScope.member.region == "대구" ? 'selected="selected"' : '' }>대구</option>
										<option value="울산" ${sessionScope.member.region == "울산" ? 'selected="selected"' : '' }>울산</option>
										<option value="대전" ${sessionScope.member.region == "대전" ? 'selected="selected"' : '' }>대전</option>
								</select></li>
								<li><label for="birth">생년월일</label></li>
								<li><input type="date" name="birth" id="birth" value='${sessionScope.member.birth }'/></li>
								<li>&nbsp;</li>
								<li><input id="modifyBtn" type="submit" value="회원수정" /></li>
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