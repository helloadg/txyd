package com.mc.db.manager.base;

import java.util.LinkedHashMap;
import java.util.List;


public interface BaseManager<T> {

	/**
	 * 保存
	 * @param object
	 * @author 
	 * @return
	 */
	public int insert(T object);

	/**
	 * 保存
	 * @param object
	 * @author 
	 * @return
	 */
	public int insertNotNull(T object);

	/**
	 * 保存
	 * @param list
	 * @author 
	 * @return
	 */
	public int insertBatch(List<T> list);

    /**
     * 通过条件筛选修改
     * @param update
     * @param where
     * @author 
     * @return
     */
    public int update(T update, T where);

	/**
	 * 删除
	 * @param object
	 * @author 
	 * @return
	 */
	public int delete(T object);

	/**
	 * 根据条件查询
	 * @param object ：查询条件
	 * @param sort ：排序规则
	 * @param offset ：起始值
	 * @param limit ：返回多少条
	 * @author 
	 * @return
	 */
	public List<T> select(T object, LinkedHashMap<String, String> sort, Integer offset, Integer limit);

	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @param offset ：起始值
	 * @param limit ：返回多少条
	 * @author 
	 * @return
	 */
	public List<T> select(T object, Integer offset, Integer limit);
	
	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @param sort ：排序规则
	 * @author 
	 * @return
	 */
	public List<T> select(T object, LinkedHashMap<String, String> sort);
	
	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @author 
	 * @return
	 */
	public List<T> select(T object);

	/**
	 * 获取总条数
	 * @param object
	 * @author 
	 * @return
	 */
	public int selectCount(T object);

}