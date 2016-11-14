package com.mc.db.dao.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author 
 * @param <T>
 * @param <ID>
 */
public interface BaseKeyDao<T,ID> {
	T getById(ID id);
	List<T> getByIds(List<ID> ids);

	Integer updateById(@Param("tb") T updateAlias, @Param("id") ID id);
    Integer updateByIds(@Param("tb") T updateAlias, @Param("ids") List<ID> ids);

	Integer deleteById(ID id);
	Integer deleteByIds(List<ID> list);
}