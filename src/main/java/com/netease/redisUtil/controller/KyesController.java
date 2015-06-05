package com.netease.redisUtil.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netease.redisUtil.service.KeyRdsService;

/**
 * 
 * @author hzhuguoqun
 *
 *         2015年6月4日
 */
@Controller
@RequestMapping("/key")
public class KyesController {
	@Autowired
	private KeyRdsService keyRdsService;

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 查询key列表
	 * 
	 * @param db
	 * @param key
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query")
	public String queryKeys(int db, String key) {
		Set<String> keys = keyRdsService.queryKeys(db, key);
		return JSON.toJSONString(keys);
	}

	/**
	 * 删除key
	 * 
	 * @param db
	 * @param key
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public void delKey(int db, String key) {
		Set<String> keys = keyRdsService.queryKeys(db, key);
		String[] keysDel = (String[]) keys.toArray(new String[] {});
		for (String temp : keysDel) {
			logger.debug("Del key : " + temp);
		}
		keyRdsService.delKey(db, keysDel);
	}
}
