<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script>
window.addEventListener("load",init);
function init(){
	const btnTag = document.getElementById('btn');
	btnTag.addEventListener("click",btn_f);
}
function btn_f(evnet){
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
		if (this.readyState == 4 && this.status == 400) {
			console.log(this.responseText);
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
	xhttp.open(
			"GET", 
			"http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd?ServiceKey=WSUnQ5U1Vm8Q9RY89hM7PZfOXVCkOzSXwAV%2BiX6s3fLs9nW5tpYtIZhfDoKdPH065PQF%2FLZGueqX%2FGNBPCgujg%3D%3D&searchSe=road&srchwrd=%EC%84%B8%EC%A2%85%EB%A1%9C%2017");
	xhttp.send(null);
}

</script>
</head>
<body>
http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd?ServiceKey=WSUnQ5U1Vm8Q9RY89hM7PZfOXVCkOzSXwAV%2BiX6s3fLs9nW5tpYtIZhfDoKdPH065PQF%2FLZGueqX%2FGNBPCgujg%3D%3D&searchSe=road&srchwrd=%EC%84%B8%EC%A2%85%EB%A1%9C%2017


<button id="btn">클릭</button>
</body>
</html>