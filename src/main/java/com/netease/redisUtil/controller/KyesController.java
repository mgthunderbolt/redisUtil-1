package com.netease.redisUtil.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.redisUtil.service.KeyRdsService;

@Controller
@RequestMapping("/key")
public class KyesController {
	@Autowired
	private KeyRdsService keyRdsService;
	
	@ResponseBody
	@RequestMapping("/query")
	public String queryKeys(){
		Set<String> keys = keyRdsService.queryKeys(0, "*");
		StringBuilder keysSb = new StringBuilder();
		for(String key : keys){
			keysSb.append(key);
		}
		return keysSb.toString();
	}
}
