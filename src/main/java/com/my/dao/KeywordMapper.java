package com.my.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface KeywordMapper {
    public int selectKeywordCount() throws Exception;
    
    public int selectKeywordInvalidDataCount() throws Exception;
    
    public List<Map<String,Object>> selectLastUpdatedData(Map<String,Object> query) throws Exception;
    
    public List<Map<String,Object>> selectKeywordsCntForLastSevenDays(Map<String,Object> query) throws Exception;
    
    public List<Map<String,Object>> selectKeywordsRelData() throws Exception;
    
}