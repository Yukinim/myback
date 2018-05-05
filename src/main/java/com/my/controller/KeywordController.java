package com.my.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.dao.KeywordMapper;

@SpringBootApplication
@RestController
public class KeywordController {
	@Autowired
    private KeywordMapper mapper;
	 @RequestMapping(value="/selectKeywords",method=RequestMethod.GET)
	    public @ResponseBody Map<String,Object> selectKeywords(HttpServletRequest request) throws Exception{
	    	List<Map<String,Object>> resultList = mapper.selectKeywords();
	    	resultList = null;
	        Map<String, Object> jsonObject = new HashMap<String, Object>();
	        jsonObject.put("resultList", resultList);
	        return jsonObject;
	    }
	 
}
