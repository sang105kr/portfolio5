<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<!-- 절대경로 -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!-- 상대경로 -->
<%--@ include file="../include/common.jsp" --%>
<title>회원가입</title>
<link rel="stylesheet" href="${contextPath }/css/main.css">
</head>
<style>
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
	main form .err{
		color:red;
		font-size : 0.7em;
		font-weight: bold;
	}
</style>  	
<script>
	window.addEventListener("load",init);
	function init() {
		let joinBtn = document.getElementById('joinBtn');
		joinBtn.addEventListener("click", joinF);
	}
	function joinF(e) {
		e.preventDefault(); //기본이벤트 막기
		console.log(e.target);
		let joinForm = document.getElementById('joinForm');
		
		let idTag 			= document.getElementById('id');
		let pwTag 			= document.getElementById('pw');
		let pwchkTag 		= document.getElementById('pwchk');
		let emailTag 		= document.getElementById('email');
		let telTag 			= document.getElementById('tel');
		let nicknameTag = document.getElementById('nickname');
		let genderTags 	= document.querySelectorAll("input[name='gender']");
		let regionTag 	= document.getElementById('region');
		let birthTag    = document.getElementById('birth');
		
		//정규표현식 
		let idExpReg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-A]{2,3}$/i;
		//전화번호
		let telExpReg = /^01(?:0|1|[6-9])-(?:\d{3}|d{4}-\d{4}$)/;
		
		
		//아이디 체크
		if(idTag.value.trim().length == 0){
			//alert('아이디를 입력하세요');
			document.getElementById('err_id').textContent = '아이디를 입력하세요';
			idTag.select();
			return;
		}
		document.getElementById('err_id').textContent = '';
		
		if(idTag.value.trim().length > 40){
			alert('아이디는 40자 이하로 작성바랍니다.');
			idTag.select();
			return;
		}	
		if(!idExpReg.test(idTag.value)){
			alert('이메일 형식에 맞지 않습니다 ex)aaa@bbb.com');
			idTag.select();
			return;
		}
		
		//비밀번호체크
		if(pwTag.value.trim().length == 0){
			alert('비밀번호를 입력하세요');
			pwTag.select();
			return
		}
		if(pwTag.value.trim().length > 10){
			alert('비밀번호는 10자 이하로 작성바랍니다');
			pwTag.select();
			return
		}		
		if(pwchkTag.value.trim().length == 0){
			alert('비밀번호를 입력하세요');
			pwchkTag.select();
			return
		}
		if(pwTag.value.trim() != pwchkTag.value.trim()) {
			alert('비밀번호가 일치하지 않습니다');
			pwchkTag.select();
			return			
		}
	
		//전화번호체크
		if(telTag.value.trim().length == 0){
			alert('전화번호를 입력하세요');
			telTag.select();
			return;
		}		
		if(!telExpReg.test(telTag.value)){
			alert('전화번호 형식에 맞지 않습니다. ex)010-123-456')
			telTag.select();
			return;			
		}
		
		//별칭체크
		if(nicknameTag.value.trim().length == 0){
			alert('별칭을 입력하세요');
			nicknameTag.select();
			return;		
		}
		//성별체크
		let genderArray = Array.from(genderTags);		
		if((genderArray[0].checked || genderArray[1].checked) != true) {
			alert('성별을 체크하세요');
			genderTags[0].select();
			return;
		}
		//지역
		if(region.selectedIndex == 0) {
			alert('지역을 선택하세요');
			region.select();
			return;
		}
		//생년월일
		if(!birth.value){
			alert('생년월일을 입력하세요');
			birth.select();
			return;
		}
		//서버에 전송
		joinForm.method = 'post';
		joinForm.submit();
		
	}
</script>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp" %>

  <!-- header -->
  <%--@ include file="/header.jsp" --%>

  <!-- 메뉴 -->
  <%--@ include file="/menu.jsp" --%>
  
<main>
	<div class="container">
		<div class="content">
			<section>
				<form id="joinForm" method="post" action="${contextPath}/member/join.do">
				<h1>회원가입</h1>
					<ul>
						<li><label for="id">아이디</label></li>
						<li><input type="text" name="id" id="id" /></li>
						<li><span class="err err_id" id="err_id"></span></li>
						<li><label for="pw">비밀번호</label></li>
						<li><input type="password" name="pw" id="pw" /></li>
						<li><label for="pwchk">비밀번호확인</label></li>
						<li><input type="password" name="pwchk" id="pwchk" /></li>
						<li><label for="tel">전화번호</label></li>
						<li><input type="tel" name="tel" id="tel" /></li>
						<li><label for="nickname">별칭</label></li>
						<li><input type="text" name="nickname" id="nickname" /></li>
						<li><label>성별</label></li>
						<li><input type="radio" name="gender" value="남" id="men" /><label
							for="men">남자</label> <input type="radio" name="gender" value="여"
							id="women" /><label for="women">여자</label></li>
						<li><label for="region">지역</label></li>
						<li><select name="region" id="region">
								<option value="">== 선 택==</option>
								<option value="서울">서울</option>
								<option value="부산">부산</option>
								<option value="대구">대구</option>
								<option value="울산">울산</option>
								<option value="대전">대전</option>
						</select></li>
						<li><label for="birth">생년월일</label></li>
						<li><input type="date" name="birth" id="birth" /></li>
						<li>&nbsp;</li>
						<li><input id='joinBtn' type="submit" value="회원가입" /></li>
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