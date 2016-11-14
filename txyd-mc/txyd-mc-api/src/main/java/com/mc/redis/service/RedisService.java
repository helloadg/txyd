package com.mc.redis.service;

import com.mc.ServiceResult;

public interface RedisService {
	/**
	 * 清空redis  内部用
	 *
	 * @return
	 */
	ServiceResult<Boolean> clearAllRedis();
	
}
