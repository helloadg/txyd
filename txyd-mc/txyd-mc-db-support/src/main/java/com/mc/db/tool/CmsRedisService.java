package com.mc.db.tool;


import com.mc.redis.service.impl.BaseRedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * Created by Mxl on 2016/10/12.
 */
@Service
public class CmsRedisService extends BaseRedisServiceImpl {
    public static final Logger logger = LoggerFactory.getLogger(CmsRedisService.class);
    public final static String VIRTUAL_COURSE_PREX = "_lc_vc_";

    /**
     * 设置 list
     * @param <T>
     * @param key
     */
    public <T> void setList(String key ,List<T> list){
        Jedis jedis = null;
        try {
            jedis = super.getPool().getResource();
            jedis.set(key.getBytes(), ObjectTranscoder.serialize(list));
        }finally {
            if(jedis != null) {
                jedis.close();
            }

        }
    }

    /**
     * 获取list
     * @param <T>
     * @param key
     * @return list
     */
    public <T> List<T> getList(String key){
        Jedis jedis = null;
        try{
            jedis = super.getPool().getResource();
            if(jedis == null || !jedis.exists(key.getBytes())){
                return null;
            }
            byte[] in = jedis.get(key.getBytes());
            List<T> list = (List<T>) ObjectTranscoder.deserialize(in);
            return list;
        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }



    }

    /**
     * 设置 map
     * @param <T>
     * @param key
     */
    public <T> void setMap(String key ,Map<String,T> map){
        Jedis jedis = null;
        try {
            jedis = super.getPool().getResource();
            jedis.set(key.getBytes(), ObjectTranscoder.serialize(map));
        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取list
     * @param <T>
     * @param key
     * @return list
     */
    public <T> Map<String,T> getMap(String key){
        Jedis jedis = null;
        try{
            jedis = super.getPool().getResource();
            if(jedis == null || !jedis.exists(key.getBytes())){
                return null;
            }
            byte[] in = jedis.get(key.getBytes());
            Map<String,T> map = (Map<String, T>) ObjectTranscoder.deserialize(in);
            return map;
        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
