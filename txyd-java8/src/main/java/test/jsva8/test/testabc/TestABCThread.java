package test.jsva8.test.testabc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestABCThread {
	private Lock lock = new ReentrantLock();  
    private Condition condition = lock.newCondition();  
    private int count;  
  
    public static void main(String[] args) {  
        ExecutorService executorService = Executors.newFixedThreadPool(3);  
        TestABCThread abc = new TestABCThread();  
        for (int i = 0; i < 10; i++) {  
            executorService.execute(abc.new Run("AAAAAAAAAAAAAAAA", 1));  
            executorService.execute(abc.new Run("BBBBBBBBBBBBBBBBB", 2));  
            executorService.execute(abc.new Run("CCCCCCCCCCCCCCcCC", 3));  
        }  
        executorService.shutdown();  
    }  
  
    class Run implements Runnable {  
        private String _name = "";  
        private int _threadNum;  
  
        public Run(String name, int threadNum) {  
            _name = name;  
            _threadNum = threadNum;  
        }  
  
        @Override  
        public void run() {  
            lock.lock();  
            try {  
                while (true) {  
                    if (count % 3 == _threadNum - 1) {  
                        System.out.println("Thread-Name:" + _name);  
                        count++;  
                        condition.signalAll();  
                        break;  
                    } else {  
                        try {  
                            condition.await();  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            } finally {  
                lock.unlock();  
            }  
        }  
    }  
}
