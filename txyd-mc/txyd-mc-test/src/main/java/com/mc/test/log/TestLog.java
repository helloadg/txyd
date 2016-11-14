package com.mc.test.log;


import com.mc.common.utils.TimeUtil;
import com.mc.db.dao.LogDao;
import com.mc.db.entity.LogEntity;
import com.mc.test.BaseTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/11/4.
 * @RunWith(SpringJUnit4ClassRunner.class)
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class TestLog extends BaseTest {
	private static final Logger logger = Logger.getLogger( TestLog.class);
	
	@Autowired
	private LogDao logDao;
	
	@Test
	public void testThreadUpdate(){
		int count=logDao.updateTest(null);
		System.out.println(count);
	}
	
	@Test
	public void testSelectLog(){
		List<LogEntity> list =new ArrayList<>();
		int now = TimeUtil.getSecs();
		LogEntity where = new LogEntity();
		where.setFplanId(1000L);
		try{
			list=this.logDao.select(where,null,null,null);
		}catch (Throwable throwable){
			System.out.println(throwable.getMessage());
			logger.error(throwable.getMessage(),throwable);
		}
		
		System.out.println(list.size());
	}
	
	@Test
	public void testInsertBatchLog(){
		List<LogEntity> list =new ArrayList<>();
		int now = TimeUtil.getSecs();
		for(int i=0;i<1006;i++){
			LogEntity logEntity = new LogEntity();
			logEntity.setFplanId(Long.valueOf(1000));
			logEntity.setType(1);
			logEntity.setContent("{\"name\":\"test insert \"}");
			logEntity.setCt(now);
			logEntity.setUt(now);
			logEntity.setIsDeleted(0);
			list.add(logEntity);
		}
		try{
			this.logDao.insertBatch(list);
		}catch (Throwable throwable){
			System.out.println(throwable.getMessage());
			logger.error(throwable.getMessage(),throwable);
		}
		
		System.out.println(list.get(2).getId());
	}
	
	
	@Test
	public void testUpdateByIdsLog(){

		int now = TimeUtil.getSecs();
		LogEntity update = new LogEntity();
		update.setUt(now);
		update.setCt(now);
		List<Long> list =new ArrayList<>();
		list=Stream.iterate(1539L,e->++e).limit(1000L).collect(Collectors.toList());

		LogEntity where = new LogEntity();
		where.setFplanId(1000L);
		
		try{
			this.logDao.updateByIds(update,list);
		}catch (Throwable throwable){
			System.out.println(throwable.getMessage());
			logger.error(throwable.getMessage(),throwable);
		}
		
		System.out.println(list);
	}
	@Test
	public void testUpdateLog(){
		
		int now = TimeUtil.getSecs();
		LogEntity update = new LogEntity();
		update.setUt(now);
		update.setCt(now);

		LogEntity where = new LogEntity();
		where.setFplanId(1000L);
		
		try{
			this.logDao.update(update,where);
		}catch (Throwable throwable){
			System.out.println(throwable.getMessage());
			logger.error(throwable.getMessage(),throwable);
		}
		
		System.out.println(where);
	}
	
	@Test
	public void testDeleteLog(){
		int now = TimeUtil.getSecs();
		
		
		LogEntity where = new LogEntity();
		where.setFplanId(1000L);
		
		try{
			this.logDao.delete(where);
		}catch (Throwable throwable){
			System.out.println(throwable.getMessage());
			logger.error(throwable.getMessage(),throwable);
		}
		
		System.out.println(where);
	}
}
