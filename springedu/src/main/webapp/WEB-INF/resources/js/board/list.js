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
	window.location.href="./writeForm";
}

//검색 클릭시
function findBtn_f(e) {
  console.log("검색 클릭됨:");
	const searchTypeTag = document.getElementById("searchType");
	const keywordTag = document.getElementById("keyword");
	if(keywordTag.value.trim().length == 0){
		alert('검색어를 입력하세요');
		keywordTag.value = "";
		keywordTag.focus();
		return false;
	}
	
	console.log(searchTypeTag.value, keywordTag.value);
	const url = `/portfolio/board/list/1/${searchTypeTag.value}/${keywordTag.value}`;
	window.location.href = url; 	
}


