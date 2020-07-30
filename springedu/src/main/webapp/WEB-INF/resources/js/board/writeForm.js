/**
 * 
 */
const writeBtn  = document.getElementById("writeBtn");
const cancelBtn = document.getElementById("cancelBtn");
const listBtn   = document.getElementById("listBtn");
const writeForm = document.getElementById("writeForm");

writeBtn.addEventListener("click", writeBtn_f);
cancelBtn.addEventListener("click", cancelBtn_f);
listBtn.addEventListener("click", listBtn_f);

//등록
function writeBtn_f(e) {
  console.log("등록");
  //1) 유효성체크

  //2) 서버전송
  writeForm.submit();
}
//취소
function cancelBtn_f(e) {
  console.log("취소");
  //입력한 내용 clear
  writeForm.reset();
}
//목록
function listBtn_f(e) {
  console.log("목록");
  //목록리스트로 이동
  location.href = "list";
}