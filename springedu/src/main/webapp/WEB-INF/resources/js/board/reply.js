"use strict";
const comment = document.querySelector(".comment");
const comments = document.querySelector(".comments");
const paging = document.querySelector(".paging");
const modal = document.querySelector('.modal');

//댓글작성 foucs이벤트 발생시 버튼활성화
comment.querySelector('.rcontent')
       .addEventListener("focus", (e) => 
  e.target.nextElementSibling.style.display = "block"  
);

//댓글작성 이벤트 등록
comment.addEventListener("click",(e)=>{
  const rcontent = comment.querySelector('.rcontent');

  //댓글 입력시
  rcontent.addEventListener("keyup", (e)=>{
    console.log(e.target.textContent.trim().length);
    const writeBtn = e.target.parentElement.querySelector(".btn-write");
    if (rcontent.textContent.trim().length > 0) {
      writeBtn.style.backgroundColor = "#0000ff";   
    } else {
      writeBtn.style.backgroundColor = "#909090";
    }
  });

  //취소
  if(e.target.classList.contains("btn-cancel")){
    console.log("취소");
    comment.querySelector('.btngrp').style.display = "none";
    comment.querySelector('.rcontent').textContent = "";    
  }
  //댓글
  if(e.target.classList.contains("btn-write")){
    console.log("댓글");
  }
});

//댓글목록 이벤트 등록
comments.addEventListener("click",(e)=>{
  console.log(e.target);
  //대댓글
  if(e.target.classList.contains('btn-rereply')){
    console.log("대댓글");

    const parentComment = e.target.closest("[data-rnum]");
    const comment_clone = comment.cloneNode(true);
    const rcontent = comment_clone.querySelector(".rcontent");

    //대댓글작성 요소가 없으면 추가
    if(parentComment.nextElementSibling){
      if(parentComment.nextElementSibling.classList.contains('comment')){
        return;
      };
    }
    //대댓글 들여쓰기(부모댓글,자식댓글 판단)
    if(parentComment.classList.contains('parent')){
      comment_clone.classList.add('rereply-depth1');
    }else{
      comment_clone.classList.add('rereply-depth2');
    }
    //복제한 노드의 댓글내용 비우기
    comment_clone.querySelector('.rcontent').textContent="";

    //버튼보이기
    comment_clone.querySelector(".btngrp").style.display="block";

    //답글 바탕색 초기화
    comment_clone.querySelector(".btn-write").style.backgroundColor="#909090";

    //버튼명 => 답글
    comment_clone.querySelector(".btn-write").textContent = "답글";

    //클래스명 변경
    comment_clone.querySelector(".btn-write").classList.replace("btn-write","btn-replyWrite");

    //댓글 입력시
    rcontent.addEventListener("keyup", (e)=>{
      console.log(e.target.textContent.trim().length);
      const writeBtn = e.target.parentElement.querySelector(".btn-replyWrite");
      if (rcontent.textContent.trim().length > 0) {
        writeBtn.style.backgroundColor = "#0000ff";
      } else {
        writeBtn.style.backgroundColor = "#909090";
      }
    });

    //부모노드의 형제노드로 댓글작성 추가
    parentComment.after(comment_clone);
  }
  //대댓글 처리
  else if(e.target.classList.contains('btn-replyWrite')){
    console.log('대댓글처리');
  }

  //수정
  else if(e.target.classList.contains('btn-modify')){
    console.log("수정");
    const parentComment = e.target.closest("[data-rnum]");
    const comment_clone = comment.cloneNode(true);
    const rcontent = comment_clone.querySelector(".rcontent");
    
    //0) hiddenMenu 숨김
    parentComment.querySelector('.hiddenMenu').style.display="";

    //1) 댓글 숨김
    parentComment.style.display="none";

    //2) 댓글 수정 추가

    //수정글 들여쓰기(부모댓글,자식댓글 판단)
    if(parentComment.classList.contains('parent')){
      comment_clone.classList.add('modify-depth1');
    }else{
      comment_clone.classList.add('modify-depth2');
    }
    //복제한 노드의 댓글내용 채우기
    comment_clone.querySelector('.rcontent').innerHTML=
    parentComment.querySelector('.rcontent').innerHTML;

    //버튼보이기
    comment_clone.querySelector(".btngrp").style.display="block";

    //답글 바탕색 초기화
    comment_clone.querySelector(".btn-write").style.backgroundColor="#909090";

    //버튼명 => 저장
    comment_clone.querySelector(".btn-write").textContent = "저장";

    //클래스명 변경
    comment_clone.querySelector(".btn-write").classList.replace("btn-write","btn-modifyWrite");

    //수정시
    rcontent.addEventListener("keyup", (e)=>{
      console.log(e.target.textContent.trim().length);
      const writeBtn = e.target.parentElement.querySelector(".btn-modifyWrite");
      if (rcontent.textContent.trim().length > 0) {
        writeBtn.style.backgroundColor = "#0000ff";
      } else {
        writeBtn.style.backgroundColor = "#909090";
      }
    });

    //부모노드의 형제노드로 댓글작성 추가
    parentComment.after(comment_clone);    
    //커서 지정
    comment_clone.querySelector('.rcontent').focus();
  }
  //수정 처리
  else if(e.target.classList.contains('btn-modifyWrite')){
    console.log('수정처리');
  }
  //삭제
  else if(e.target.classList.contains('btn-delete')){
    console.log("삭제");
    // if(confirm('삭제하시겠습니까?')){
    //   console.log('삭제진행')
    // }
    modal.classList.remove('hidden');
  }
  //선호
  else if(e.target.classList.contains('btn-thumbs-up')){
    console.log("선호");
  }
  //비선호
  else if(e.target.classList.contains('btn-thumbs-down')){
    console.log("비선호");
  }
  //숨김아이템
  else if(e.target.classList.contains('btn-ellipsis')){
    console.log("숨김아이템");
    const hiddenMenu = e.target.parentElement.querySelector("ul.hiddenMenu");
    const ellipsis = e.target;
    
    //댓글목록의 숨김아이템을 모두 숨김
    Array.from(comments.querySelectorAll('.btn-ellipsis'))
         .forEach((element)=>{
           if(element.isEqualNode(ellipsis)) return;
           element.style.display="";
          });
    Array.from(comments.querySelectorAll('.hiddenMenu'))
         .forEach((element)=>{
           if(element.isEqualNode(hiddenMenu)) return;
           element.style.display="";
          });

    //댓글목록의 숨김아이템을 토글처리
    if (hiddenMenu.style.display === "block") {
      hiddenMenu.style.display = "none";
    } else {
      hiddenMenu.style.display = "block";
      ellipsis.style.display = "block";
    }
  }
  //취소
  else if(e.target.classList.contains('btn-cancel')){
    console.log("취소");
    e.target.closest('.comment').previousElementSibling.style.display="grid";
    e.target.closest('.comment').remove();
  }

});

/* 모달 */
/* 
  즉시 실행 함수 IIFE(Immediately Invoked Function Expression)
  (function(){})();
*/
(function (){
  // const deleteBtn = document.querySelector('.btn-delete');
  // const modal = document.querySelector('.modal');
  const modalCancelBtn = modal.querySelector('.modal__cancel');
  const modalDeletBtn = modal.querySelector('.modal__delete');
  const modalOveray = modal.querySelector('.modal__overlay');

  // deleteBtn.addEventListener("click",()=>{
  //   modal.classList.remove('hidden');
  // }); 

  modalOveray.addEventListener("click",(e)=>{
    modal.classList.add('hidden');
  })

  //취소
  modalCancelBtn.addEventListener("click",(e)=>{
    modal.classList.add('hidden');
  })
  //삭제
  modalDeletBtn.addEventListener("click",(e)=>{
    modal.classList.add('hidden');
    console.log('삭제진행');
  })      
})();