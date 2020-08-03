/**
 * 
 */
const writeBtn = document.getElementById("writeBtn");
const findBtn = document.getElementById("findBtn");

writeBtn.addEventListener("click", writeBtn_f);
findBtn.addEventListener("click", findBtn_f);

//글쓰기 클릭시
function writeBtn_f(e) {
  console.log("글쓰기 클릭됨!");
}

//검색 클릭시
function findBtn_f(e) {
  console.log("검색 클릭됨");
}