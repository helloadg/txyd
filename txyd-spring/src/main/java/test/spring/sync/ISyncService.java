package test.spring.sync;

import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/12/26.
 */
public interface ISyncService {
	void print(int secs);
	Future<String> get();
}
