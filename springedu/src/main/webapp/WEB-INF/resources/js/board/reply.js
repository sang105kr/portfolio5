"use strict";
const g_bnum = '1130'; //게시원글
const g_rid  = 'test4@test.com'; //댓글작성자 id
const g_rnickname = '테스터4'//	댓글작성자 별칭
const g_url = 'http://localhost:9080/portfolio/rboard'; //공통URL
let		g_reqPage = '1'; //요청페이지

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
		writeComment(e);
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
		replyComment(e);  
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
		comment_clone.setAttribute('data-rnum',parentComment.getAttribute('data-rnum')); 
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
		modifyComment(e);
  }
  //삭제
  else if(e.target.classList.contains('btn-delete')){
    console.log("삭제");
    // if(confirm('삭제하시겠습니까?')){
    //   console.log('삭제진행')
    // }
    modal.classList.remove('hidden');

		//모달에 삭제대상 rnum값 전달.
		const rnum = e.target.closest('div[data-rnum]')
												 .getAttribute('data-rnum');
		modal.setAttribute('data-rnum',rnum);
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
		deleteComment(e);
  })      
})();

//--------
replyList(g_reqPage);


//댓글등록
function writeComment(e) {
	
	//1)XMLHTTPRequest 객체 생성
	const xhttp = new XMLHttpRequest();
	//2)서버응답 처리
  //readyState
  // 0 : open()가 호출되지 않은 상태
  // 1 : open()가 실행된 상태 server 연결됨
  // 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
  // 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
  // 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
	xhttp.addEventListener("readystatechange",(e)=>{
		if(e.target.readyState == 4 && e.target.status == 200){
			console.log(e.target.responseText);
			if(e.target.responseText == 'success'){
				//댓글목록 가져오기
				replyList(g_reqPage);
			}else{
				console.log('댓글작성오류');
			}
		}
	});
	//3) 요청메세지
	const rcontent = e.target.closest('.comment')//
													 .querySelector('.rcontent')//
													 .innerHTML;
	const sendData = {};
	sendData.bnum = g_bnum;
//	sendData.rid = g_rid;
//	sendData.rnickname = g_rnickname;
	sendData.rcontent = rcontent;
	
	//4) javascript객체 => json문자열 포맷변환
	const sendJsonFormatString = JSON.stringify(sendData);
	
	//5) 요청메서드 + 요청URL
	xhttp.open('POST', g_url);
	xhttp.setRequestHeader('Content-type',"application/json;charset=utf-8");
	
	//6) 요청
	xhttp.send(sendJsonFormatString);
}
//대댓글등록
function replyComment(e){
	
	//1)XMLHTTPRequest 객체 생성
	const xhttp = new XMLHttpRequest();
	//2)서버응답 처리
  //readyState
  // 0 : open()가 호출되지 않은 상태
  // 1 : open()가 실행된 상태 server 연결됨
  // 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
  // 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
  // 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
	xhttp.addEventListener("readystatechange",(e)=>{
		if(e.target.readyState == 4 && e.target.status == 200){
			console.log(e.target.responseText);
			if(e.target.responseText == 'success'){
				//댓글목록 가져오기
				replyList(g_reqPage);
			}else{
				console.log('대댓글작성오류');
			}
		}
	});
	//3) 요청메세지
	const rcontent = e.target.closest('.comment')//
													 .querySelector('.rcontent')//
													 .innerHTML;													
	const prnum		= e.target.closest('.comment')
													.previousElementSibling
													.getAttribute('data-rnum');
											
	const sendData = {};
	sendData.bnum = g_bnum;
//	sendData.rid = g_rid;
//	sendData.rnickname = g_rnickname;
	sendData.rcontent = rcontent;
	sendData.prnum = prnum;
	
	//4) javascript객체 => json문자열 포맷변환
	const sendJsonFormatString = JSON.stringify(sendData);
	
	//5) 요청메서드 + 요청URL
	const l_url = `${g_url}/reply`;
	xhttp.open('POST', l_url);
	xhttp.setRequestHeader('Content-type',"application/json;charset=utf-8");
	
	//6) 요청
	xhttp.send(sendJsonFormatString);	
	
}




//댓글 수정
function modifyComment(e){
	//1)XMLHTTPRequest 객체 생성
	const xhttp = new XMLHttpRequest();
	//2)서버응답 처리
  //readyState
  // 0 : open()가 호출되지 않은 상태
  // 1 : open()가 실행된 상태 server 연결됨
  // 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
  // 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
  // 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
	xhttp.addEventListener("readystatechange",(e)=>{
		if(e.target.readyState == 4 && e.target.status == 200){
			console.log(e.target.responseText);
			if(e.target.responseText == 'success'){
				//댓글목록 가져오기
				replyList(g_reqPage);
			}else{
				console.log('댓글수정오류');
			}
		}
	});
	//3) 요청메세지
	const rnum     = e.target.closest('.comment')
												   .getAttribute('data-rnum');
	const rcontent = e.target.closest('.comment')//
													 .querySelector('.rcontent')//
													 .innerHTML;
	const sendData = {};
	sendData.rnum = rnum;
//	sendData.rid = g_rid;
//	sendData.rnickname = g_rnickname;
	sendData.rcontent = rcontent;
	
	//4) javascript객체 => json문자열 포맷변환
	const sendJsonFormatString = JSON.stringify(sendData);
	
	//5) 요청메서드 + 요청URL
	xhttp.open('PUT', g_url);
	xhttp.setRequestHeader('Content-type',"application/json;charset=utf-8");
	
	//6) 요청
	xhttp.send(sendJsonFormatString);
}

//댓글 삭제
function deleteComment(e){
	//1)XMLHTTPRequest 객체 생성
	const xhttp = new XMLHttpRequest();
	//2)서버응답 처리
  //readyState
  // 0 : open()가 호출되지 않은 상태
  // 1 : open()가 실행된 상태 server 연결됨
  // 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
  // 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
  // 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
	xhttp.addEventListener("readystatechange",(e)=>{
		if(e.target.readyState == 4 && e.target.status == 200){
			console.log(e.target.responseText);
			if(e.target.responseText == 'success'){
				//댓글목록 가져오기
				replyList(g_reqPage);
			}else{
				console.log('댓글삭제오류');
			}
		}
	});
	//3) 요청메세지
	const rnum = e.target.closest('.modal').getAttribute('data-rnum');
	
	//5) 요청메서드 + 요청URL
	const l_url = `${g_url}/${rnum}`;
	xhttp.open('DELETE', l_url);
	
	//6) 요청
	xhttp.send(null);	
}


//목록가져오기;
function replyList(reqPage){
	
	console.log('목록가져오기');
	//1)XMLHTTPRequest 객체 생성
	const xhttp = new XMLHttpRequest();
	//2)서버응답 처리
  //readyState
  // 0 : open()가 호출되지 않은 상태
  // 1 : open()가 실행된 상태 server 연결됨
  // 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
  // 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
  // 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
	xhttp.addEventListener("readystatechange",(e)=>{
		if(e.target.readyState == 4 && e.target.status == 200){
			//console.log(e.target.response);
			const jsonObj = JSON.parse(e.target.response);
			//console.log(jsonObj.list);
			//console.log(jsonObj.pc);
			const list = jsonObj.list;
			const pc = jsonObj.pc;
			
			addComments(list);
			addPaging(pc);
		}
	});
	
	//3) 요청메서드 + 요청URL
	const l_url = `${g_url}/${reqPage}/${g_bnum}`
	xhttp.open('GET', l_url);
		
	//6) 요청
	xhttp.send();	
}

//댓글목록
function addComments(list){
	let str ='';
	list.forEach(comment=>{
		
		if(comment.rindent == 0){
			//댓글
			str += `<div class="parent" data-rnum="${comment.rnum}">`;
		}else{
			//대댓글
		  str += `<div class="child" data-rnum="${comment.rnum}" data-prnum="${comment.prnum}">`;  
		}
		str += `  <div class="profileImg">`;
		str += `    <img src="https://via.placeholder.com/50x50.png" alt="" />`;
		str += `  </div>`;
		str += `  <div class="replybody">`;
		str += `    <div class="writer">`;
		str += `      <span class="nickname">${comment.rnickname}</span>`;
		str += `      <span class="cdate">${comment.rcdate}</span>`;
		str += `    </div>`;
		str += `    <div class="rcontent" contenteditable="false">${comment.rcontent}</div>`;
		str += `    <div class="btngrp">`;
		str += `      <i class="fas fa-thumbs-up mybtn btn-thumbs-up"></i>`;
		str += `      <span class="vote">${comment.rgood}</span>`;
		str += `      <i class="fas fa-thumbs-down mybtn btn-thumbs-down"></i>`;
		str += `      <span class="vote">${comment.rbad}</span>`;
		str += `      <a href="#" class="mybtn btn-rereply">답글</a>`;
		str += `    </div>`;
		str += `  </div>`;
		str += `  <div class="hiddenItem">`;
		str += `    <i class="fas fa-ellipsis-v mybtn btn-ellipsis"></i>`;
		str += `    <ul class="hiddenMenu">`;
		str += `      <li>`;
		str += `        <span class="mybtn btn-modify">`;
		str += `          <i class="fas fa-eraser"></i>수정</span >`;
		str += `      </li>`;
		str += `      <li>`;
		str += `        <span class="mybtn btn-delete">`;
		str += `          <i class="far fa-trash-alt"></i>삭제</span >`;
		str += `      </li>`;
		str += `    </ul>`;
		str += `  </div>`;
		str += `</div>  `;		
	});
	comments.innerHTML = str;
}

//페이징
function addPaging(pc){
	
}















