<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<title>아이디 찾기</title>
<style>
  #findIDForm * {
  	box-sizing: border-box;
  }
	#findIDForm input {
			width:100%;
	}
	#findIDForm button {
		width: 100%;
		padding: 7px;
		border: none;
		outline: none;
		background-color: green;
		color: white;
		font-family: "Nanum Gothic Coding", monospace;
		font-weight: 700;
		letter-spacing: 1em;
		border-radius: 5px;
	}
	#findIDForm #findedID {
		color :blue;
		font-weight: bold;  
	}	
	#findIDForm .errmsg {
	 	color:red;
  	font-size: 0.7em;
		font-weight: bold;  	
	}
	
</style>
<script>
      window.addEventListener("load", init);
      function init() {
        const findIDBtn = document.getElementById("findIDBtn");
        const okBtn 		= document.getElementById("okBtn");
        findIDBtn.addEventListener("click", findID_f);
        okBtn.addEventListener("click", okBtn_f);
      }
      
      //확인버튼 클릭시
      function okBtn_f(event){
    	  event.preventDefault();    	  
    	  const findedIDTag = document.getElementById('findedID');
    	  //찾은아이디가 발견되었으면
    	  if(findedIDTag.textContent){   
    		  
    	  //부모창 접근은 window.opener속성 이용
	    	  window.opener//
	    	        cument.getElementById('id').value = findedIDTag.textContent;
    	  	window.opener//
    	  	 			cument.getElementById('pw').select();
    	  	window.close();
    	  }
      }
      //유효성 체크
      function chkValidation(){
    	  //전화번호체크
    	  
    	  if(!document.getElementById('tel').value) {
    		  document.getElementById('errmsg').textContent = '전화번호 입력바랍니다!';
    		  document.getElementById('tel').select();
    		  return false;
    	  }
    	  
    	  //생년월일체크
    	  if(!document.getElementById('birth').value) {
    		  document.getElementById('errmsg').textContent = '생년월일 입력바랍니다!';
    		  document.getElementById('birth').select();
    		  return false;    		  
    	  }
    	  
      }
           
      //아이디 찾기 버튼 클릭시
      function findID_f(event) {
        event.preventDefault(); //<button>의 기본 이벤트 차단
				
        if(!chkValidation()) return;
        
        const telTag 		= document.getElementById("tel");
        const birthTag 	= document.getElementById("birth");
        
        const findedIDTag		= document.getElementById("findedID");        
        const errmsgTag 		= document.getElementById("errmsg");	
        
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
          if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);

            //json포맷 문자열 => 자바스크립트 ojb
            const jsonObj = JSON.parse(this.responseText);
            
            switch(jsonObj.rtcode){
            case "success" :
            	findedIDTag.textContent = jsonObj.value;
            	errmsgTag.textContent = '';
            	break;
            case "fail" :
            	errmsgTag.textContent = jsonObj.value;            	
            	break;
            }          
          }
        }

        //3)요청 파라미터만들기(json포맷) { "tel": "010-1234-5678", "birth":"2020-07-01" }
        const reqParam = {};
        reqParam.tel = telTag.value;
        reqParam.birth = birthTag.value;
        //js객체를 json포맷 문자열로 변환JSON.stringify()
        //json포맷 문자열을 js객체로 변환JSON.parse()
        const result = JSON.stringify(reqParam);

        //4)서비스요청
        xhttp.open(
          "POST",
          "http://localhost:9080/myweb/member/findIdByRestfull"
        );
        xhttp.setRequestHeader(
          "Content-Type",
          "application/x-www-form-urlencoded"
        );
        xhttp.send("result=" + result);
      }
    </script>
</head>
<body>
	<form id="findIDForm">
		<div>
			<p>
				<label for="tel">전화번호</label>
				<input type="text" name="tel" id="tel" />
			</p>
		</div>
		<div>
			<p>
				<label for="birth">생년월일</label>
				<input type="date" name="birth" id="birth" />
			</p>
		</div>
		<div>
			<span id="findedID"></span>
		</div>
		<div>
			<span class="errmsg" id="errmsg"></span>
		</div>
		<div>
			<p><button id="findIDBtn">아이디찾기</button></p>
			<p><button id="okBtn">확인</button></p>
		</div>
	</form>
</body>
</html>