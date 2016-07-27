package com.txyd.cache.ehcache;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class LookupManager {
//	
//	/***单态对象*/
//	private static LookupManager instance;
//	/***日志*/
//	private static Logger log = Logger.getLogger(LookupManager.class);
//
//	/***数据字典类别*/
//	private LinkedHashMap<String, LinkedHashMap<String, String>> oracleLookup;
//	/***数据字典item*/
//	private LinkedHashMap<String, String> oracleLookupItem;
//
//	/***数据字典类别keyName*/
//	public static String lookupKeyName     = "oracleLookup";
//	/***数据字典Item keyName*/
//	public static String lookupItemKeyName = "oracleLookupItem";
//
//	/***构造方法*/
//	private LookupManager() {}
//	
//
//	/***取单态方法*/
//	public static synchronized LookupManager getInstance() {
//		
//		if (instance != null) {
//			return instance;
//		} else {
//			instance = new LookupManager();
//		}
//		return instance;
//	}
//	/***设置数据字典类别*/
//	private void setOracleLookup(
//			LinkedHashMap<String, LinkedHashMap<String, String>> oracleLookup) {
//		this.oracleLookup = oracleLookup;
//	}
//	/***取得数据字典Item*/
//	private LinkedHashMap<String, String> getOracleLookupItem() {
//		return oracleLookupItem;
//	}
//	/***设置数据字典Item*/
//	private void setOracleLookupItem(
//			LinkedHashMap<String, String> oracleLookupItem) {
//		this.oracleLookupItem = oracleLookupItem;
//	}
//
//	/***取数据字典Item值*/
//	public String getOracleValue(String name, String code) {
//		String value = null;
//		setOracleLookupItem(getOracleLookup().get(name));
//		if (getOracleLookupItem() != null) {
//			value = getOracleLookupItem().get(code);
//		}
//		return value;
//	}
//
//	/***增加数据字典Item*/
//	private void addOracleLookupItem(String name, String code, String value) {
//		if (name == null || code == null || value == null) {
//			return;
//		}
//		oracleLookupItem = oracleLookup.get(name);
//		if (oracleLookupItem == null) {
//			oracleLookupItem = new LinkedHashMap<String, String>();
//			oracleLookupItem.put("", "请选择");
//		}
//		oracleLookupItem.put(code, value);
//		oracleLookup.put(name, oracleLookupItem);
//	}
//	/***加载数据字典*/
//	private int loadOracleLookup(String name) {
//		CodeValuePo codeValuePo= new CodeValuePo();
//		if (name != null) {
//			codeValuePo.setType(name);
//		}
//		int rowsCached = 0;
//
//		try {
//			
//			List<CodeValuePo> list = codeValueService.findCodeValue(codeValuePo);
//			for (int i = 0; i < list.size(); i++) {
//				addOracleLookupItem(list.get(i).getType(), list.get(i).getKey(), list.get(i).getValue());
//				rowsCached++;
//				
//			}
//		} catch (Exception e) {
//			log.error("查询数据库中的字典表时发生异常:", e);
//			e.printStackTrace();
//		} finally {
//			
//			
//		}
//		return rowsCached;
//	}
//	/***
//	 * 数据字典管理器初始化
//	 * @param applicationContext spring上下文
//	 **/
//	public void initialize() {
//		oracleLookup = new LinkedHashMap<String, LinkedHashMap<String, String>>();
//		refreshLookupCache();
//		
//		
//	}
//	/***
//	 * 重新加载数据字典
//	 * @param name 数据字典类别名称
//	 **/
//	public int reloadOracle(String name) {
//		log.info("开始刷新CRM的字典表：" + name);
//		oracleLookup.remove(name);
//		int count = loadOracleLookup(name);
//		log.info("完成加载CRM的字典表，共计：" + count);
//		Element lookupElement = new Element(lookupKeyName, oracleLookup);		
//		getCache(IEhCacheManager.CCBTMANAGER).put(lookupElement);
//		log.info("CRM的字典表缓存刷新完成，共计：" + count);
//		return 0;
//	}
//	/***
//	 * 取Cache
//	 * @param name cacheName
//	 **/
//	private Cache getCache(String name) {
//    	return (Cache) EhCacheManager.getInstance().getCacheManager().getCache(name);
//    }
//
//
//	/***刷新字典缓存*/
//	private void refreshLookupCache() {
//		setOracleLookup(new LinkedHashMap<String, LinkedHashMap<String, String>>());
//		log.info("开始刷新CCBTMANAGER的字典表缓存……");
//		int count = loadOracleLookup(null);
//		Element lookupElement = new Element(lookupKeyName, oracleLookup);
//		log.info("CCBTMANAGER的字典表缓存刷新完成，共计：" + count);
//		getCache(IEhCacheManager.CCBTMANAGER).put(lookupElement);
//	}
//	
//
//
//	/***取得数据字典类别*/
//	private LinkedHashMap<String, LinkedHashMap<String, String>> getOracleLookup() {
//		Cache lookupCache = getCache(IEhCacheManager.CCBTMANAGER);
//		Element lookupElement = lookupCache.get(lookupKeyName);
//		if (lookupElement == null) {
//			refreshLookupCache();
//		}		
//		return getLookupFromCache();
//	}
//	
//	/***
//	 * from Cache取oracleLookup
//	 **/
//	@SuppressWarnings("unchecked")
//	private LinkedHashMap<String, LinkedHashMap<String, String>> getLookupFromCache() {
//		Cache lookupCache = getCache(IEhCacheManager.CCBTMANAGER);
//		Element lookupElement = lookupCache.get(lookupKeyName);
//		
//    	return (LinkedHashMap<String, LinkedHashMap<String, String>>)
//    			lookupElement.getObjectValue();
//    }
//	
//	/***取数据字典Item map*/
//	public LinkedHashMap<String, String> getOracleValues(String name) {
//		return getOracleLookup().get(name);
//	}
//	

}
