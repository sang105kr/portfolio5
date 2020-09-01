<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${contextPath }/css/board/reply.css">
<script>
var g_bnum = '${boardVO.bnum}'; //게시원글
var g_bid  = '${boardVO.bid}';  //게시원글 id
var g_rid  = '${sessionScope.member.id}'; //댓글작성자 id
var g_rnickname = '${sessionScope.member.nickname}'//	댓글작성자 별칭
var g_url = '${contextPath }/rboard'; //공통URL
</script>
<script defer src="${contextPath }/js/board/reply.js"></script>

<div class="wrapper-rereply">

  <!--댓글작성-->
  <div class="comment">
    <div class="profileImg">
  	<c:if test="${empty sessionScope.member}"> 
      <img src="https://via.placeholder.com/50x50.png" alt="" />
    </c:if>
  	<c:if test="${!empty sessionScope.member}">  
      <img src="data:${sessionScope.member.ftype};base64,${sessionScope.member.picBase64}" alt="" />
    </c:if>
    </div>
    <div class="replybody">
      <div class="writer">${(sessionScope.member.nickname==null)? "Guest":sessionScope.member.nickname }</div>
      <div
        class="rcontent"
        contenteditable="true"
        data-placeholder="댓글추가..."
      ></div>
      <div class="btngrp">
        <button class="mybtn btn-cancel">취소</button>
        <button class="mybtn btn-write">댓글</button>
      </div>
    </div>
  </div>
  <!--댓글목록-->
  <div class="comments"></div>
  <!--댓글페이징-->
  <div class="paging"></div>
  <!--모달-->
  <div class="modal hidden">
    <div class="modal__overlay"></div>
    <div class="modal__content">
      <div class="modal__header">
        <div class="modal__title">
          <i class="fas fa-trash-alt"></i>
          <span>댓글 삭제</span>
        </div>
      </div>
      <div class="modal__body">댓글을 완전히 삭제할까요?</div>
      <div class="modal__footer">
        <button class="mybtn modal__cancel">취소</button>
        <button class="mybtn modal__delete">삭제</button>
      </div>
    </div>
  </div>  
</div>
