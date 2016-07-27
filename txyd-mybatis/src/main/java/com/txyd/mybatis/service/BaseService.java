package com.txyd.mybatis.service;

import java.util.LinkedHashMap;
import java.util.List;


public interface BaseService<T,ID> {
	/**
	 * 保存
	 * @param object
	 * @author 
	 * @return
	 */
	int insert(T object);

	/**
	 * 保存
	 * @param object
	 * @author 
	 * @return
	 */
	int insertNotNull(T object);

	/**
	 * 保存
	 * @param list
	 * @author 
	 * @return
	 */
	int insertBatch(List<T> list);

	/**
	 * 修改
	 * @param t
	 * @param id
	 * @author 
	 * @return
	 */
	int updateById(T t, ID id);

	/**
	 * 删除
	 * @param object
	 * @author 
	 * @return
	 */
	int delete(T object);

	/**
	 * 删除
	 * @param id
	 * @author 
	 * @return
	 */
	int deleteById(ID id);

	/**
	 * 删除
	 * @param ids
	 * @author 
	 * @return
	 */
	int deleteByIds(List<ID> ids);
	/**
	 * 
	 * @param id
	 * @author 
	 * @return
	 */
	T getById(ID id);
	/**
	 * 
	 * @param ids
	 * @author 
	 * @return
	 */
	List<T> getByIds(List<ID> ids);

	/**
	 * 根据条件查询
	 * @param object ：查询条件
	 * @param sort ：排序规则
	 * @param offset ：起始值
	 * @param limit ：返回多少条
	 * @author 
	 * @return
	 */
	List<T> select(T object, LinkedHashMap<String, String> sort, Integer offset, Integer limit);

	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @param offset ：起始值
	 * @param limit ：返回多少条
	 * @author 
	 * @return
	 */
	List<T> select(T object, Integer offset, Integer limit);
	
	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @param sort ：排序规则
	 * @author 
	 * @return
	 */
	List<T> select(T object, LinkedHashMap<String, String> sort);
	
	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @author 
	 * @return
	 */
	List<T> select(T object);

	/**
	 * 获取总条数
	 * @param object
	 * @author 
	 * @return
	 */
	int selectCount(T object);

}