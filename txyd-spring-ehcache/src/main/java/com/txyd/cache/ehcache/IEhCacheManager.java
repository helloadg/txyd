package com.txyd.cache.ehcache;

import net.sf.ehcache.CacheManager;

public interface IEhCacheManager {	 
    public static String CCBTMANAGER = "CCBTMANAGER";
	public void setCacheManager(CacheManager cacheManager);
	
    public CacheManager getCacheManager();
	
}
