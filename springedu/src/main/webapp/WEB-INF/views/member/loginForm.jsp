<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/common.jsp" %>

<title>로그인</title>
<style>
* {
	/* outline: 1px solid red;*/
	box-sizing: border-box;
}

.container .content {
	display: flex;
	justify-content: center;
	margin-top: 100px;
}

.container .content form {
	display: flex;
	flex-direction: column;
	width: 320px;
	border: 1px solid lightgray;
}

.container .content form .item:nth-child(1) {
	margin-top: 10px;
	margin-bottom: 50px;
	padding: 10px;
}

.container .content form .item:nth-child(1) img {
	width: 100%;
	height: 100%;
}

.container .content form .item {
	margin: 5px 0px;
	padding: 3px;
}

.container .content form #id, .container .content form #pw {
	width: 90%;
	padding: 5px;
}

.far.fa-envelope, .fas.fa-key {
	font-size: 1.5em;
}

.container .content form #loginBtn {
	width: 100%;
	padding: 10px;
	border: none;
	outline: none;
	background-color: green;
	color: white;
	font-family: "Nanum Gothic Coding", monospace;
	font-weight: 700;
	letter-spacing: 1em;
}

.container .content form #loginBtn:hover {
	background-color: rgba(2, 29, 2, 0.7);
	transition: all 0.7s;
}

.container .content form .find_info {
	text-align: center;
	font-size: 0.7em;
	padding: 10px;
	border-top: 1px solid rgba(0, 0, 0, 0.05);
}

.container .content form #login_chk {
	font-size: 0.8em;
}

.container .content form .errmsg {
	color: red;
	font-weight: bold;
	font-size: 0.7em;
}

</style>
<script>
	window.addEventListener("load",init);
	function init() {
		let loginBtn = document.querySelector('#loginBtn');
		let findIDTag = document.querySelector('#findID');
		let findPWTag = document.querySelector('#findPW');
		
		loginBtn.addEventListener("click",loginF);
		findIDTag.addEventListener("click",findID_F);
		findPWTag.addEventListener("click",findPW_F);
	}
	function loginF(e){
		e.preventDefault();//<button>요서의 서버전송 기본 이벤트 중단
		let loginForm = document.querySelector('#loginForm');
		let idTag 		= document.querySelector('#id');
		let pwTag 		= document.querySelector('#pw');
		let errmsg_id = document.querySelector('#errmsg_id');
		let errmsg_pw = document.querySelector('#errmsg_pw');
		
		//아이디체크
		if(!idTag.value.trim()){
			errmsg_id.textContent='아이디를 입력해주세요.';
			idTag.select();
			return false;
		}
		errmsg_id.textContent='';
		
		//비밀번호체크
		if(!pwTag.value.trim()){
			errmsg_pw.textContent='비밀번호를 입력해주세요.';
			pwTag.select();
			return false;			
		}
		errmsg_pw.textContent='';
		
		//서버전송
		loginForm.submit();
	}
	function findID_F(event){
		event.preventDefault();
		console.log("findID");
		const url 		= event.target.href;
		const fname 	= event.target.id;
		const top  = document.getElementById('loginForm').getBoundingClientRect().top+112;
		const left = document.getElementById('loginForm').getBoundingClientRect().left+26;
		console.log(top,left);
		const option 	= "width=250,height=300,top="+top+",left="+left+",location=no,resizable=no";
		console.log(option);
		window.open(url,fname,option);
		
	}
	function findPW_F(event){
		event.preventDefault();
		console.log("findPW");
		const url 		= event.target.href;
		const fname 	= event.target.id;
		const top  = document.getElementById('loginForm').getBoundingClientRect().top+112;
		const left = document.getElementById('loginForm').getBoundingClientRect().left+26;
		const option 	= "width=250,height=400,top="+top+",left="+left+",location=no,resizable=no";
		window.open(url,fname,option);		
	}
</script>
</head>
<body>
	<div class="container">
		<div class="content">
			<form id="loginForm" method="post" action="${contextPath }/member/login.do">
				<div class="item">
					<a href="${contextPath }">
						<img src="${contextPath }/img/car.jpg" alt="" />
					</a>
				</div>
				<div class="item">
					<input type="text" name="id" id="id" placeholder="아이디" /> <i
						class="far fa-envelope"></i>
				</div>
				<div class="item">
					<span class="errmsg" id="errmsg_id"></span>
				</div>
				<div class="item">
					<input type="password" name="pw" id="pw" placeholder="비밀번호" /> <i
						class="fas fa-key"></i>
				</div>
				<div class="item">
					<span class="errmsg" id="errmsg_pw">${requestScope.errmsg }</span>
				</div>
				<div class="item">
					<button id="loginBtn">로그인</button>
				</div>
				<div class="item">
					<input type="checkbox" name="login_chk" id="login_chk" /> <label
						for="login_chk" id="login_chk">로그인상태유지</label>
				</div>
				<div class="item find_info">
					<a href="${contextPath }/member/findIDForm.do" id="findID">아이디 찾기</a> <span>|</span> 
					<a href="${contextPath }/member/findPWForm.do" id="findPW">비밀번호 찾기</a> <span>|</span>
					<a href="${contextPath }/member/joinForm.do">회원 가입</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>