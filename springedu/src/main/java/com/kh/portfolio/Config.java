package com.kh.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kh.portfolio.common.page.FindCriteria;
import com.kh.portfolio.common.page.PageCriteria;
import com.kh.portfolio.common.page.RecordCriteria;

@Configuration
public class Config {
	@Bean
	public RecordCriteria recordCriteria() {
		return new RecordCriteria();
	}
	@Bean
	public PageCriteria pageCriteria() {
		return new PageCriteria();
	}
	@Bean
	public FindCriteria findCriteria() {
		return new FindCriteria();
	}
}
