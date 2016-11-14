package com.mc.db.manager.impl.base;


import org.springframework.beans.factory.annotation.Autowired;
import com.mc.db.dao.base.BaseDao;
import com.mc.db.manager.base.BaseManager;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class BaseManagerImpl<T> extends BaseImpl implements BaseManager<T> {
	
	@Autowired
	private BaseDao<T> baseDao;

	@Override
	public int insert(T object) {
		return this.baseDao.insert(object);
	}
	
	@Override
	public int insertNotNull(T object) {
		return this.baseDao.insertNotNull(object);
	}
	
	@Override	
	public int insertBatch(List<T> tList) {
		List<List<T>> list = splite(tList);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.baseDao.insertBatch(list.get(i));
				}
			}
		}
		return count;
	}

    @Override
    public int update(T update, T where) {
    	return this.baseDao.update(update,where);
    }

	@Override
	public int delete(T object) {
		return this.baseDao.delete(object);
	}
	
	@Override
	public List<T> select(T object, LinkedHashMap<String,String> sort, Integer offset,  Integer limit) {
		return this.baseDao.select(object,sort,  offset,  limit);
	}

	@Override
	public List<T> select(T object, Integer offset, Integer limit) {
		return this.baseDao.select(object,null,  offset,  limit);
	}
	
	@Override
	public List<T> select(T object, LinkedHashMap<String,String> sort) {
		return this.baseDao.select(object,sort,  null,  null);
	}
	
	@Override
	public List<T> select(T object) {
		return this.baseDao.select(object,null,  null,  null);
	}
	
	@Override
	public int selectCount(T object) {
		return this.baseDao.selectCount(object);
	}

}
