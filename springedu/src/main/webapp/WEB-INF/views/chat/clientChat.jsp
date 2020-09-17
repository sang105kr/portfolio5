<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<title>채팅방</title>
<link rel="stylesheet" href="${contextPath }/css/board/board.css">
<style>
.container .content #chatroom{
	margin:0 auto;
	margin-top:30px;
}

.container .content #messages {
	margin-bottom: 10px; border : 1px solid gray;
	overflow: auto;
	border: 1px solid gray;
	line-height:1.5rem;
	height:200px;
}

.container .content #messageinput {
	width:100%;
	margin-bottom:10px;
}

.container .content button{
	padding:5px 10px;
	border:none;
	border-radius:5px;
	outline:none;
	background-color:#111;
	color:#fff;
}
</style>

<script>
	let ws, messages;
	window.addEventListener("load",init,false);
	
	function init(){
    messages=document.getElementById("messages");
    
    openSocketBtn.addEventListener("click",e => openSocket(),false);
    messageinput.addEventListener("keyup", e => { if (e.keyCode == 13) send(); });
    closeSocketBtn.addEventListener("click",e => closeSocket(),false);
    
	}
  
  function openSocket(){
    	//1)웹소켓 생성 유무 판단
      if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
          writeResponse("WebSocket is already opened.");
          return;
      }
      //2)웹소켓 객체 생성
      ws=new WebSocket("ws://192.168.0.121:9080/portfolio/echo/${sessionScope.member.id }/${sessionScope.member.nickname }");
      
      ws.addEventListener("open",e=>{
          if(event.data === undefined) return;
          writeResponse(e.data);
      },false);
      
      ws.addEventListener("message",e=>writeResponse(e.data),false);
      
      ws.addEventListener("close",e=>{
    	  writeResponse(e.data);
    	},false);
  }
  
  function send(){
		let text = sender.value + "," + messageinput.value;
		ws.send(text);
		text = "";
  }
  
  function closeSocket(){
	  writeResponse("접속종료되었습니다.");
		ws.close();
  }
  
  function writeResponse(text){
	  if(text.split(':')[0] === sender.value) {
		  messages.innerHTML+="<div><span style='color:black'>"+text+"<span></div>";
	  }else{
		  messages.innerHTML+="<div><span style='color:blue'>"+text+"<span></div>";		  
	  }
	  //스크롤이동  
	  messages.scrollTop = messages.scrollHeight;	 
  }
  


  
</script>
</head>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

	<!-- header -->
	<%--   <%@ include file="/WEB-INF/views/include/header.jsp" %> --%>

	<!-- 메뉴 -->
	<%@ include file="/WEB-INF/views/include/menu.jsp"%>
	<!-- 본문 -->
	<main>
		<div class="container">
			<div class="content">
				<div id="chatroom">
					<div id="messages"></div>
					<div>
						<input type="text" id="sender"
							value="${sessionScope.member.nickname }" style="display: none;">
						<input type="text" id="messageinput">
					</div>
					<div>
						<button type="button" id="openSocketBtn">접속</button>
						<button type="button" id="closeSocketBtn">종료</button>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- 푸터 -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

</body>
</html>