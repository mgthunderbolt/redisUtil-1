package com.netease.redisUtil.service;

import java.util.Set;

/**
 * key
 * 
 * @author hzhuguoqun
 *
 *         2015年6月4日
 */
public interface KeyRdsService {
	/**
	 * 查询key列表
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> queryKeys(int db, String key);

	/**
	 * 删除key
	 * @param db
	 * @param key
	 */
	public void delKey(int db, String[] key);
}
