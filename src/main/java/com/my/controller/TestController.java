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

import com.my.dao.TestMapper;

@SpringBootApplication
@RestController
public class TestController {
    @Autowired
    private TestMapper mapper;
     
    String resultmsg="";
     
    @RequestMapping(value="/test",method=RequestMethod.GET)
    public @ResponseBody Map<String,Object> checkid(HttpServletRequest request) throws Exception{
    	String paramTest = request.getParameter("param");
    	System.out.println("param : " +paramTest);
    	
    	List<Map<String,Object>> resultList = mapper.selectMyTestTable();
         
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        
        int max = 0;
        for(Map<String,Object> result : resultList) {
        	int id = (Integer) result.get("id");
        	max = Math.max(id, max);
        }
         
        Map<String,Object> nextInsert = new HashMap<String,Object>();
        nextInsert.put("id", max+1);
        nextInsert.put("name", "스프링부트에서 넣은 이름");
        
        mapper.insertMyTestTable(nextInsert);
        
        jsonObject.put("resultList", resultList);
         
        return jsonObject;
    }
     
}