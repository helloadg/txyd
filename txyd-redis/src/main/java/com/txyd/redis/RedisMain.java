/**
 * 
 * 在Redis中，List类型是按照插入顺序排序的字符串链表。和数据结构中的普通链表一样，我们可以在其头部(left)和尾部(right)添加新的元素。在插入时，如果该键并不存在，Redis将为该键创建一个新的链表。与此相反，如果链表中所有的元素均被移除，那么该键也将会被从数据库中删除。List中可以包含的最大元素数量是4,294,967,295(42亿左右)。
 * 
 * #在指定Key所关联的List Value的尾部插入参数中给出的所有Values。如果该Key不存在，该命令将在插入之前创建一个与该Key关联的空链表，之后再将数据从链表的尾部插入。如果该键的Value不是链表类型，该命令将返回相关的错误信息。
 * 
 * void rpush(final String key, final String... string)
 * 
 * #在指定Key所关联的List Value的头部插入参数中给出的所有Values。如果该Key不存在，该命令将在插入之前创建一个与该Key关联的空链表，之后再将数据从链表的头部插入。如果该键的Value不是链表类型，该命令将返回相关的错误信息。
 * 
 * void lpush(final String key, final String... string)
 * 
 * #返回指定Key关联的链表中元素的数量，如果该Key不存在，则返回0。如果与该Key关联的Value的类型不是链表，则返回相关的错误信息。
 * 
 * long llen(final String key)
 * 
 * #返回指定范围内元素的列表。该命令的参数start和end都是0-based。即0表示链表头部(leftmost)的第一个元素。其中start的值也可以为负值，-1将表示链表中的最后一个元素，即尾部元素，-2表示倒数第二个并以此类推。该命令在获取元素时，start和end位置上的元素也会被取出。如果start的值大于链表中元素的数量，空链表将会被返回。如果end的值大于元素的数量，该命令则获取从start(包括start)开始，链表中剩余的所有元素。
 * 
 * List<byte[]> lrange(final String key, final long start, final long end)
 * 
 * #该命令将仅保留指定范围内的元素，从而保证链接中的元素数量相对恒定。start和stop参数都是0-based，0表示头部元素。和其他命令一样，start和stop也可以为负值，-1表示尾部元素。如果start大于链表的尾部，或start大于stop，该命令不错报错，而是返回一个空的链表，与此同时该Key也将被删除。如果stop大于元素的数量，则保留从start开始剩余的所有元素。
 * 
 * void ltrim(final String key, final long start, final long end)
 * 
 * #该命令将返回链表中指定位置(index)的元素，index是0-based，表示头部元素，如果index为-1，表示尾部元素。如果与该Key关联的不是链表，该命令将返回相关的错误信息。
 * 
 * byte[] lindex(final String key, final long index)
 * 
 * #设定链表中指定位置的值为新值，其中0表示第一个元素，即头部元素，-1表示尾部元素。如果索引值Index超出了链表中元素的数量范围，该命令将返回相关的错误信息。
 * 
 * void lset(final String key, final long index, final String value)
 * 
 * #在指定Key关联的链表中，删除前count个值等于value的元素。如果count大于0，从头向尾遍历并删除，如果count小于0，则从尾向头遍历并删除。如果count等于0，则删除链表中所有等于value的元素。如果指定的Key不存在，则直接返回0,返回被删除的元素数量。
 * 
 * long lrem(final String key, long count, final String value)
 * 
 * #返回并弹出指定Key关联的链表中的第一个元素，即头部元素。如果该Key不存，返回null。
 * 
 * byte[] lpop(final String key)
 * 
 * #返回并弹出指定Key关联的链表中的最后一个元素，即尾部元素。如果该Key不存，返回nil。
 * 
 * byte[] rpop(final String key)
 * 
 * #原子性的从与srckey键关联的链表尾部弹出一个元素，同时再将弹出的元素插入到与dstkey键关联的链表的头部。如果srckey键不存在，该命令将返回null，同时不再做任何其它的操作了。如果srckey和dstkey是同一个键，则相当于原子性的将其关联链表中的尾部元素移到该链表的头部。
 * 
 * byte[] rpoplpush(final String srckey, final String dstkey)
 * 
 * 
 * 
 */



package com.txyd.redis;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.txyd.redis.entity.User;
import com.txyd.redis.utils.SerializationUtil;

import redis.clients.jedis.Jedis;

public class RedisMain {
	/**
	 * 清空
	 * @param jedis
	 */
	private static void clearRedis(Jedis jedis){
		Set<String> keys=jedis.keys("*");
		if(keys!=null&&keys.size()>0){
			jedis.del(keys.toArray(new String[keys.size()]));			
		}
	}
	/**
	 * 验证string类型
	 * @param jedis
	 */
	private static void testString(Jedis jedis){
		String key ="name";
		String value ="redis测试";
		String keyJson="json";
		String valueJson="{\"name\":\"测试json\",\"salary\":[{\"one\":1,\"two\":1},{\"one\":2,\"two\":2}]}";
		
		jedis.set(key, value);
		jedis.set(keyJson, valueJson);
		System.out.println(jedis.get(key));
		System.out.println(jedis.get(keyJson));
	}
	/**
	 * 验证mXXXXX类型:
	 * mset, mget,
	 * @param jedis
	 */
	private static void testMap(Jedis jedis){
		 /**
	     * mset 是设置多个key-value值 参数（key1,value1,key2,value2,...,keyn,valuen） mget
	     * 是获取多个key所对应的value值 参数（key1,key2,key3,...,keyn） 返回的是个list
	     */
		jedis.mset("name1", "aa", "name2", "bb", "name3", "cc");
		System.out.println(jedis.mget("name1", "name2", "name3"));
		System.out.println(jedis.mget(new String[]{"name1", "name2", "name3"} ));
	}
	/**
	 * 验证hmXXXXX类型:
	 * 	hmset,hmget,  hlen,hkeys,hvals,hdel
	 * @param jedis
	 */
	private static void testHMap(Jedis jedis){
		Map<String, String> map=new HashMap<>();
		for(int i=0;i<10;i++){
//			map.put("key_name"+i, "name"+i);
			map.put("key_id"+i, "id"+i);
		}
		jedis.hmset("mapKey", map);
		
		System.out.println(jedis.hlen("mapKey"));
		System.out.println(jedis.hkeys("mapKey"));
		System.out.println(jedis.hmget("mapKey", new String[]{"key_id1","key_id2","key_id3"} ));
		System.out.println(jedis.hvals("mapKey"));
		jedis.hdel("mapKey", "key_id0");
		System.out.println(jedis.hvals("mapKey"));
	}
	/**
	 * 验证LXXXX类型 :len,lpush,lrange,lpop,del,ltrim,lset
	 * @param jedis
	 */
	private static void testLList(Jedis jedis){
//		jedis.lpush("list",new String[] {"value1","value2"});
//		for(int i=0;i<10;i++){
//			jedis.lpush("list","value_"+i);
//		}
//
//		System.out.println(jedis.rpop("list"));
//		System.out.println(jedis.llen("list"));
//		System.out.println(jedis.lrem("list", 2, "value_9"));
//		System.out.println(jedis.ltrim("list", 0, 3));
//		System.out.println(jedis.lset("list", 1, "value_set"));
//		System.out.println(jedis.lindex("list", 1));
////		System.out.println(jedis.lrange("list", 0, -1));
////		System.out.println(jedis.lrange("list", 0, jedis.llen("list")));
////		System.out.println(jedis.lrange("list", 0, 1));
////		System.out.println(jedis.lpop("list"));
////		System.out.println(jedis.lrange("list", 0, jedis.llen("list")));
//////		jedis.del("list");
////		System.out.println(jedis.lrange("list", 0, jedis.llen("list")));
//		System.out.println(jedis.lrange("list", 0, -1));
		
		{
			for (int i = 0; i < 10; i++) {
				jedis.lpush("list", "value_" + i);
				jedis.lpush("list2", "value2_" + i);
			}
			System.out.println(jedis.lrange("list", 0, -1));
			System.out.println(jedis.lrange("list2", 0, -1));
			jedis.rpoplpush("list", "list2");
			System.out.println(jedis.lrange("list", 0, -1));
			System.out.println(jedis.lrange("list2", 0, -1));
		}
	
	}

	/**
	 * 验证set
	 * @param jedis
	 */
	private static void testSet(Jedis jedis){
		jedis.sadd("set1", new String[]{"value1","value2","value3","value3"});
		jedis.sadd("set2", new String[]{"value1","value2","value3","value3"});
		jedis.sadd("set2", new String[]{"value4"});
		jedis.smove("set1", "set2","value1");
		jedis.srem("set2", new String[]{"value1","value2","value3","value5"});
		System.out.println(jedis.smembers("set1"));
		System.out.println(jedis.smembers("set2"));
		System.out.println(jedis.spop("set1"));
		System.out.println(jedis.smembers("set1"));
		System.out.println(jedis.sunion(new String[]{"set1","set2"}));
	}
	
	/**
	 * 验证zset
	 * @param jedis
	 */
	private static void testZSet(Jedis jedis){
		Map<String, Double> map=new HashMap<>();
		map.put("value1", 1D);
		map.put("value2", 2D);
		map.put("value3", 3D);
		map.put("value-1", -1D);
		jedis.zadd("linkedset1", map);
		jedis.zadd("linkedset1", 0D, "value0");
		System.out.println();
		System.out.println(jedis.zrangeByScore("linkedset1", 0D, 2D));
		System.out.println(jedis.zrange("linkedset1", 0, -1));
		System.out.println(jedis.zcount("linkedset1", 0D, 2D));
		System.out.println(jedis.zcard("linkedset1"));
		jedis.zrem("linkedset1", "value-1");
		System.out.println(jedis.zrange("linkedset1", 0, -1));
	}
	
	/**
	 * 验证testSerialized
	 * @param jedis
	 */
	private static void testSerialized(Jedis jedis){
		User user =new User();
		user.setId(123);
		user.setName("name测试");
		
		jedis.set("user".getBytes(), SerializationUtil.serialize(user));

		byte[] bs = jedis.get("user".getBytes());
		User desUser = (User) SerializationUtil.deserialize(bs);
		
		System.out.println("id:"+desUser.getId()+";name:"+desUser.getName());
	
	}
	public static void main(String[] args) throws Exception{
		RedisService redisService=RedisService.getRedisService();
		Jedis jedis=redisService.getPool().getResource();		
//		clearRedis(jedis);
		System.out.println(jedis.keys("*o6S1lt9ZWN9v1YBu_RS9qzyoT9ko"));
//		System.out.println(jedis.get(""));

//		testString(jedis);
		
//		testMap(jedis);
		
//		testHMap(jedis);
		
//		testLList(jedis);
		
//		testSet(jedis);
		
//		testZSet(jedis);
		
//		testSerialized(jedis);
		

		
	}

}
