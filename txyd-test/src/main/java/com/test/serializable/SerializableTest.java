package com.test.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2016/8/9.
 */
public class SerializableTest {
	public static void main(String[] args) throws IOException
	{
		Student s=new Student();
		s.setName("123");
		s.setScore(100);
		ObjectOutputStream oos=new ObjectOutputStream(
				new FileOutputStream("d:/test/saveJavaObject.txt"));
		oos.writeObject(s);
		System.out.println("end");
	}
}
