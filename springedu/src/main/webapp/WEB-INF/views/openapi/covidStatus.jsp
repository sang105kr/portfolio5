<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>
<title>코로나19 감염_현황</title>
<link rel="stylesheet" href="${contextPath }/css/board/board.css">
<script defer src="${contextPath }/js/openapi/covid.js"></script>
  <style>
    table{
      border-collapse: collapse;
      width:100%;
    }
    th, td {
      border:1px solid;
    } 
    td {
    	font-size:0.9rem;
    	letter-spacing: normal;
    }
    thead th{
    	text-align:center;
    	font-size:1.2em;
    	font-width:bold;
    	letter-spacing: 0.1em;
    }
    caption {
    	font-style:italic;
    	font-width:bold;
    	caption-side: top;
    	padding: 20px;
    	color: #666;
    	text-align: center;
    	letter-spacing:0.2em;
    }
    
    .search{
    	margin-top: 10px;
    	display:flex;
    	justify-content:flex-end;
    }
  </style>
</head>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp" %>

  <!-- header -->
<%--   <%@ include file="/WEB-INF/views/include/header.jsp" %> --%>

  <!-- 메뉴 -->
  <%@ include file="/WEB-INF/views/include/menu.jsp" %>

  <!-- 본문 -->
  <main>
    <div class="container">
      <div class="content">  
				<div id="movie_rank" class="cont_reserve">
				  <table>
				    <caption>코로나 현황</caption>
				    <thead>
				      <tr>
				        <th>기준일</th>
				        <th>확진자 수</th>
				        <th>격리해제 수</th>
				        <th>검사진행 수</th>
				        <th>사망자 수</th>
				      </tr>
				    </thead>
				    <tbody class="movie-body">
<!-- 				      <tr>
				        <td>1</td>
				        <td>테넷</td>
				        <td>2020.08.01</td>
				        <td>1000</td>
				        <td>100</td>
				      </tr> -->
				    </tbody>
				  </table>
				  <div class="search"> 	
				  	<input type="date" class="movieDaily"/>
				  	<button class="movieDailyBtn">조회</button>
				  </div>			
				</div>
			</div>
		</div>
	</main>
  <!-- 푸터 -->
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>  

</body>
</html>