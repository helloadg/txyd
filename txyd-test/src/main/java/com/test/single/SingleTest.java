package com.test.single;

import java.io.*;


/**
 * Created by Administrator on 2016/8/8.
 */
public class SingleTest {
	public static void main(String[] args) {
//		SerSingleton s2=SerSingleton.getInstance();
		
		try {
			SerSingleton s2=new SerSingleton();
			s2.setName("name2");
			s2.setSex(100);
			s2.setScore(88.0);
			s2.setSalary(99.0);
			s2.setSalary2(198.00);
			s2.setId(1);
			ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream("d:/test/SerSingleton.txt"));
			os.writeObject(s2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
		{
//			System.out.println(s2.getName());
//			System.out.println(s2.getSex());
		}
		
//		try {
//			Runnable runnable = ()->{
//				try {
//					ObjectInputStream os = new ObjectInputStream(new FileInputStream("d:/test/SerSingleton.txt"));
//					SerSingleton s1=(SerSingleton)os.readObject();
//					System.out.println(s1.getName());
//					System.out.println(s1.getSex());
//					System.out.println(s1.getScore());
//					System.out.println(s1.getSalary());
//					System.out.println(s1.getSalary2());
//					System.out.println(s1.getId());
////					if(s1==s2){
////						System.out.println("s1 == s2");
////					}else{
////						System.out.println("s1 != s2");
////					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			};
//			for (int i = 0; i <10 ; i++) {
//				Thread th   = new Thread(runnable);
//				th.start();
//				System.out.println(th.getName());
//
//			}
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
	}
}
	