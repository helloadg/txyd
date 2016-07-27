package com.txyd.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestMain {
	
	public static void main(String[] args) throws Exception{
		
		RedisService redisService = RedisService.getRedisService();
		{	
			
			Set<String> set =redisService.keys("market_*");
			if(set.size()>0){
				redisService.delete(set.toArray(new String[set.size()]));				
			}
		}
		{
//			Set<String> keys= redisService.keys("pro_api_tagpromotion_area*");
//			String dd=redisService.getPool().getResource().hget("pro_api_tagpromotion_area_rule:1:15:2817","ci_id");
//			System.out.println(dd);
//			System.out.println(keys);
//			for(String key:keys){
////				redisService.getPool().getResource().lpop(key);
////				redisService.getPool().getResource().rpop(key);
////				redisService.getPool().getResource().hgetAll(key);
////				long count = redisService.getPool().getResource().llen(key);
//				redisService.getPool().getResource().lrange(key, 0, 10);
////				redisService.getPool().getResource().mget(keys);
//				
//			}
		}
		{
//			System.out.println(redisService.getString("weixin_redis_text_queue"));
//			System.out.println(redisService.getString("market"));
			
		}

		{
//			for(int i=0;i<10;i++){
//				redisService.set("market_"+i, "market_"+i);			
//			}
		}
		{
//			Set<String> set =redisService.keys("test_*");
//			redisService.delete(set.toArray(new String[set.size()]));
		}
		
		
//		List<Thread> list=new ArrayList<>();
//		for(int i=0;i<1000;i++){
//			list.add(new Thread(new RedisThread()));			
//		}
//		for(Thread thread:list){
//			thread.start();				
//		}
	}
}
