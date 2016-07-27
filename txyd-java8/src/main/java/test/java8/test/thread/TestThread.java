package test.java8.test.thread;

public class TestThread {
	public static void main(String[] args){
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println(String.valueOf(Long.MAX_VALUE).length());
//		for(int i=0;i<10000;i++){
//			new Thread(()->{
//				SynClass.synClass();
//				
//			}).start();;
//			
//		}
		
		{
//			for (int i = 0; i < 10000; i++) {
//				new Thread(()->{
//					new SynClass().synObject();
//				}).start();
//			}
		}
		
		{
			SynClass synClass=new SynClass();
			for (int i = 0; i < 10000; i++) {
				new Thread(()->{
					synClass.synObject();
				}).start();
			}
		}

		
	}

}

class SynClass {
	public static int i=0;
	public synchronized static void synClass(){
//		SynClass.i++;
//		System.out.println(SynClass.i);
		StaticClass.i++;
		System.out.println(StaticClass.i);
	}
	
	public synchronized void synObject(){
		StaticClass.i++;
		System.out.println(StaticClass.i);
		
	}

}

class StaticClass{
	public static int i=0;
}