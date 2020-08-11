<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>

<title>게시글 보기</title>
<link rel="stylesheet" href="${contextPath }/css/board/board.css">
<link rel="stylesheet" href="${contextPath }/css/board/list.css">
<script defer src="${contextPath }/js/board/list.js"></script>

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
		    <div id="boardList">
		      <div><h2>게시글 목록</h2></div>
		      <div class="wrapper">
		        <div class="head">번호</div>
		        <div class="head">분류</div>
		        <div class="head">제목</div>
		        <div class="head">작성자</div>
		        <div class="head">작성일</div>
		        <div class="head">조회수</div>
		        <c:forEach var="rec" items="${requestScope.list }">
		        <div class="rec">${rec.bnum }</div>
		        <div class="rec">${rec.boardCategoryVO.cname }</div>
		        <div class="rec">
		        	<c:forEach begin="1" end="${rec.bindent }">&nbsp;&nbsp;</c:forEach>
		        	<c:if test="${rec.bindent > 0 }">
		        		<i class="fas fa-reply"></i>
		        	</c:if>
		        	<a href="./view/${rec.bnum }">${rec.btitle }</a>
		        </div>
		        <div class="rec">${rec.bnickname }</div>
		        <div class="rec">
		        	<fmt:formatDate value="${rec.bcdate }" pattern="yyyy/MM/dd"/>
		        </div>
		        <div class="rec" style="text-align:right">${rec.bhit }</div>
		        </c:forEach>
		      </div>
		      <div class="btnGrp"><button id="writeBtn">글쓰기</button></div>
		      <div class="paging">
	        	<c:if test="${pageCriteria.prev}">
		        <div>
		        	<a href="${contextPath }/board/list/1"><i class="fas fa-angle-double-left"></i></a>
		        </div>
		        <div>
		        	<a href="${contextPath }/board/list/${pageCriteria.startPage-1}"><i class="fas fa-angle-left"></i></a>
		        </div>
	        	</c:if>
		        <c:forEach var="pageNum" begin="${pageCriteria.startPage }"
		        												 end="${pageCriteria.endPage }" >
		        
		        <!-- 현재페이지와 요청페이지가 같으면 -->
		        <c:if test="${pageNum == pageCriteria.rc.reqPage}">												 
		        <div class="active">
		        </c:if>
		        <!-- 현재페이지와 요청페이지가 다르면 -->
		        <c:if test="${pageNum != pageCriteria.rc.reqPage}">
		        <div>
		        </c:if>			        
		        	<a href="${contextPath }/board/list/${pageNum }">${pageNum }</a>
		        </div>
		        
		        </c:forEach>
		        <c:if test="${pageCriteria.next}">
		        <div>
		        	<a href="${contextPath }/board/list/${pageCriteria.endPage+1}"><i class="fas fa-angle-right"></i></a>
		        </div>
		        <div>
		        	<a href="${contextPath }/board/list/${pageCriteria.finalEndPage}"><i class="fas fa-angle-double-right"></i></a>
		        </div>
		        </c:if>
		      </div>
		      <div class="find">
		        <form method="post" action="">
		          <select name="" id="">
		            <option value="">제목</option>
		            <option value="">내용</option>
		            <option value="">제목 + 내용</option>
		            <option value="">작성자</option>
		          </select>
		          <input type="text" />
		          <button id="findBtn" type="button">검색</button>
		        </form>
		      </div>
		    </div>
			</div>
		</div>
	</main>
  <!-- 푸터 -->
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>  

</body>
</html>