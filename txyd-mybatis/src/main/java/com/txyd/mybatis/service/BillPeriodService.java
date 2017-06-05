package com.txyd.mybatis.service;


import com.txyd.mybatis.entity.BillPeriodEntity;
import com.txyd.mybatis.service.base.BaseService;
import txyd.common.ServiceResult;

public interface BillPeriodService extends BaseService<BillPeriodEntity,Long> {
	
	public BillPeriodEntity testTruncate(long id) throws Exception;
	public ServiceResult<BillPeriodEntity> testTruncateSr(long id) throws Exception;
}
