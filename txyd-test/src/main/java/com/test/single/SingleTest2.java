package com.test.single;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


/**
 * Created by Administrator on 2016/8/8.
 */
public class SingleTest2 {
	public static void main(String[] args) {
		Runnable runnable=()->{
			SerSingleton s1=null;
			try {
				ObjectInputStream os=new ObjectInputStream(new FileInputStream("d:/test/SerSingleton.txt"));
				System.out.println(os.readObject());
//				s1=(SerSingleton)os.readObject();
//				System.out.println(s1.toString());
//				System.out.println(s1.getName());
//				System.out.println(s1.getSex());
//				System.out.println(s1.getScore());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
		for (int i = 0; i <1 ; i++) {
			Thread thread1=new Thread(runnable);
			thread1.start();
			System.out.println(thread1.getName());
		}
		System.out.println("SingleTest2  end");
		
	}
}
