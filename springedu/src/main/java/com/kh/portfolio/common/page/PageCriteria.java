package com.kh.portfolio.common.page;

import com.kh.portfolio.exception.BizException;

/*
 * 현재 페이지에서 보여줄 페이지 범위 계산
 * 전제조건: RecordCriteria 정보, 전체레코드수(totalRec)를 입력값으로 받아
 * 시작페이지,종료페이지,이전,다음,처음,마지막 페이지를 산출한다.
 */
public class PageCriteria {

	private int pageNumPerPage;			//한페이지에보여줄 페이지수
	private int startPage;					//한페이지에보여줄 시작페이지
	private int endPage;						//한페이지에보여줄 종료페이지
	
	private int totalRec;						//전체레코드수
	private int finalEndPage;				//최종페이지
	
	
	private boolean prev;						//이전페이지
	private boolean next;						//다음페지
	
	RecordCriteria rc;							//한페이지에 보여줄 레코드수, 요청페이지
	
	public PageCriteria() {}				//디폴트생성자

	public void calculatePaging() {

		//한페이지에 보여줄 종료페이지
		//올림(요청페이지/한페이지에 보여줄 페이지수) * 한페이지에 보여줄 페이지수
		endPage = (int)Math.ceil(rc.getReqPage() / (double)getPageNumPerPage()) * getPageNumPerPage();
		
		//한페이지에보여줄 시작페이지
		//한페이지에보여줄 종료페이지 - 한페이지에 보여줄 페이지수 + 1
		startPage = getEndPage() - getPageNumPerPage() + 1;
		
				
		//최종(마지막) 페이지계산: 올림(전체레코드수/한페이지에보여줄 레코드수)
		finalEndPage = (int)Math.ceil(getTotalRec() / (double)rc.getRecNumPerPage());
		
		//최종페이지가 현재페이지의 종료페이지보다 작으면 최종페이지가 종료페이지가 된다.
		if(finalEndPage < endPage) {
			endPage = finalEndPage;
		}		
	}
	
	public int getPageNumPerPage() {
		return pageNumPerPage;
	}
	
	//한페이지에보여줄 페이지수
	public void setPageNumPerPage(int pageNumPerPage) {
		this.pageNumPerPage = pageNumPerPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {		
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalRec() {
		return totalRec;
	}

	public void setTotalRec(int totalRec) {
		this.totalRec = totalRec;
	}

	public int getFinalEndPage() {	
		return finalEndPage;
	}

	public void setFinalEndPage(int finalEndPage) {
		this.finalEndPage = finalEndPage;
	}

	//이전페이지 노출여부 : 요청페이지의 시작페이지가 1이 아닌경우 노출
	public boolean isPrev() {
		return getStartPage() == 1 ? false : true;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	//다음페이지 노출여부 : 전체 레코드수가 요청페이지의 종료페이지보다 큰경우 노출
	public boolean isNext() {
		return totalRec > getEndPage() * rc.getRecNumPerPage() ? true : false;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public RecordCriteria getRc() {
		return rc;
	}

	public void setRc(RecordCriteria rc) {
		this.rc = rc;
	}
		
}
