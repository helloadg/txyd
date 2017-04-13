package com.txyd.mybatis.service.impl.base;


import com.txyd.mybatis.dao.base.BaseDao;
import com.txyd.mybatis.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<T, ID> extends BaseNoKeyServiceImpl<T> implements BaseService<T, ID> {
	
	@Autowired
	private BaseDao<T, ID> baseDao;

	@Override
	public int updateById(T t, ID id) {
		return this.baseDao.updateById(t,id);
	}

    @Override
    public int updateByIds(T t, List<ID> ids) {
		List<List<ID>> list = splite(ids);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.baseDao.updateByIds(t,list.get(i));
				}
			}
		}
		return count;
    }

	@Override
	public int deleteById(ID id) {
		return this.baseDao.deleteById(id);
	}
	
	@Override
	public int deleteByIds(List<ID> ids) {
		List<List<ID>> list = splite(ids);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.baseDao.deleteByIds(list.get(i));
				}
			}
		}
		return count;
	}

	@Override
	public T getById(ID id) {
		return this.baseDao.getById(id, null);
	}

	@Override
	public T getById(ID id, Boolean isForUpdate) {
		return this.baseDao.getById(id, isForUpdate);
	}
	
	@Override
	public List<T> getByIds(List<ID> ids) {
		List<T> result = new ArrayList<>();
		
		List<List<ID>> list = splite(ids);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					result.addAll(this.baseDao.getByIds(list.get(i), null)) ;
				}
			}
		}
		
		return result;
	}

	@Override
	public List<T> getByIds(List<ID> ids, Boolean isForUpdate) {
		List<T> result = new ArrayList<>();
		
		List<List<ID>> list = splite(ids);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					result.addAll(this.baseDao.getByIds(list.get(i), isForUpdate)) ;
				}
			}
		}
		
		return result;
	}
	
}
