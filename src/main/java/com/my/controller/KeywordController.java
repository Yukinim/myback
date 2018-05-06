package com.my.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.common.util.MyCommonUtil;
import com.my.service.KeywordService;

@CrossOrigin(origins = "*")
@SpringBootApplication
@RestController
public class KeywordController {

	@Autowired
	private KeywordService service;
	
	private static final int MY_BATCH_RUN_MINUTE_SCHEDULE = 12;

	@RequestMapping(value="/api/v1/mykeywords",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> mykeywords(HttpServletRequest request) throws Exception{
		String last_date = MyCommonUtil.getCurrentDate();
		String first_date = MyCommonUtil.getOneWeekAgoDate();
		int curr_dateseq = MyCommonUtil.getCurrDateSeq();
		
		//배치 실행시간인 12분에 대한 예외 처리
		if(MyCommonUtil.getCurrMin() >= MY_BATCH_RUN_MINUTE_SCHEDULE) {
			//이번 시간 배치 실행된 이후임.
			//만약 11일 경우 , 7일전 11시부터 ~ 오늘 10시까지 가져옴.
			// 여기서 +1을 하게 되면, 7일전 12시부터 오늘 11시까지 가져옴. (오늘 11시 배치가 완료 되었어야 함)
			curr_dateseq++;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date lastDt = df.parse(last_date);
		Date firstDt = df.parse(first_date);
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(lastDt);
		cal.add(Calendar.DATE, -1);
		String date_to = df.format(cal.getTime());
		
		cal.setTime(firstDt);
		cal.add(Calendar.DATE, 1);
		String date_from = df.format(cal.getTime());
		
		Map<String,Object> query = new HashMap<String,Object>();
		query.put("date_from", date_from);
		query.put("date_to", date_to);
		query.put("last_date", last_date);
		query.put("first_date", first_date);
		query.put("curr_dateseq", curr_dateseq);
		List<Map<String,Object>> keywordList = service.selectKeywordsCntForLastSevenDays(query);
		String currKey = null;
		long currCnt = 0;
		Map<String,Object> curr = null;
		List<Map<String,Object>> keywordListRemoveDuplication = new ArrayList<Map<String,Object>>();
		
		for(Map<String,Object> keyword : keywordList) {
			if(!keyword.get("KEYWORD").equals(currKey)) {
				//새로운 row
				curr = keyword;
				currCnt = (long) keyword.get("CNT");
				currKey = (String) keyword.get("KEYWORD");
				keywordListRemoveDuplication.add(keyword);
			}else {
				// 중복된 row -> cnt만 증가시켜줌
				currCnt += (long) keyword.get("CNT");
				curr.put("CNT", currCnt);
			}
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("data", keywordListRemoveDuplication);
		
		List<Map<String,Object>> keywordRelList = service.selectKeywordsRelData();
		
		int leftIdx = 0;
		for( ; leftIdx < keywordListRemoveDuplication.size(); leftIdx ++) {
			Map<String,Object> left = keywordListRemoveDuplication.get(leftIdx);
			List<Map<String,Object>> relKeywords = new ArrayList<Map<String,Object>>();
			left.put("relKeywords", relKeywords);
			for(int rightIdx = 0 ; rightIdx < keywordRelList.size(); rightIdx ++) {
				Map<String,Object> rel = keywordRelList.get(rightIdx);
				if(left.get("KEYWORD").equals(rel.get("KEY_ONE"))){
					Map<String,Object> each = new HashMap<String,Object>();
					each.put("KEYWORD",rel.get("KEY_TWO"));
					each.put("COUNT",rel.get("CNT"));
					relKeywords.add(each);
				}else if(left.get("KEYWORD").equals(rel.get("KEY_TWO"))) {
					Map<String,Object> each = new HashMap<String,Object>();
					each.put("KEYWORD",rel.get("KEY_ONE"));
					each.put("COUNT",rel.get("CNT"));
					relKeywords.add(each);
				}
			}
		}
		return ret;
	}
}
