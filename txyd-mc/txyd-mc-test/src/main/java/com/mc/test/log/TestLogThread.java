package com.mc.test.log;


import com.mc.db.dao.LogDao;
import com.mc.test.BaseTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/11/4.
 *
 * @RunWith(SpringJUnit4ClassRunner.class)
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class TestLogThread extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestLogThread.class);
	
	@Autowired
	private LogDao logDao;
	
	@Test
	public void testThreadUpdate() throws Exception {
//		int count=logDao.updateTest(null);
//		System.out.println(count);
		
		final int threadNum =1000;
		
		CountDownLatch begin = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(threadNum);
		final ExecutorService exec = Executors.newFixedThreadPool(threadNum);
		for (int i = 0; i < threadNum; i++) {
			final int num =i+1;
			Runnable run = ()->{
				try{
					begin.await();
					this.logDao.updateTest(null);
//					System.out.println(Thread.currentThread().getName()+"执行完毕");
				}catch (Throwable t){
					
				}finally {
					end.countDown();
				}
			};
			exec.submit(run);
			
		}
		System.out.println("start");
		begin.countDown();
		end.await();
		System.out.println("Game Over");
		exec.shutdown();
	}
	
}
