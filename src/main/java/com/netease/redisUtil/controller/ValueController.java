package com.netease.redisUtil.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netease.redisUtil.service.ValueRdsService;

/**
 * 
 * @author hzhuguoqun
 *
 *         2015年6月4日
 */
@Controller
@RequestMapping("/value")
public class ValueController {
	private final static String S = "S";
	private final static String Z = "Z";
	private final static String L = "L";
	private final static String X = "X";
	private final static String M = "M";
	private final static String H = "H";

	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ValueRdsService valueRdsService;

	@ResponseBody
	@RequestMapping("/query")
	public String getValue(int db, String key) {
		logger.debug("db:" + db + "     key:" + key);
		String value = "出错了";
		if (key.startsWith(S)) {
			Set<String> valueSet = valueRdsService.getSValue(db, key);
			for (String item : valueSet) {
				value = item;
			}
		} else if (key.startsWith(Z)) {
			Set<String> valueSet = valueRdsService.getZValue(db, key);
			for (String item : valueSet) {
				value = item;
			}
		} else if (key.startsWith(L)) {
			List<String> valueList = valueRdsService.getLValue(db, key);
			for (String item : valueList) {
				value = item;
			}
		} else if (key.startsWith(X)) {
			value = valueRdsService.getXValue(db, key);
		} else if (key.startsWith(M)) {
			List<String> valueList = valueRdsService.getMValue(db, key);
			for (String item : valueList) {
				value = item;
			}
		} else if (key.startsWith(H)) {
			Map<String, String> map = valueRdsService.getHValue(db, key);
			value = JSON.toJSONString(map);
		}
		return value;
	}
}
