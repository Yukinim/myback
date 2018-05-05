package com.my.dao;

import java.util.List;
import java.util.Map;

public interface KeywordMapper {
    public List<Map<String,Object>> selectKeywords() throws Exception;
}


