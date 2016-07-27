package com.txyd.thread;

public class MySynchronizedThread {
	public static void main(String[] args) {

		final Outputter output = new MySynchronizedThread().new Outputter();
		new Thread() {
			public void run() {
				try {
					output.output();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}.start();
		new Thread() {
			public void run() {
				try {
					output.output();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	class Outputter {
		public void output() throws InterruptedException {
			{// 非线程安全
				// for (int i = 0; i < 10; i++) {
				// System.out.print(i);
				// Thread.sleep(10);
				// }
			}
			{// 使用synchronized将需要互斥的代码包含起来，并上一把锁:这把锁必须是需要互斥的多个线程间的共享对象

				{// 非线程安全
					// Object lock = new Object();
					// synchronized (lock) {
					// for (int i = 0; i < 10; i++) {
					// System.out.print(i);
					// Thread.sleep(10);
					// }
					// }
				}
				{// 线程安全
					synchronized (this) {
						for (int i = 0; i < 10; i++) {
							System.out.print(i);
							Thread.sleep(10);
						}
					}
				}

			}
		}

		public synchronized void outputSafe() throws InterruptedException {
			for (int i = 0; i < 10; i++) {
				System.out.print(i);
				Thread.sleep(10);
			}
		}
	}
}
//
// class Outputter {
// public void output() throws InterruptedException {
// {//非线程安全
//// for (int i = 0; i < 10; i++) {
//// System.out.print(i);
//// Thread.sleep(10);
//// }
// }
// {//使用synchronized将需要互斥的代码包含起来，并上一把锁:这把锁必须是需要互斥的多个线程间的共享对象
//
// {//非线程安全
//// Object lock = new Object();
//// synchronized (lock) {
//// for (int i = 0; i < 10; i++) {
//// System.out.print(i);
//// Thread.sleep(10);
//// }
//// }
// }
// {//线程安全
// synchronized (this) {
// for (int i = 0; i < 10; i++) {
// System.out.print(i);
// Thread.sleep(10);
// }
// }
// }
//
// }
// }
// public synchronized void outputSafe() throws InterruptedException {
// for (int i = 0; i < 10; i++) {
// System.out.print(i);
// Thread.sleep(10);
// }
// }
// }