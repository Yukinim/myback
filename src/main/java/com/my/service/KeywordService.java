package com.my.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.KeywordMapper;

@Service
public class KeywordService {

	@Autowired
	KeywordMapper mapper;

	public int selectAllKeywordTotalCount() throws Exception {
		return mapper.selectKeywordCount();
	}

	public int selectKeywordInvalidDataCount() throws Exception {
		return mapper.selectKeywordInvalidDataCount();
	}

	public List<Map<String, Object>> selectLastUpdatedData(Map<String, Object> query) throws Exception {
		return mapper.selectLastUpdatedData(query);
	}

	public List<Map<String, Object>> selectKeywordsCntForLastSevenDays(Map<String, Object> query) throws Exception {
		return mapper.selectKeywordsCntForLastSevenDays(query);
	}
	
	public List<Map<String, Object>> selectKeywordsRelData() throws Exception {
		return mapper.selectKeywordsRelData();
	}
}
