<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>게시글 작성</title>
<link rel="stylesheet" href="${contextPath }/css/board/board.css">
<link rel="stylesheet" href="${contextPath }/css/board/writeForm.css">
<script defer src="href="${contextPath }/js/board/writeForm.js"></script>
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
				<form method="post" action="${contextPath }/board/write">
					<legend>게시글 작성</legend>
					<ul>
						<li><label for="">분류</label> <select
							name="boardCategoryVO.cid" id="">
								<option value="">선택</option>
								<option value="1001">SPRING</option>
								<option value="1002">DATABASE</option>
								<option value="1003">JAVA</option>
						</select></li>
						<li><label for="btitle">제목</label><input type="text"
							name="btitle" id="btitle" /></li>
						<li><label for="bid">작성자</label><input type="text" name="bid"
							id="bid" /></li>
						<li><label for="bcontent">내용</label>
						<textarea name="bcontent" id="bcontent" rows="10"></textarea></li>
						<li><label for="">첨부</label> <input type="file" name="" id=""
							multiple /></li>
						<li>
							<button>등록</button>
							<button>취소</button>
							<button>목록</button>
						</li>
					</ul>

				</form>
			</div>
		</div>
	</main>
	<!-- 푸터 -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

</body>

</html>