<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<title>게시글 보기</title>
<link rel="stylesheet" href="${contextPath }/css/main.css">
<link rel="stylesheet" href="${contextPath }/css/board/readForm.css">
<script defer src="${contextPath }/js/board/readForm.js"></script>
</head>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

	<!-- header -->
	<%--@ include file="/WEB-INF/views/include/header.jsp" --%>

	<!-- 메뉴 -->
	<%@ include file="/WEB-INF/views/include/menu.jsp"%>

	<!-- 본문 -->
 	<main>
		<div class="container">
			<div class="content">
				<form:form id="writeFrm" 
									 method="post"
									 action="${contextPath }/board/modify" 
									 enctype="multipart/form-data"
									 modelAttribute="boardVO">
					<form:hidden path="bnum"/>				 
					<legend id="title">게시글 보기</legend>
					<ul>
						<li>
							<form:label path="boardCategoryVO.cid">분류</form:label> 
							<form:select	path="boardCategoryVO.cid" disabled="true">
								<option value="0">선택</option>
								<form:options path="boardCategoryVO.cid"
															items="${boardCategory }"
															itemValue="cid"
															itemLabel="cname"/>
							</form:select>
							<span class="client_msg" id="boardCategoryVO.cid.error"></sapn>
							<form:errors cssClass="svr_msg" path="boardCategoryVO.cid"/>
						</li>
						<li>
							<form:label path="btitle">제목</form:label>
							<form:input type="text" path="btitle" readonly="true"/>
							<span class="client_msg" id="btitle.error"></sapn>				
							<form:errors cssClass="svr_msg" path="btitle"/>
						</li>
						<li>
							<form:label path="bid">작성자</form:label>
							<form:input type="text" path="bid" readonly="true"/>
							<span class="client_msg" id="bid.error"></sapn>								
							<form:errors cssClass="svr_msg" path="bid"/>
						</li>
						<li>
							<form:label path="bcontent">내용</form:label> 
							<form:textarea	path="bcontent" rows="10" readonly="true"></form:textarea>
							<span class="client_msg" id="bcontent.error"></sapn>								
							<form:errors cssClass="svr_msg" path="bcontent"/>
						</li>
						<li class="umode">
							<form:label path="files">첨부</form:label>
							<form:input	type="file" path="files" multiple="multiple" />
							<span class="client_msg" id="files.error"></sapn>	
							<form:errors cssClass="svr_msg" path="files"/>
						</li>	
						<li class="btnGrp">
							<!-- 읽기모드 버튼 -->	
							<form:button id="replyBtn" 	type="button" class="btn rmode btn-outline-success">답글</form:button>
							<form:button id="modifyBtn" type="button" class="btn rmode btn-outline-danger">수정</form:button>
							<form:button id="deleteBtn" type="button" class="btn rmode btn-outline-info" data-bnum="${requestScope.boardVO.bnum }"> 삭제</form:button>
							<!-- 수정모드 버튼 -->
							<form:button id="cancelBtn" type="button" class="btn umode btn-outline-danger">취소</form:button>
							<form:button id="saveBtn" 	type="button" class="btn umode btn-outline-success">저장</form:button>
							<!-- 공통버튼 -->	
							<form:button id="listBtn" 	type="button" class="btn btn-outline-info"> 목록</form:button>
						</li>
						<!-- 첨부목록 -->
						<li>
							<form:label path="">첨부목록</form:label>
							<c:if test="${!empty files }">
							<div>
								<c:forEach var="file" items="${requestScope.files }">
								<p style="display:flex">
									<a href="${contextPath }/board/file/${file.fid}">${file.fname }</a>
									<span>(${file.fsize/1000 } kb)</span>			
									<span class="umode"><i style="padding-left:5px"class="fas fa-backspace"></i></span>						
								</p>
								</c:forEach>
							</div>
							</c:if>
							<c:if test="${empty files }">
								<div>
									<p> 첨부파일 없음</p>
								</div>
							</c:if>
						</li>
					</ul>
				</form:form>
			</div>
		</div>
	</main>
	<!-- 푸터 -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

</body>

</html>