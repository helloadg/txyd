package test.java8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TestAtomic {
	public static Object lock =new Object();
	
	public static AtomicInteger atomic_integer_static = new AtomicInteger();
	
	public static AtomicLong atomic_long_static=new AtomicLong();

	public static volatile Integer volatile_integer_static = 0;
	public static volatile int volatile_int_static = 0;

	public static Integer integer_static = 0;
	public static int _int_static = 0;

	public static int endThread = 0;
	
	private  int  _int=0;	

	public int get_int() {
		return _int;
	}
	public void set_int(int _int) {
		this._int = _int;
	}



	public static void main(String[] args) throws InterruptedException {
		TestAtomic testAtomic=new TestAtomic();
		String str=new String();
		for (int i = 0; i < 100; i++) {
//			new Thread(new ThreadTest()).start();
//			new Thread(new ThreadTestSynchronized()).start();
			new Thread(()->{
				for(int j=0;j<10000;j++){
					TestAtomic.atomic_integer_static.incrementAndGet();
					TestAtomic.atomic_long_static.incrementAndGet();
				}
				++TestAtomic.endThread; // 貌似很无敌！难道是原子性的吗？
			}).start();
//			new Thread(()->{
//				synchronized(str){
//					for (int j = 0; j < 100; j++) {
//						TestAtomic.atomic_integer_static.incrementAndGet();
//						TestAtomic.volatile_integer_static++;
//						TestAtomic.volatile_int_static++;
//						TestAtomic.integer_static++;
//						TestAtomic._int_static++;
////					testAtomic.set_int(testAtomic.get_int()+1);
//					}
//					++TestAtomic.endThread; // 貌似很无敌！难道是原子性的吗？
//				}
//				
//			}).start();
		}

		while (true) {
			Thread.sleep(100);
			if (TestAtomic.endThread == 100) {
				System.out.println("Execute End:");
				System.out.println("atomic_integer_static: " + TestAtomic.atomic_integer_static);
				System.out.println("atomic_long_static: " + TestAtomic.atomic_long_static);
				System.out.println("volatile_integer_static: " + TestAtomic.volatile_integer_static);
				System.out.println("volatile_int_static: " + TestAtomic.volatile_int_static);
				System.out.println("integer_static: " + TestAtomic.integer_static);
				System.out.println("_int_static: " + TestAtomic._int_static);
				
				System.out.println("_int:"+testAtomic.get_int());
				System.out.println("ThreadTestSynchronized._int:"+ThreadTestSynchronized._int_static);
				break;
			}
		}

	}

}

class ThreadTestSynchronized implements Runnable {
	private TestAtomic testAtomic=new TestAtomic();
	
	public static int _int_static =0;


	public  void run() {
		synchronized(TestAtomic.lock){
			for (int i = 0; i < 1000; i++) {
				testAtomic.set_int(testAtomic.get_int()+1);
				TestAtomic.atomic_integer_static.incrementAndGet();
				TestAtomic.volatile_integer_static++;
				TestAtomic.integer_static++;
				TestAtomic._int_static++;
				ThreadTestSynchronized._int_static++;
			}
			++TestAtomic.endThread; // 貌似很无敌！难道是原子性的吗？			
		}
	}
}
class ThreadTest implements Runnable {
	private TestAtomic testAtomic=new TestAtomic();
	
	public static int _int_static =0;

	
	public void run() {
		for (int i = 0; i < 1000; i++) {
			testAtomic.set_int(testAtomic.get_int()+1);
			TestAtomic.atomic_integer_static.incrementAndGet();
			TestAtomic.volatile_integer_static++;
			TestAtomic.volatile_int_static++;
			TestAtomic.integer_static++;
			TestAtomic._int_static++;
			ThreadTestSynchronized._int_static++;
		}
		++TestAtomic.endThread; // 貌似很无敌！难道是原子性的吗？

		
	}
}