package com.txyd.database.dao;

import java.util.LinkedHashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @author 
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T,ID> {
	T getById(ID id);
	List<T> getByIds(List<ID> ids);
	Integer selectCount(@Param("t") T t);
	List<T> select(@Param("t") T t,@Param("sort") LinkedHashMap<String,String> sort, @Param("offset") Integer offset,  @Param("limit") Integer limit);
	
	Integer insert(T t);
	Integer insertNotNull(T t);
	Integer insertBatch(List<T> list);
	
	Integer updateById(@Param("t") T t, @Param("id") ID id);
	
	Integer delete(T t);
	Integer deleteById(ID id);
	Integer deleteByIds(List<ID> list);
}
