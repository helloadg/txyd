package com.txyd.thread;

public class MyVolatileThread {
	private static final Outputter Outputter = null;
	

	public static void main(String[] args) {

		final Outputter output = MyVolatileThread.Outputter;
		int sum=1000000;
		new Thread() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					for(int i=0;i<sum;i++){
						output.one();
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					for(int i=0;i<sum;i++){
						output.two();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	static class Outputter {
		private static int i = 0;
		private static int j = 0;
		
		private static volatile int count = 0;

		public static void one() throws InterruptedException {
			i++;
			j++;
			Thread.sleep(1);
		}

		public static void two() throws InterruptedException {
//			System.out.println((count++)+":"+"i=" + i + " j=" + j);
			if(i!=j){
				System.out.println("not  same ！");		
				Thread.interrupted();
			}
			Thread.sleep(1);
		}
	}
}
