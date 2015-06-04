package com.netease.redisUtil.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.netease.redisUtil.service.JedisSentinel;
import com.netease.redisUtil.service.ValueRdsService;
@Repository("ValueRdsService")
public class ValueRdsServiceImpl implements ValueRdsService {
	
	@Autowired
	private JedisSentinel jedisSentinel;
	
	/**
	 * 获取集合类型的value
	 */
	@Override
	public Set<String> getSValue(int db, String key) {
		Jedis jedis = jedisSentinel.getSentinelPool().getResource();
		jedis.select(db);
		Set<String> valueSet = jedis.smembers(key);
		jedis.close();
		return valueSet;
	}

	@Override
	public Set<String> getZValue(int db, String key) {
		Jedis jedis = jedisSentinel.getSentinelPool().getResource();
		jedis.select(db);
		Set<String> valueSet = jedis.zrange(key, 0, 1000);
		jedis.close();
		return valueSet;
	}

	@Override
	public List<String> getLValue(int db, String key) {
		Jedis jedis = jedisSentinel.getSentinelPool().getResource();
		jedis.select(db);
		List<String> valueList = jedis.lrange(key, 0, 1000);
		jedis.close();
		return valueList;
	}

	@Override
	public String getXValue(int db, String key) {
		Jedis jedis = jedisSentinel.getSentinelPool().getResource();
		jedis.select(db);
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	@Override
	public List<String> getMValue(int db, String key) {
		Jedis jedis = jedisSentinel.getSentinelPool().getResource();
		jedis.select(db);
		List<String> valueList = jedis.mget(key);
		jedis.close();
		return valueList;
	}

	@Override
	public Map<String,String> getHValue(int db, String key) {
		Jedis jedis = jedisSentinel.getSentinelPool().getResource();
		jedis.select(db);
		Map<String,String> valueMap = jedis.hgetAll(key);
		jedis.close();
		return valueMap;
	}
	

}
