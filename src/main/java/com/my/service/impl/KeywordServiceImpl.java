package com.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.KeywordMapper;
import com.my.service.KeywordService;

@Service("keywordService")
public class KeywordServiceImpl implements KeywordService {

	@Autowired
	KeywordMapper mapper;
	
	@Override
	public int selectAllKeywordTotalCount() throws Exception {
		return mapper.selectKeywords();
	}

}
