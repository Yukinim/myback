package com.my.dao;

import java.util.List;
import java.util.Map;

public interface TestMapper {
    
    public List<Map<String,Object>> selectMyTestTable() throws Exception;
    public void insertMyTestTable(Map<String,Object> param) throws Exception; 
}


