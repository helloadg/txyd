package com.mc.db.manager;


import com.mc.db.entity.LogEntity;
import com.mc.db.manager.base.BaseKeyManager;
import com.mc.db.manager.base.BaseManager;

public interface LogManager extends BaseManager< LogEntity>,BaseKeyManager<LogEntity,Long> {
	public LogEntity createLog(Long planId, Integer type, String content);
}