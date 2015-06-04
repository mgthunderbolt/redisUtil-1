package com.netease.redisUtil.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.netease.redisUtil.service.JedisSentinel;
import com.netease.redisUtil.service.KeyRdsService;

@Repository("KeyRdsService")
public class KeyRdsServiceImpl implements KeyRdsService {
	@Autowired
	private JedisSentinel jedisSentinel;

	@Override
	public Set<String> queryKeys(int db, String key) {
		Jedis jedis = jedisSentinel.getSentinelPool().getResource();
		jedis.select(db);
		return jedis.keys(key);
	}

}
