<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<title>게시글 보기</title>
<link rel="stylesheet" href="${contextPath }/css/board/board.css">
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
									 action="${contextPath }/board/modify/${requestScope.returnPage }" 
									 enctype="multipart/form-data"
									 modelAttribute="boardVO">
					<form:hidden path="bnum"/>
					<form:hidden path="bid" value="${sessionScope.member.id }"/>										 
					<legend id="title">게시글 보기</legend>
					<ul>
						<li>
							<form:label path="boardCategoryVO.cid">분류</form:label> 
							<form:select	path="boardCategoryVO.cid" disabled="true">
								<form:option value="0">선택</form:option>
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
							<label for="bid">작성자</label>
							<input type="text" id="bid" value="${boardVO.bnickname }(${boardVO.bid })" readonly="true"/>
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
							<form:button id="replyBtn" 	class="btn rmode btn-outline-success" data-bnum="${requestScope.boardVO.bnum }" data-returnPage="${requestScope.returnPage }">답글</form:button>
							<!-- 작성자만 수정,삭제 가능 시작 -->
							<c:if test="${sessionScope.member.id == boardVO.bid }">
							<form:button id="modifyBtn" class="btn rmode btn-outline-danger">수정</form:button>
							<form:button id="deleteBtn" class="btn rmode btn-outline-info" data-bnum="${requestScope.boardVO.bnum }" data-returnPage="${requestScope.returnPage }"> 삭제</form:button>
							</c:if>
							<!-- 작성자만 수정,삭제 가능 끝 -->
							<!-- 수정모드 버튼 -->
							<form:button id="cancelBtn" class="btn umode btn-outline-danger">취소</form:button>
							<form:button id="saveBtn" 	class="btn umode btn-outline-success" data-returnPage="${requestScope.returnPage }">저장</form:button>
							<!-- 공통버튼 -->	
							<form:button id="listBtn" 	class="btn btn-outline-info" data-returnPage="${requestScope.returnPage }"> 목록</form:button>
						</li>
						<!-- 첨부목록 -->
						<li>
							<form:label path="">첨부목록</form:label>
							<c:if test="${!empty files }">
							<div  id="fileList">
								<c:forEach var="file" items="${requestScope.files }">
								<p style="display:flex">
									<a href="${contextPath }/board/file/${file.fid}">${file.fname }</a>
									<span>(${file.fsize/1000 } kb)</span>			
									<span class="umode">
										<a href="#"><i style="padding-left:5px" class="fas fa-backspace" data-fid="${file.fid }"></i></a>
									</span>						
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
				<!-- 댓글 -->
				<%@include file="/WEB-INF/views/board/rereply.jsp" %>
			</div>			
		</div>
	</main>
	<!-- 푸터 -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

</body>

</html>