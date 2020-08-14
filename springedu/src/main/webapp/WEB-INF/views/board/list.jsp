<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>
<!-- spring form태그사용 -->
<!-- 장점 : 양식(form태그)의 코딩량을 줄일수있다. -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		      <div class="totalRec">
		      	<button type="button" class="btn badge-secondary">
  						총 <span class="badge badge-light">${findCriteria.pageCriteria.totalRec}</span>건
						</button>
					</div>
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
		        	<a href="${contextPath }/board/view/${rec.bnum }/${findCriteria.pageCriteria.rc.reqPage}">${rec.btitle }</a>
		        </div>
		        <div class="rec">${rec.bnickname }</div>
		        <div class="rec">
		        	<fmt:formatDate value="${rec.bcdate }" pattern="yyyy/MM/dd"/>
		        </div>
		        <div class="rec" style="text-align:right">${rec.bhit }</div>
		        </c:forEach>
		      </div>
		      <div class="btnGrp"><button id="writeBtn" class="btn btn-outline-dark">글쓰기</button></div>
		      <div class="paging">
	        	<c:if test="${findCriteria.pageCriteria.prev}">
		        <div>
		        	<a href="${contextPath }/board/list/1/${findCriteria.searchType}/${findCriteria.keyword}"><i class="fas fa-angle-double-left"></i></a>
		        </div>
		        <div>
		        	<a href="${contextPath }/board/list/${findCriteria.pageCriteria.startPage-1}/${findCriteria.searchType}/${findCriteria.keyword}"><i class="fas fa-angle-left"></i></a>
		        </div>
	        	</c:if>
		        <c:forEach var="pageNum" begin="${findCriteria.pageCriteria.startPage }"
		        												 end="${findCriteria.pageCriteria.endPage }" >
		        
		        <!-- 현재페이지와 요청페이지가 같으면 -->
		        <c:if test="${pageNum == findCriteria.pageCriteria.rc.reqPage}">												 
		        <div class="active">
		        </c:if>
		        <!-- 현재페이지와 요청페이지가 다르면 -->
		        <c:if test="${pageNum != findCriteria.pageCriteria.rc.reqPage}">
		        <div>
		        </c:if>			        
		        	<a href="${contextPath }/board/list/${pageNum }/${findCriteria.searchType}/${findCriteria.keyword}">${pageNum }</a>
		        </div>
		        
		        </c:forEach>
		        <c:if test="${findCriteria.pageCriteria.next}">
		        <div>
		        	<a href="${contextPath }/board/list/${findCriteria.pageCriteria.endPage+1}/${findCriteria.searchType}/${findCriteria.keyword}"><i class="fas fa-angle-right"></i></a>
		        </div>
		        <div>
		        	<a href="${contextPath }/board/list/${findCriteria.pageCriteria.finalEndPage}/${findCriteria.searchType}/${findCriteria.keyword}"><i class="fas fa-angle-double-right"></i></a>
		        </div>
		        </c:if>
		      </div>
		      <div class="find">
<%-- 		        <form>
		          <select name="searchType" id="searchType">
		            <option value="TC"
		            	<c:out value="${findCriteria.searchType == 'TC' ? 'selected':'' }"/>>제목+내용</option>
		            <option value="T"
		            	<c:out value="${findCriteria.searchType == 'T' ? 'selected':'' }"/>>제목</option>
		            <option value="C"
		            	<c:out value="${findCriteria.searchType == 'C' ? 'selected':'' }"/>>내용</option>
		            <option value="N"
		            	<c:out value="${findCriteria.searchType == 'N' ? 'selected':'' }"/>>별칭</option>
		            <option value="I"
		            	<c:out value="${findCriteria.searchType == 'I' ? 'selected':'' }"/>>아이디</option>
		          	<option value="A"
		          		<c:out value="${findCriteria.searchType == 'A' ? 'selected':'' }"/>>전체</option>
		          </select>
		          <input type="text" name="keyword" id="keyword" value="${findCriteria.keyword }"/>
		          <button id="findBtn" type="button">검색</button>
		        </form> --%>
		        <form:form modelAttribute="findCriteria">
		          <form:select path="searchType">
   							<form:options items="${codeDecodeList}" 
  														itemLabel="decode" 
  														itemValue="code" />	          
		          </form:select>
		          <form:input type="text" path="keyword" />
		          <button id="findBtn" type="button"  class="btn btn-outline-secondary" >검색</button>
		        </form:form>		        
		      </div>
		    </div>
			</div>
		</div>
	</main>
  <!-- 푸터 -->
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>  

</body>
</html>