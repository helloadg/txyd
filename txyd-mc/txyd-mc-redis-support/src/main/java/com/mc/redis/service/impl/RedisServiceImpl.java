package com.mc.redis.service.impl;


import com.mc.ServiceResult;
import com.mc.common.exception.SystemException;
import com.mc.db.Constants;
import com.mc.redis.service.RedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {
	private Logger logger = Logger.getLogger(RedisServiceImpl.class);
	
	@Autowired
	private BaseRedisServiceImpl baseRedisServiceImpl;

	@Override
	public ServiceResult<Boolean> clearAllRedis() throws SystemException {
    	
        Jedis jedis = null;
        try {
	        ServiceResult<Boolean> result=new ServiceResult<>();
	        result.setSuccess(true);
	        result.setBody(true);
        	jedis=this.baseRedisServiceImpl.getPool().getResource();
			Set<String> keys =jedis.keys(Constants.REDIS_SYSTEM_KEY+"*");
			if(keys!=null&&keys.size()>0){
				this.baseRedisServiceImpl.delete(keys.toArray(new String[keys.size()]));
			}
	        return result;
        }catch(Throwable e){
	        throw new SystemException("clear redis faile :"+e.getMessage(),e);
        } finally {
            if (jedis != null) {
	            jedis.close();
            }
        }
		
	}
	
}
