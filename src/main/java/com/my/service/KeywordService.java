package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.KeywordMapper;
import com.my.service.KeywordService;

@Service
public class KeywordService{

	@Autowired
	KeywordMapper mapper;
	
	public int selectAllKeywordTotalCount() throws Exception {
		return mapper.selectKeywords();
	}

}
