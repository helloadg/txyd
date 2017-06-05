package com.txyd.mybatis.service.impl;

import com.txyd.mybatis.dao.BillPeriodDao;
import com.txyd.mybatis.entity.BillPeriodEntity;
import com.txyd.mybatis.service.BillPeriodService;
import com.txyd.mybatis.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import txyd.common.ServiceResult;
import txyd.util.ServiceResultUtil;
import txyd.util.TimeUtil;


@Service
public class BillPeriodServiceImpl extends BaseServiceImpl<BillPeriodEntity, Long> implements BillPeriodService {
	
	//	@Resource(type = BillPeriodDao.class)
	@Autowired
	private BillPeriodDao billPeriodDao;
	
	private void update(long id) throws Exception {
		int now = TimeUtil.getSecs();
		BillPeriodEntity update = new BillPeriodEntity();
		update.setCt(now);
		update.setStatus(0);
		int count = this.billPeriodDao.updateById(update, id);
		if (true) {
//			throw new RuntimeException("测试使用");
		}
	}
	
	private void update2(long id) throws Exception {
		int now = TimeUtil.getSecs();
		BillPeriodEntity update = new BillPeriodEntity();
		update.setCt(now);
		update.setStatus(2);
		int count = this.billPeriodDao.updateById(update, id);
		if (true) {
			throw new RuntimeException("测试使用");
		}
	}
	
	private BillPeriodEntity get(long id) {
		BillPeriodEntity result = this.billPeriodDao.getById(id, null);
		return result;
	}
	
//	@Transactional(rollbackFor = Exception.class)
	@Transactional
	public BillPeriodEntity testTruncate(long id) throws Exception {
		this.update(id);
		this.update2(id);
//		{
//
//			int now = TimeUtil.getSecs();
//			BillPeriodEntity update = new BillPeriodEntity();
//			update.setCt(now);
//			update.setStatus(0);
//			int count = this.billPeriodDao.updateById(update, id);
//			if(true){
//				throw new Exception("测试使用");
//			}
//		}
//		{
//
//			int now = TimeUtil.getSecs();
//			BillPeriodEntity update = new BillPeriodEntity();
//			update.setCt(now);
//			update.setStatus(2);
//			int count = this.billPeriodDao.updateById(update, id);
//			if(true){
//				throw new Exception("测试使用");
//			}
//		}
		if (true) {
			throw new RuntimeException("测试使用");
		}
		return this.get(id);
		
	}
	//	@Transactional(rollbackFor = Exception.class)
	@Transactional
	public ServiceResult<BillPeriodEntity> testTruncateSr(long id) throws Exception {
		this.update(id);
//		this.update2(id);
//		{
//
//			int now = TimeUtil.getSecs();
//			BillPeriodEntity update = new BillPeriodEntity();
//			update.setCt(now);
//			update.setStatus(0);
//			int count = this.billPeriodDao.updateById(update, id);
//			if(true){
//				throw new Exception("测试使用");
//			}
//		}
//		{
//
//			int now = TimeUtil.getSecs();
//			BillPeriodEntity update = new BillPeriodEntity();
//			update.setCt(now);
//			update.setStatus(2);
//			int count = this.billPeriodDao.updateById(update, id);
//			if(true){
//				throw new Exception("测试使用");
//			}
//		}
		if (true) {
			throw new RuntimeException("测试使用");
		}
		return ServiceResultUtil.success(this.get(id));
		
	}
}

