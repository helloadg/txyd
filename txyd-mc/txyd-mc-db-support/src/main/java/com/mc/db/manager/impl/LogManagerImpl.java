package com.mc.db.manager.impl;

import com.mc.common.utils.TimeUtil;
import com.mc.db.Constants;
import com.mc.db.entity.LogEntity;
import com.mc.db.manager.LogManager;
import com.mc.db.manager.impl.base.BaseWithKeyManagerImpl;
import org.springframework.stereotype.Service;

@Service
public class LogManagerImpl extends BaseWithKeyManagerImpl<LogEntity,Long> implements LogManager {
	public LogEntity createLog(Long planId,Integer type,String content){
		LogEntity logEntity = new LogEntity();
		logEntity.setFplanId(planId);
		logEntity.setType(type);
		logEntity.setCt(TimeUtil.getSecs());
		logEntity.setUt(TimeUtil.getSecs());
		logEntity.setContent(content);
		logEntity.setIsDeleted(Constants.IS_DELETED_NO);
		if(insert(logEntity)>0){
			return logEntity;
		}else{
			return null;
		}
	}
}
