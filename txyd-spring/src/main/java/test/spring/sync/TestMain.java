package test.spring.sync;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/12/26.
 */
public class TestMain {
	
	private static final ApplicationContext ac = new ClassPathXmlApplicationContext("spring-sync-schema.xml");
	
	public static void main(String[] args) {
		{
//			ISyncService iService=new SyncService();
//			iService.print(10);
		}
		{
//			System.out.println("test -start");
//			ISyncService iSyncService = ac.getBean("syncService", ISyncService.class);
//			iSyncService.print(5);
//			System.out.println("test -end");
		}
		{
			ISyncService iSyncService = ac.getBean("syncService", ISyncService.class);
			Future<String> future = iSyncService.get();
			while (true) {
				if (future.isDone()) {
					try {
						System.out.println("sync done ,result :{result}".replace("{result}", future.get() + ""));
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					break;
				}
				
				try {
					System.out.println("Continue doing something else. ");
					Thread.sleep(1000);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}
}
