package com.test.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2016/12/26.
 */
public class TestMain {
	public static void SerializableTest(){
		Student s=new Student();
		s.setName("123");
		s.setScore(100);
		ObjectOutputStream oos= null;
		try {
			oos = new ObjectOutputStream(
					new FileOutputStream("d:/test/saveJavaObject.txt"));
			oos.writeObject(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
	public static void UntiSerializableTest(){
		try {
			ObjectInputStream oos= null;
			oos = new ObjectInputStream(
					new FileInputStream("d:/test/saveJavaObject.txt"));
			Object object=oos.readObject();
			System.out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		HashMap s=(HashMap)oos.readObject();
//		System.out.println(s);
	}
	public static void main(String[] args) {
		{
			
		}
	}
}
