package test.java8.test.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    private ReentrantLock lock = new ReentrantLock();  
    
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock(): " + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}

	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
		} finally {
			if (captured)
				lock.unlock();
		}
	}
    
	public static void main(String[] args) throws InterruptedException{
		final TestReentrantLock al = new TestReentrantLock();  
		al.untimed(); // True -- 可以成功获得锁  
		al.timed(); // True --可以成功获得锁  
		
		
		//新创建一个线程获得锁并且不释放  
		new Thread() {
			{
				/**
				 * setDaemon方法把java的线程设置为守护线程，此方法的调用必须在线程启动之前执行。只有在当前jvm中所有的线程都为守护线程时，jvm才会退出。
				 * 如果创建的线程没有显示调用此方法，这默认为用户线程
				 * 定义：守护线程--也称“服务线程”，在没有用户线程可服务时会自动离开。
				 * 优先级：守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务。
				 * 设置：通过setDaemon(true)来设置线程为“守护线程”；将一个用户线程设置为
				 * 守护线程的方式是在 线程对象创建 之前 用线程对象的setDaemon方法。
				 * 
				 * 调试时可以设置为false，那么这个程序是个死循环，没有退出条件。设置为true，即可主线程结束，test线程也结束。
				 */
//				setDaemon(true);//后台进程       设置为守护线程
				setDaemon(false);//用户线程
			}

			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
        Thread.sleep(1000);// 保证新线程能够先执行  
        al.untimed(); // False -- 马上中断放弃  
        al.timed(); // False -- 等两秒超时后中断放弃  

		
	}

}

class LockClass {
	public static int i=0;
	public synchronized static void synClass(){
//		SynClass.i++;
//		System.out.println(SynClass.i);
		StaticLockClass.i++;
		System.out.println(StaticLockClass.i);
	}
	
	public synchronized void synObject(){
		StaticLockClass.i++;
		System.out.println(StaticLockClass.i);
		
	}

}

class StaticLockClass{
	public static int i=0;
}