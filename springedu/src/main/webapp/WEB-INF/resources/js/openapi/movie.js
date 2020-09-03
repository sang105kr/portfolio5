'use strict'

	const movieBodyTag = document.querySelector('.movie-body');
	const movieDailyBtn = document.querySelector('.movieDailyBtn');
	
	
	//조회날짜 이벤트 등록
	movieDailyBtn.addEventListener("click",(e)=>{
//		console.log("클릭!"+e.target);
//		console.log(document.querySelector('.movieDaily').value);
		const searchDay = document.querySelector('.movieDaily')
															.value
															.replaceAll('-','');
		
		rankDailyMovie(searchDay);
	});
	
	
	const today = new Date();
	today.setDate(today.getDate()-1);  //오늘날짜에 하루 빼기
	
	rankDailyMovie(getFormatDate(today));
	//console.log("요청일:"+getFormatDate(new Date()));
	
	//날짜를 입력받아 8자리(yyyyMMdd)포맷으로 변환해주는 함수(ex: 20200902)
	function getFormatDate(date){
		const l_year 	=	date.getFullYear();
		
		const l_month_tmp = date.getMonth() + 1;
		const l_month = l_month_tmp < 10 ? '0' + l_month_tmp : l_month_tmp;
		
		const l_date	= date.getDate()  < 10 ? '0' + date.getDate()  : date.getDate();	
		return l_year+l_month+l_date;
	}

	function rankDailyMovie(day){
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
				console.log(jsonObj.boxOfficeResult.dailyBoxOfficeList);
				
				const list = jsonObj.boxOfficeResult.dailyBoxOfficeList
				let str = "";
				list.forEach((movie)=>{
					str += `<tr>`;
					str += `  <td>${movie.rank}</td>`;
					str += `  <td>${movie.movieNm}</td>`;
					str += `  <td>${movie.openDt}</td>`;
					str += `  <td>${movie.salesAmt}</td>`;
					str += `  <td>${movie.audiCnt}</td>`;
					str += `</tr>`;				
				});
				
				movieBodyTag.innerHTML = str;
			}
		});
		
		//3) 요청메서드 + 요청URL
		const l_url = "http://localhost:9080/portfolio/movie/rank/"+day;
		xhttp.open('GET', l_url);
			
		//6) 요청
		xhttp.send(null);	
	}