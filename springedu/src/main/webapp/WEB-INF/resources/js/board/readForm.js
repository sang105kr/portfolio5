/* 읽기모드 버튼 */
const replyBtn  = document.getElementById("replyBtn");
const modifyBtn = document.getElementById("modifyBtn");
const deleteBtn = document.getElementById("deleteBtn");
/* 수정모드 버튼 */
const cancelBtn = document.getElementById("cancelBtn");
const saveBtn  	= document.getElementById("saveBtn");
/* 공통 버튼 */
const listBtn   = document.getElementById("listBtn");
const fileList  = document.getElementById("fileList");

if(replyBtn) 	replyBtn.addEventListener("click", replyBtn_f);
if(modifyBtn) modifyBtn.addEventListener("click", modifyBtn_f);
if(deleteBtn) deleteBtn.addEventListener("click", deleteBtn_f);

if(cancelBtn) cancelBtn.addEventListener("click", cancelBtn_f);
if(saveBtn) 	saveBtn.addEventListener("click", saveBtn_f);
if(listBtn) 	listBtn.addEventListener("click", listBtn_f);

if(fileList)  fileList.addEventListener("click", fileList_f);

changeMode(false); //읽기모드

//답글
function replyBtn_f(e){
	console.log('답글');
}
//수정
function modifyBtn_f(e){
	console.log('수정');
	changeMode(true); //수정모드
}

function changeMode(modeFlag){
	const rmodes = document.getElementsByClassName("rmode");
	const umodes = document.getElementsByClassName("umode");
	
	//읽기모드 -> 수정모드
	if(modeFlag){
		
		//제목변경 => 게시글보기
		document.getElementById('title').textContent = '게시글 수정';
		//카테고리,제목,내용 활성화
		document.getElementById('boardCategoryVO.cid').removeAttribute('disabled');
		document.getElementById('btitle').removeAttribute('readOnly');
		document.getElementById('bcontent').removeAttribute('readOnly');
		
		Array.from(rmodes).forEach(rmode=>{rmode.style.display="none";});
		Array.from(umodes).forEach(umode=>{umode.style.display="grid";});
		
		modeFlag = false;
	//수정모드	-> 읽기모드
	}else{
		//제목변경 => 게시글수정
		document.getElementById('title').textContent = '게시글 보기';
		//카테고리,제목,내용 비활성화
		document.getElementById('boardCategoryVO.cid').setAttribute('disabled',true);
		document.getElementById('btitle').setAttribute('readOnly',true);
		document.getElementById('bcontent').setAttribute('readOnly',true);
		
		Array.from(rmodes).forEach(rmode=>{rmode.style.display="grid";});
		Array.from(umodes).forEach(umode=>{umode.style.display="none";});		
		
		modeFlag = true;
	}
}


//삭제
function deleteBtn_f(e){
	console.log('삭제');
	if(confirm("삭제하시겠습니까?")){
		const bnum = e.target.getAttribute("data-bnum");
		const url = `/portfolio/board/delete/${bnum}`;
		window.location.href = url;
	}
}
//취소
function cancelBtn_f(e) {
	e.preventDefault();
  console.log("취소");
	
	//수정모드=>읽기모드
	changeMode(false); 
	
}

//저장
function saveBtn_f(e) {
	e.preventDefault();
  console.log("등록");

  //1) 유효성체크
	if(!checkValidation()) {
		return false;
	}
	
  //2) 서버전송
  writeFrm.submit();
}

//목록
function listBtn_f(e) {
  console.log("목록");
  //목록리스트로 이동
  location.href = "/portfolio/board/list";
}

//유효성 체크
function checkValidation(){
	
	//분류지정
	const cidTag = document.getElementById('boardCategoryVO.cid');
	if(cidTag.options[cidTag.selectedIndex].value == 0) {
		//cidTag.select();
		document.getElementById('boardCategoryVO.cid.error').textContent = "분류 지정해주세요!";
		return false;
	}
	document.getElementById('boardCategoryVO.cid.error').textContent = "";
	
	//제목
	const btitleTag = document.getElementById('btitle');
	if(btitleTag.value.trim().length == 0){
		document.getElementById('btitle.error').textContent = "제목을 작성바랍니다.";
		btitleTag.select();
		return false;
	}
	document.getElementById('btitle.error').textContent = "";
	
	//작성자
	const bidTag = document.getElementById('bid');
	if(bidTag.value.trim().length == 0){
		document.getElementById('bid.error').textContent = "아이디를 작성바랍니다.";
		bidTag.select();
		return false;
	}
	document.getElementById('bid.error').textContent = "";
	//정규표현식 
	let idExpReg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-A]{2,3}$/i;
	if(!idExpReg.test(bidTag.value)){
		document.getElementById('bid.error').textContent = "이메일 형식에 맞지 않습니다 ex)aaa@bbb.com";
		bidTag.select();
		return false;
	}
	//내용
	const bcontentTag = document.getElementById('bcontent');
	if(bcontentTag.value.trim().length < 4){
		document.getElementById('bcontent.error').textContent = "내용은 4자 이상 작성바랍니다.";
		bcontentTag.select();
		return false;
	}
	document.getElementById('bcontent.error').textContent = "";
	
	//파일사이즈
	const filesTag = document.getElementById('files');
	//첨부파일 합구하기
	let sum = 0;
	Array.from(filesTag.files).forEach(file=>{sum += file.size});
/*	
	Array.from(filesTag).forEach(
		function(file){
			sum += file.size
		}
	);
*/	
	//첨부파일 합계가 10M초과여부 체크
	if(sum > 10485760) {
		document.getElementById('files.error').textContent = "10M이하로 첨부 가능합니다.";
		return false;
	}
	
	return true;
}

//파일삭제
function fileList_f(e){
	//이벤트 소스 요소
	console.log(e.target);
	
	//이벤트를 등록한 요소
	console.log(e.currentTarget);
	
	if(e.target.tagName !== 'I') return false;
	if(!confirm('삭제하시겠습니까?')) return false;
	
	const iTag = e.target;
	const fid  = e.target.getAttribute("data-fid");
	
	//AJAX 사용
	//1) XMLHttpRequest 객체 생성
	const xhttp = new XMLHttpRequest();
	
	//2) 서비스 요청
	const url = `http://localhost:9080/portfolio/board/file/${fid}`;
	xhttp.open("DELETE",url);
	xhttp.send();
	
	//3) 서버응답처리
  //readyState
  // 0 : open()가 호출되지 않은 상태
  // 1 : open()가 실행된 상태 server 연결됨
  // 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
  // 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
  // 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태	
	xhttp.addEventListener("readystatechange",ajaxCall);
	function ajaxCall(e){
		if(this.readyState == 4 && this.status == 200){
			//서버에서 파일 삭제를 처리했으면
			if(this.responseText == "success") {
				let pTag = iTag.parentElement.parentElement.parentElement;
				fileList.removeChild(pTag);
			}else{
				console.log('파일삭제 실패!!');
			}
		}
	}
}












