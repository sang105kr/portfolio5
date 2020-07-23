/**
 * 
 */
let init = function() {
	let tableTag = document.querySelector('table');
	tableTag
			.addEventListener(
					"click",
					function(e) {

						if (e.target.tagName != 'BUTTON')
							return;
						if (e.target.className == 'modiBtn') {
							console.log('수정클릭됨!');
							let id = e.target.getAttribute('data-modiBtnId');
							// let id =
							// document.querySelector("[data-id='"+e.target.getAttribute('data-modiBtnId')+"']").textContent;
							// let id =
							// e.target.parentElement.parentElement.firstElementChild.textContent;
							// console.log("id="+id);
							window.location.href = "http://localhost:9080/myweb/member/modifyForm.do?id="
									+ id;
						} else if (e.target.className = 'delBtn') {
							console.log('삭제클릭됨!');
							let id = e.target.getAttribute('data-delBtnId');
							// let id =
							// document.querySelector("[data-id='"+e.target.getAttribute('data-delBtnId')+"']").textContent;
							// let id =
							// e.target.parentElement.parentElement.firstElementChild.textContent;
							window.location.href = "http://localhost:9080/myweb/member/out.do?id="
									+ id;
						}
					});
}
window.addEventListener("load", init);