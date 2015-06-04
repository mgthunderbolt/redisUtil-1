package com.netease.redisUtil.service;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author hzjinwenbin
 *
 */
public class JedisSentinel {

	private JedisSentinelPool pool;
	private String masterName;
	private String sentinelAddressList;
	private int timeout = 3000;
	private String password;

	/**
	 * 获取连接池
	 * 
	 * @return
	 */
	public synchronized JedisSentinelPool getSentinelPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(500);
			config.setTestOnBorrow(false);
			config.setMaxIdle(5);
			config.setTestOnBorrow(true);
			String addressList[] = sentinelAddressList.split(",");
			Set<String> aset = new HashSet<String>();
			for (String s : addressList) {
				aset.add(s);
			}
			this.pool = new JedisSentinelPool(masterName, aset, config,
					timeout, password);
		}
		return pool;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public void setSentinelAddressList(String sentinelAddressList) {
		this.sentinelAddressList = sentinelAddressList;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
