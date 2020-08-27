<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${contextPath }/css/board/reply.css">
<script defer src="${contextPath }/js/board/reply.js"></script>

<div class="wrapper-rereply">

  <!--댓글작성-->
  <div class="comment">
    <div class="profileImg">
      <img src="https://via.placeholder.com/50x50.png" alt="" />
    </div>
    <div class="replybody">
      <div class="writer">홍길동</div>
      <div
        class="rcontent"
        contenteditable="true"
        data-placeholder="댓글추가..."
      ></div>
      <div class="btngrp">
        <button class="btn btn-cancel">취소</button>
        <button class="btn btn-write">댓글</button>
      </div>
    </div>
  </div>
  <!--댓글목록-->
  <div class="comments">
    <div class="parent" data-rnum="1">
      <div class="profileImg">
        <img src="https://via.placeholder.com/50x50.png" alt="" />
      </div>
      <div class="replybody">
        <div class="writer">
          <span class="nickname">홍길동</span>
          <span class="cdate">2020.09.10</span>
        </div>
        <div class="rcontent" contenteditable="false">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad, laborum odio pariatur esse optio rem nisi cupiditate debitis ut quos quas dolorum doloribus tenetur consequuntur! Consequatur quasi veniam nulla praesentium!</div>
        <div class="btngrp">
          <i class="fas fa-thumbs-up btn btn-thumbs-up"></i>
          <span class="vote">10</span>
          <i class="fas fa-thumbs-down btn btn-thumbs-down"></i>
          <span class="vote">3</span>
          <a href="#" class="btn btn-rereply">답글</a>
        </div>
      </div>
      <div class="hiddenItem">
        <i class="fas fa-ellipsis-v btn btn-ellipsis"></i>
        <ul class="hiddenMenu">
          <li>
            <span class="btn btn-modify">
              <i class="fas fa-eraser"></i>수정</span >
          </li>
          <li>
            <span class="btn btn-delete">
              <i class="far fa-trash-alt"></i>삭제</span >
          </li>
        </ul>
      </div>
    </div>    

    <div class="child" data-rnum="2">
      <div class="profileImg">
        <img src="https://via.placeholder.com/50x50.png" alt="" />
      </div>
      <div class="replybody">
        <div class="writer">
          <span class="nickname">홍길동</span>
          <span class="cdate">2020.09.10</span>
        </div>
        <div class="rcontent" contenteditable="false">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Excepturi odio ad recusandae adipisci deleniti officia laudantium ipsa repellat dolores ducimus quo, quidem perspiciatis iure quibusdam eius! Facilis saepe a vitae?</div>
        <div class="btngrp">
          <i class="fas fa-thumbs-up btn btn-thumbs-up"></i>
          <span class="vote">10</span>
          <i class="fas fa-thumbs-down btn btn-thumbs-down"></i>
          <span class="vote">3</span>
          <a href="#" class="btn btn-rereply">답글</a>
        </div>
      </div>
      <div class="hiddenItem">
        <i class="fas fa-ellipsis-v btn btn-ellipsis"></i>
        <ul class="hiddenMenu">
          <li>
            <span class="btn btn-modify">
              <i class="fas fa-eraser"></i>수정</span>
          </li>
          <li>
            <span class="btn btn-delete">
              <i class="far fa-trash-alt"></i>삭제</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <!--댓글페이징-->
  <div class="paging">
    <ul>
      <li><a href="#"><i class="fas fa-angle-double-left"></i></a></li>
      <li><a href="#"><i class="fas fa-angle-left"></i></a></li>
      <li><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li><a href="#">6</a></li>
      <li><a href="#">7</a></li>
      <li><a href="#">8</a></li>
      <li><a href="#">9</a></li>
      <li><a href="#">10</a></li>
      <li><a href="#"><i class="fas fa-angle-right"></i></a></li>
      <li><a href="#"><i class="fas fa-angle-double-right"></i></a></li>
    </ul>
  </div>
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
        <button class="btn modal__cancel">취소</button>
        <button class="btn modal__delete">삭제</button>
      </div>
    </div>
  </div>  


</div>
