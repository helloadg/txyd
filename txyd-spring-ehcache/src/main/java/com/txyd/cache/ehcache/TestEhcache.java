package com.txyd.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TestEhcache {

	public static void main(String[] args) {
		// 创建一个缓存管理器
		EhCacheManager ehCacheManager = EhCacheManager.getInstance();
		CacheManager singletonmanager=ehCacheManager.getCacheManager();
		// 建立一个缓存实例
		Cache memoryonlycache = new Cache("testcache", 5000, false, false, 5, 2);
		// 在内存管理器中添加缓存实例
		singletonmanager.addCache(memoryonlycache);
		Cache cache = singletonmanager.getCache("testcache");
		// 使用缓存
		for(int i=0;i<10;i++){
			Element element = new Element("key"+i, "value"+i);
			cache.put(element);
		}

		Element element = cache.get("key1");
		Object obj = element.getObjectValue();
		System.out.println(obj);

		int elementsinmemory = cache.getSize();
		System.out.println(elementsinmemory);


		cache.remove("key1");
		singletonmanager.shutdown();
		// manager.shutdown();
		System.out.println(2);
	}

}
