package com.netease.redisUtil.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netease.redisUtil.service.KeyRdsService;
/**
 * 
 * @author hzhuguoqun
 *
 * 2015年6月4日
 */
@Controller
@RequestMapping("/key")
public class KyesController {
	@Autowired
	private KeyRdsService keyRdsService;

	@ResponseBody
	@RequestMapping("/query")
	public String queryKeys(int db, String key) {
		Set<String> keys = keyRdsService.queryKeys(db, key);
		return JSON.toJSONString(keys);
	}
}
