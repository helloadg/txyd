package com.mc.db.service;


import com.mc.ServiceResult;

import java.util.LinkedHashMap;
import java.util.List;

public interface BaseService<T,ID>  {
	/**
	 * 保存
	 * @param object
	 * @author
	 * @return
	 */
	ServiceResult<T> insert(T object);
	

	/**
	 * 保存
	 * @param object
	 * @author
	 * @return
	 */
	ServiceResult<T> insertNotNull(T object);

	/**
	 * 保存
	 * @param list
	 * @author
	 * @return
	 */
	ServiceResult<Integer> insertBatch(List<T> list);

	/**
	 * 修改
	 * @param t
	 * @param id
	 * @return
	 */
	ServiceResult<T> updateById(T t, ID id);

	/**
	 * 删除
	 * @param object
	 * @author
	 * @return
	 */
	//ServiceResult<Integer> delete(T object);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	//ServiceResult<Integer> deleteById(ID id);

	/**
	 * 删除
	 * @param list
	 * @author
	 * @return
	 */
	//ServiceResult<Integer> deleteByIds(List<ID> list);

	/**
	 *
	 * @param id
	 * @author
	 * @return
	 */
	ServiceResult<T> getById(ID id);

	/**
	 *
	 * @param id
	 * @author
	 * @return
	 */
	ServiceResult<List<T>> getByIds(List<ID> id);

	/**
	 * 根据条件查询
	 * @param object
	 * @author
	 * @return
	 */
	ServiceResult<List<T>> select(T object, LinkedHashMap<String, String> sort, Integer offset, Integer limit);

	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @param offset ：起始值
	 * @param limit ：返回多少条
	 * @author
	 * @return
	 */
	ServiceResult<List<T>>  select(T object, Integer offset, Integer limit);

	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @param sort ：排序规则
	 * @author
	 * @return
	 */
	ServiceResult<List<T>>  select(T object, LinkedHashMap<String, String> sort);

	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @author
	 * @return
	 */
	ServiceResult<List<T>>  select(T object);

	/**
	 * 获取总条数
	 * @param object
	 * @author
	 * @return
	 */
	ServiceResult<Integer>  selectCount(T object);
}
