<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<!-- 요청URL  : http://localhost:9080/portfilio/rtest/11/{num} -->
<!-- 요청파라미터 : 찾고사하는 사람의 번호 문자(4) -->
<!-- 응답포맷 : 
			응답코드 2자리
			응답메세지: 10자리
			응답데이터 : 이름 10자리
			                     나이 3자리 
-->
<!-- 데이터포맷 : json -->
<!--  -->
<script>
	window.addEventListener("load", init);
	function init() {
		const findBtn = document.getElementById('findBtn');
		findBtn.addEventListener("click", find_f);
	}
	function find_f(event) {
		console.log(event.target.id);
		const numTag = document.getElementById('num');
		if(!numTag.value) {
			const errmsg = document.getElementById('errmsg');
			errmsg.textContent = "번호를 입력바랍니다!!"
			errmsg.style.color = "red";
			numTag.select();
			return false;
		}
		
		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();

		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4) {
				console.log(this.responseText);

				//json포맷 문자열 => 자바스크립트 ojb
				const jsonObj = JSON.parse(this.responseText);

				if (this.status == 200 || this.status == 400) {
					switch (jsonObj.rtcode) {
					case "00":
						//이름
						document.getElementById('name').textContent = jsonObj.result.name;
						document.getElementById('age').textContent = jsonObj.result.age;
						document.getElementById('errmsg').textContent = jsonObj.msg;
						break;
					case "01":
						document.getElementById('errmsg').textContent = jsonObj.msg;
						document.getElementById('name').textContent = '';
						document.getElementById('age').textContent = '';						
						break;
					}
				}

			}
		}

		//3)요청 파라미터만들기(json포맷) 
		/*         const reqParam = {};
		 reqParam.tel = telTag.value;
		 reqParam.birth = birthTag.value;
		 //js객체를 json포맷 문자열로 변환JSON.stringify()
		 //json포맷 문자열을 js객체로 변환JSON.parse()
		 const result = JSON.stringify(reqParam); */

		//4)서비스요청
		xhttp.open("GET", "http://localhost:9080/portfolio/rtest/11/" + num.value);
		/*         xhttp.setRequestHeader(
		 "Content-Type",
		 "application/x-www-form-urlencoded"
		 ); */
		xhttp.send(null);
	}
</script>
</head>
<body>
	<h3>사람을 찾습니다</h3>
	<input type="text" name="num" id="num" />
	<button id="findBtn">찾기</button>
	<div id="errmsg"></div>
	<div id="name"></div>
	<div id="age"></div>
</body>
</html>







