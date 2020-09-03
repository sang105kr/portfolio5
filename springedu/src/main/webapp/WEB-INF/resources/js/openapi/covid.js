'use strict'

	const movieBodyTag = document.querySelector('.movie-body');


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
			
			const parser = new DOMParser();
			const xmlDoc = parser.parseFromString(e.target.response,"text/xml");
			console.log('첫번째 레코드 출력');
			console.log(xmlDoc.querySelector('items item').innerHTML);
			
			const list = xmlDoc.querySelectorAll('items item');
			
			let str = "";
			list.forEach((item)=>{
				//console.log("gg:"+item.querySelector('stateDt').textContent);
				
				str += `<tr>`;
				str += `  <td>${item.querySelector('stateDt').textContent}</td>`;
				str += `  <td>${item.querySelector('decideCnt').textContent}</td>`;
				str += `  <td>${item.querySelector('clearCnt').textContent}</td>`;
				str += `  <td>${item.querySelector('examCnt').textContent}</td>`;
				str += `  <td>${item.querySelector('deathCnt').textContent}</td>`;
				str += `</tr>`;
								
			});

			movieBodyTag.innerHTML = str;
		}
	});
	
	//3) 요청메서드 + 요청URL
	const l_url = "http://localhost:9080/portfolio/covid/20200901";
	xhttp.open('GET', l_url);
		
	//6) 요청
	xhttp.send(null);	
