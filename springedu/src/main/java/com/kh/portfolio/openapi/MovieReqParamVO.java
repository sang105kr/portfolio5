package com.kh.portfolio.openapi;

import lombok.Data;

@Data
public class MovieReqParamVO {
	private String key;						//	문자열(필수)	발급받은키 값을 입력합니다.
	private String targetDt;			//	문자열(필수)	조회하고자 하는 날짜를 yyyymmdd 형식으로 입력합니다.
	private String itemPerPage;		//	문자열	결과 ROW 의 개수를 지정합니다.(default : “10”, 최대 : “10“)
	private String multiMovieYn;	//	문자열	다양성 영화/상업영화를 구분지어 조회할 수 있습니다. “Y” : 다양성 영화 “N” : 상업영화 (default : 전체)
	private String repNationCd;		//	문자열	K: : 한국영화 “F” : 외국영화 (default : 전체)
	private String wideAreaCd;		//	문자열	상영지역별로 조회할 수 있으며, 지역한국/외국 영화별로 조회할 수 있습니다. “코드는 공통코드 조회 서비스에서 “0105000000” 로서 조회된 지역코드입니다. (default : 전체)
}
