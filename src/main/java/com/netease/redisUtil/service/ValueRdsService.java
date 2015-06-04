package com.netease.redisUtil.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author hzhuguoqun
 *
 *         2015年6月4日
 */
public interface ValueRdsService {
	public Set<String> getSValue(int db, String key);

	public Set<String> getZValue(int db, String key);

	public List<String> getLValue(int db, String key);

	public String getXValue(int db, String key);

	public List<String> getMValue(int db, String key);
	
	public Map<String,String> getHValue(int db, String key);
}
