package com.txyd.mybatis.service.base;

import java.util.List;


public interface BaseService<T,ID>  extends BaseNoKeyService<T> {

	/**
	 * 通过id单条修改
	 * @param t
	 * @param id
	 * @author 
	 * @return
	 */
	public int updateById(T t, ID id);

    /**
     * 通过ids批量修改
     * @param t
     * @param ids
     * @author 
     * @return
     */
    public int updateByIds(T t, List<ID> ids);

	/**
	 * 删除
	 * @param id
	 * @author 
	 * @return
	 */
	public int deleteById(ID id);

	/**
	 * 删除
	 * @param ids
	 * @author 
	 * @return
	 */
	public int deleteByIds(List<ID> ids);

	/**
	 * 
	 * @param id
	 * @author 
	 * @return
	 */
	public T getById(ID id);

	/**
	 *
	 * @param id
	 * @param isForUpdate
	 * @author 
	 * @return
	 */
	public T getById(ID id, Boolean isForUpdate);

	/**
	 * 
	 * @param ids
	 * @author 
	 * @return
	 */
	public List<T> getByIds(List<ID> ids);

	/**
	 *
	 * @param ids
	 * @param isForUpdate
	 * @author 
	 * @return
	 */
	public List<T> getByIds(List<ID> ids, Boolean isForUpdate);

}