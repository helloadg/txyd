package test.spring.sync;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/12/26.
 */
@Component
@EnableAsync
public class SyncService implements  ISyncService {
	
	@Async
	@Override
	public void print(int secs) {
		System.out.println("start");
		try {
			Thread.sleep((secs * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
	@Async
	@Override
	public Future<String> get() {
		System.out.println("start");
		try {
			Thread.sleep((5 * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");
		return new AsyncResult<>("hello world !!!!");
	}
}
