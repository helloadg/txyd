package com.mc.db.service.impl;


import com.mc.ServiceResult;
import com.mc.common.utils.ServiceResultUtil;
import com.mc.db.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.mc.db.manager.base.BaseKeyManager;
import com.mc.db.manager.base.BaseManager;

import java.util.LinkedHashMap;
import java.util.List;

public class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

	@Autowired
	private BaseManager<T> baseManager;
	
	@Autowired
	private BaseKeyManager<T, ID> baseKeyManager;

	@Override
	public ServiceResult<T> insert(T object) {
		this.baseManager.insert(object);
		return ServiceResultUtil.success(object);
	}

	@Override
	public ServiceResult<T> insertNotNull(T object) {
		this.baseManager.insertNotNull(object);
		return ServiceResultUtil.success(object);
	}

	@Override
	public ServiceResult<Integer> insertBatch(List<T> list) {
		int count= this.baseManager.insertBatch(list);
		return ServiceResultUtil.success(count);
	}

	@Override
	public ServiceResult<T> updateById(T t, ID id) {
		this.baseKeyManager.updateById(t, id);
		T data=this.baseKeyManager.getById(id);
		return ServiceResultUtil.success(data);
	}
/*
	@Override
	public  ServiceResult<Integer> delete(T object) {
		return ServiceResultUtil.success(this.baseManager.delete(object));
	}

	@Override
	public  ServiceResult<Integer> deleteById(ID id) {
		return ServiceResultUtil.success(this.baseKeyManager.deleteById(id));
	}

	@Override
	public  ServiceResult<Integer> deleteByIds(List<ID> list) {
		return ServiceResultUtil.success(this.baseKeyManager.deleteByIds(list));
	}
*/
	@Override
	public ServiceResult<List<T>>  select(T object, LinkedHashMap<String, String> sort, Integer offset, Integer limit) {
		return  ServiceResultUtil.success(this.baseManager.select(object, sort, offset, limit));
	}
	
	@Override
	public ServiceResult<List<T>> select(T object, Integer offset, Integer limit) {
		return ServiceResultUtil.success(this.baseManager.select(object, offset,  limit));
	}

	@Override
	public ServiceResult<List<T>> select(T object,  LinkedHashMap<String, String> sort) {
		return ServiceResultUtil.success(this.baseManager.select(object,sort));
	}
	
	@Override
	public ServiceResult<List<T>> select(T object) {
		return ServiceResultUtil.success(this.baseManager.select(object));
	}
	
	@Override
	public ServiceResult<Integer> selectCount(T object) {
		return ServiceResultUtil.success(this.baseManager.selectCount(object));
	}

	@Override
	public ServiceResult<T> getById(ID id) {
		return ServiceResultUtil.success(this.baseKeyManager.getById(id));
	}

	@Override
	public ServiceResult<List<T>> getByIds(List<ID> ids) {
		return ServiceResultUtil.success(this.baseKeyManager.getByIds(ids));
	}

}
