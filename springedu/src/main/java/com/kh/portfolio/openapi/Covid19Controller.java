package com.kh.portfolio.openapi;

import java.io.BufferedInputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/covid")
public class Covid19Controller {
	
	private static final Logger logger 
		= LoggerFactory.getLogger("Covid19Controller.class");
	
	@GetMapping
	public String movie() {
		
		return "/openapi/covidStatus";
	}
	
	@GetMapping(value="/{day}",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> rank(
			@PathVariable("day") String day){
		ResponseEntity<String> resp = null;
		
		BufferedInputStream reader = null;
		StringBuffer sb = new StringBuffer();
		
		URL url = null;
		String reqUrl = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson";
		String key = "WSUnQ5U1Vm8Q9RY89hM7PZfOXVCkOzSXwAV%2BiX6s3fLs9nW5tpYtIZhfDoKdPH065PQF%2FLZGueqX%2FGNBPCgujg%3D%3D";
		
		String uri = reqUrl+"?serviceKey="+key+"&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315";
		
		try {
			url = new URL(uri);
			reader = new BufferedInputStream(url.openStream());
			int i=0;
			byte[] b = new byte[4096];
			while((i=reader.read(b)) != -1) {
				sb.append(new String(b,0,i));
			}
			logger.info(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp = new ResponseEntity<String>(sb.toString(),HttpStatus.OK);
		return resp;
	}

}







