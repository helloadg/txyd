package com.txyd.mybatis.dao.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 
 * @author 
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T,ID> extends BaseNoKeyDao<T>  {
	T getById(@Param("id") ID id, @Param("isForUpdate") Boolean isForUpdate);
	List<T> getByIds(@Param("ids") List<ID> ids, @Param("isForUpdate") Boolean isForUpdate);

	Integer updateById(@Param("tb") T updateAlias, @Param("id") ID id);
    Integer updateByIds(@Param("tb") T updateAlias, @Param("ids") List<ID> ids);

	Integer deleteById(ID id);
	Integer deleteByIds(List<ID> list);
}