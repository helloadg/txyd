package com.txyd.mybatis.dao.base;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @author 
 * @param <T>
 */
public interface BaseNoKeyDao<T> {
	Integer selectCount(@Param("wb") T whereAlias);
	List<T> select(@Param("wb") T whereAlias, @Param("sort") LinkedHashMap<String, String> sort, @Param("offset") Integer offset, @Param("limit") Integer limit, @Param("isForUpdate") Boolean isForUpdate);

	Integer insert(T t);
	Integer insertNotNull(T t);
	Integer insertBatch(List<T> list);
    Integer insertNotExists(@Param("map") Map<T, T> insert);

    Integer update(@Param("tb") T updateAlias, @Param("wb") T whereAlias);

	Integer delete(T whereAlias);
}