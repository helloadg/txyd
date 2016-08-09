package com.test.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Administrator on 2016/8/9.
 */
public class UntiSerializableTest {
	public static void main(String[] args) throws IOException,ClassNotFoundException
	{
		
		ObjectInputStream oos=new ObjectInputStream(
				new FileInputStream("d:/test/saveJavaObject.txt"));

		Object object=oos.readObject();
		System.out.println(object);
//		HashMap s=(HashMap)oos.readObject();
//		System.out.println(s);
	}
}
