package com.my.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.common.util.MyCommonUtil;
import com.my.service.KeywordService;

@SpringBootApplication
@RestController
public class TestController {
    @Autowired
    private KeywordService service;
     
    String resultmsg="";
    
    @RequestMapping(value="/datacheck",method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> selectKeywords(HttpServletRequest request) throws Exception{
       	Map<String,Object> retObject = new HashMap<String,Object>();
       	retObject.put("totalCount", service.selectAllKeywordTotalCount());
       	
       	retObject.put("invalidDateCount", service.selectKeywordInvalidDataCount());
       	
       	String todayData = MyCommonUtil.getCurrentDate();
       	int currSeq = MyCommonUtil.getCurrDateSeq() - 1;
       	
       	Map<String,Object> query = new HashMap<String,Object>();
       	query.put("reg_date", todayData);
       	query.put("date_seq", currSeq);
       	
       	retObject.put("lastUpdatedData", service.selectLastUpdatedData(query));
       	
	 	return retObject;
    }
    @RequestMapping(value="/deleteKeyword",method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> deleteKeyword(HttpServletRequest request) throws Exception{
       	Map<String,Object> query = new HashMap<String,Object>();
       	query.put("keyword", request.getParameter("keyword"));
       	
       	service.deleteKeyword(query);
       	
       	Map<String,Object> retObject = new HashMap<String,Object>();
       	retObject.put("result", "{result:success}");
       	
	 	return retObject;
    }
}