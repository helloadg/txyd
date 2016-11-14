package com.mc.db.dao.base;

import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 * @author 
 * @param <T>
 */
public interface BaseDao<T> {
	Integer selectCount(@Param("wb") T whereAlias);
	List<T> select(@Param("wb") T whereAlias, @Param("sort") LinkedHashMap<String, String> sort, @Param("offset") Integer offset, @Param("limit") Integer limit);

	Integer insert(T t);
	Integer insertNotNull(T t);
	Integer insertBatch(List<T> list);

    Integer update(@Param("tb") T updateAlias, @Param("wb") T whereAlias);

	Integer delete(T whereAlias);
}