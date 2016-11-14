package com.mc.db.manager.impl.base;


import com.mc.db.manager.base.BaseKeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.mc.db.dao.base.BaseKeyDao;
import com.mc.db.manager.base.BaseManager;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWithKeyManagerImpl<T,ID> extends BaseManagerImpl<T>  implements BaseManager<T>, BaseKeyManager<T,ID> {

	
	@Autowired
	private BaseKeyDao<T, ID> baseKeyDao;


	@Override
	public int updateById(T t, ID id) {
		return this.baseKeyDao.updateById(t,id);
	}

    @Override
    public int updateByIds(T t, List<ID> ids) {
		List<List<ID>> list = splite(ids);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.baseKeyDao.updateByIds(t,list.get(i));
				}
			}
		}
		return count;
    }

	@Override
	public int deleteById(ID id) {
		return this.baseKeyDao.deleteById(id);
	}
	
	@Override
	public int deleteByIds(List<ID> ids) {
		List<List<ID>> list = splite(ids);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.baseKeyDao.deleteByIds(list.get(i));
				}
			}
		}
		return count;
	}

	@Override
	public T getById(ID  id) {
		return this.baseKeyDao.getById(id);
	}
	
	@Override
	public List<T> getByIds(List<ID>  ids) {
		List<T> result = new ArrayList<>();
		
		List<List<ID>> list = splite(ids);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					result.addAll(this.baseKeyDao.getByIds(list.get(i))) ;
				}
			}
		}
		
		return result;
	}
	
}
