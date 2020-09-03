'use strict'

	const movie_rankTag = document.getElementById('movie_rank');

	//1)XMLHTTPRequest 객체 생성
	const xhttp = new XMLHttpRequest();
	//2)서버응답 처리
	//readyState
	// 0 : open()가 호출되지 않은 상태
	// 1 : open()가 실행된 상태 server 연결됨
	// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
	// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
	// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
	xhttp.addEventListener("readystatechange",(e)=>{
		if(e.target.readyState == 4 && e.target.status == 200){
			console.log(e.target.response);
			if(!e.target.response) return;
			
			const jsonObj = JSON.parse(e.target.response);
			console.log(jsonObj);
			movie_rankTag.innerHTML = e.target.response;
		}
	});
	
	//3) 요청메서드 + 요청URL
	const l_url = "http://localhost:9080/portfolio/movie/rank";
	xhttp.open('GET', l_url);
		
	//6) 요청
	xhttp.send(null);	