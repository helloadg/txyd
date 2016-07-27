package com.txyd.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockThread {
	public static void main(String[] args) {

		final Outputter output = new Outputter();
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

}

class Outputter {
	private Lock lock = new ReentrantLock();// 锁对象

	public void output() throws InterruptedException {
		lock.lock();// 得到锁
		try {
			for (int i = 0; i < 10; i++) {
				System.out.print(i);
				Thread.sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();// 释放锁
		}

	}

}