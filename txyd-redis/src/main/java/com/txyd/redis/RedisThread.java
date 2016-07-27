package com.txyd.redis;

import org.apache.log4j.Logger;

public class RedisThread implements Runnable {
	private static final Logger logger = Logger.getLogger(RedisThread.class);
	@Override
	public void run() {
		RedisService redisService;
		try {
			for(int i=0;i<1000;i++){
				redisService = RedisService.getRedisService();
				String key="test_key"+Thread.currentThread().getId();
				String value="test_value"+Thread.currentThread().getName();
				if(! redisService.exists(key)){
					redisService.set(key,value);
					redisService.expire(key, 8);
				}
				System.out.println("key:"+key+";value:"+redisService.getString(key));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
