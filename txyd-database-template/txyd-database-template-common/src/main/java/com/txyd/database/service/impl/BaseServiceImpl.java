package com.txyd.database.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import com.txyd.database.dao.BaseDao;
import com.txyd.database.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    @Autowired
    private BaseDao<T, ID> baseDao;

    @Override
    public int insert(T object) {
        return this.baseDao.insert(object);
    }

    @Override
    public int insertNotNull(T object) {
        return this.baseDao.insertNotNull(object);
    }

    @Override
    public int insertBatch(List<T> list) {
        return this.baseDao.insertBatch(list);
    }


    @Override
    public int updateById(T t, ID id) {
        return this.baseDao.updateById(t, id);
    }

    @Override
    public int delete(T object) {
        return this.baseDao.delete(object);
    }

    @Override
    public int deleteById(ID id) {
        return this.baseDao.deleteById(id);
    }


    @Override
    public int deleteByIds(List<ID> list) {
        return this.baseDao.deleteByIds(list);
    }

    @Override
    public List<T> select(T object, LinkedHashMap<String, String> sort, Integer offset, Integer limit) {
        return this.baseDao.select(object, sort, offset, limit);
    }

    @Override
    public List<T> select(T object, Integer offset, Integer limit) {
        return this.baseDao.select(object, null, offset, limit);
    }

    @Override
    public List<T> select(T object, LinkedHashMap<String, String> sort) {
        return this.baseDao.select(object, sort, null, null);
    }

    @Override
    public List<T> select(T object) {
        return this.baseDao.select(object, null, null, null);
    }

    @Override
    public int selectCount(T object) {
        return this.baseDao.selectCount(object);
    }

    @Override
    public T getById(ID id) {
        return this.baseDao.getById(id);
    }

    @Override
    public List<T> getByIds(List<ID> ids) {
        return this.baseDao.getByIds(ids);
    }

}
