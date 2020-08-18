/**
 * 
 */
const modifyBtn_f = (event) => {
	event.preventDefault();
	console.log('modifyBtn 클릭!');
	const pwTag 		= document.getElementById("pw");
	const errmsg_pw = document.getElementById("errmsg_pw");
	if(!pwTag.value.trim()) {
		errmsg_pw.textContent = '비밀번호를 입력하세요';
		pwTag.select();
		return;
	}
	document.getElementById('modifyForm').submit();
}

const init = () => {
	const modifyBtn = document.getElementById("modifyBtn"); 
	modifyBtn.addEventListener("click",modifyBtn_f);
}

//window.addEventListener("load",init);
//프로파일 이미지 첨부
addProfileImg();
function addProfileImg() {
  const fileTag = document.querySelector("#file");
  const imgTag = document.querySelector("#mypic");
  const mypicArea = document.querySelector(".mypicArea");

  //프로파일사진 첨부영역 클릭시 파일선택창이 실행됨.
  mypicArea.addEventListener("click", function () {
    fileTag.click();
  });

  //파일선택창에서 파일을 선택했을때
  fileTag.addEventListener("change", function () {
    //메모리상에 로딩된 uri정보를 읽어와서 미리보기
    const url = URL.createObjectURL(fileTag.files[0]);
    imgTag.src = url;
  });
  //파일을 이미지영역에 드래그앤드롭했을때
  mypicArea.addEventListener("drop", function (e) {
    e.preventDefault();
    console.log("drop~");
    console.log(e.dataTransfer.files[0]);
    const file = e.dataTransfer.files[0];
		const errmsg_file = document.querySelector('#errmsg_file');
    //파일유형 체크
    const reg = /image\/*/;
    if (!file.type.match(reg)) {
      errmsg_file.textContent="이미지가 아님니다";
      return false;
    }
    //파일 사이즈 체크
    if (file.size > 1024 * 200) {
			errmsg_file.textContent="200kb이하 이미지만 첨부가능합니다.";
      return false;
    }
		//메모리에 있는 이미지파일 url읽어오기
    const url = URL.createObjectURL(file);
	  //미리보기 표시
    imgTag.src = url;
    mypicArea.classList.remove("dragover");
		//드래그한 파일을 파일요소에 추가하기
		fileTag.files = e.dataTransfer.files;
  });
  //이미지영역에 오버했을때 발생되는 기본 이벤트
  mypicArea.addEventListener("dragover", function (e) {
    e.preventDefault();
    console.log("dragover");
    mypicArea.classList.add("dragover");
  });
  mypicArea.addEventListener("dragleave", function (e) {
    e.preventDefault();
    console.log("dragleave");
    mypicArea.classList.remove("dragover");
  });
}