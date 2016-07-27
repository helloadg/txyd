package com.txyd.database.service;

import java.util.LinkedHashMap;
import java.util.List;

public interface BaseService<T, ID> {

    /**
     * 保存
     *
     * @param object
     * @return
     * @author
     */
    int insert(T object);

    /**
     * 保存
     *
     * @param object
     * @return
     * @author
     */
    int insertNotNull(T object);

    /**
     * 保存
     *
     * @param list
     * @return
     * @author
     */
    int insertBatch(List<T> list);

    /**
     * 修改
     *
     * @param t
     * @param id
     * @return
     */
    int updateById(T t, ID id);


    /**
     * 删除
     *
     * @param object
     * @return
     * @author
     */
    int delete(T object);

    /**
     * 删除
     *
     * @param id
     * @return
     * @author
     */
    int deleteById(ID id);

    /**
     * 删除
     *
     * @param list
     * @return
     * @author
     */
    int deleteByIds(List<ID> list);

    /**
     * @param id
     * @return
     * @author
     */
    T getById(ID id);

    /**
     * @param id
     * @return
     * @author
     */
    List<T> getByIds(List<ID> id);


    /**
     * 根据条件查询
     *
     * @param object
     * @param sort
     * @param offset
     * @param limit
     * @return
     */
    List<T> select(T object, LinkedHashMap<String, String> sort, Integer offset, Integer limit);


    /**
     * 根据条件查询
     *
     * @param object
     * @param offset
     * @param limit
     * @return
     */
    List<T> select(T object, Integer offset, Integer limit);


    /**
     * 根据条件查询
     *
     * @param object
     * @param sort
     * @return
     */
    List<T> select(T object, LinkedHashMap<String, String> sort);

    /**
     * 根据条件查询
     *
     * @param object
     * @return
     */
    List<T> select(T object);


    /**
     * 获取总条数
     *
     * @param object
     * @return
     * @author
     */
    int selectCount(T object);

}