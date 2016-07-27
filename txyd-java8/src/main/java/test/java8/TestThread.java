package test.java8;

public class TestThread implements Runnable   {
	public void run() {
		{
			{
//				for (int i = 0; i < 5; i++) {
//					System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			}

		}
		synchronized (this) {
			{
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		{
//			TestThread t1 = new TestThread();
//			Thread ta = new Thread(t1, "A");
//			Thread tb = new Thread(t1, "B");
//			ta.start();
//			tb.start();
		}
		{
			TestThread t1 = new TestThread();
			TestThread t2 = new TestThread();
			Thread ta = new Thread(t1, "A");
			Thread tb = new Thread(t2, "B");
			ta.start();
			tb.start();
		}

	}
}
