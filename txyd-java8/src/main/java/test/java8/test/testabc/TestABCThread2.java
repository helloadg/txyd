package test.java8.test.testabc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestABCThread2 {
	 private Object lock = new Object();  
	    private int count;  
	  
	    public static void main(String[] args) {  
	        ExecutorService executorService = Executors.newFixedThreadPool(3);  
	        TestABCThread2 abc = new TestABCThread2();  
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
	            synchronized (lock) {  
	                while (true) {  
	                    if (count % 3 == _threadNum - 1) {  
	                        System.out.println("Thread-Name:" + _name);  
	                        count++;  
	                        lock.notifyAll();  
	                        break;  
	                    } else {  
	                        try {  
	                            lock.wait();  
	                        } catch (InterruptedException e) {  
	                            e.printStackTrace();  
	                        }  
	                    }  
	                }  
	            }  
	        }  
	    }  
}
