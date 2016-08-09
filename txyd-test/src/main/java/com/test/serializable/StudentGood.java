package com.test.serializable;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/9.
 */
public class StudentGood  /*extends Student*/  implements Serializable{
	private static final long serialVersionUID = 1L;
	private  String name;
	private int score;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	//自定义序列化需要提供如下特殊签名的方法
	private void writeObject(java.io.ObjectOutputStream out)throws IOException
	{
		{
//			out.writeObject((new StringBuffer(name)).reverse());//相当于加了一下密
//			System.out.println("writeObject");
		}
		{
			out.defaultWriteObject();
			System.out.println(" StudentGood writeObject()");
		}
		
	}
	
	//系统总是先调用writeReplace()方法
	private Object writeReplace()throws ObjectStreamException
	{
		{
//			HashMap hm=new HashMap();
//			hm.put(name,score);//那就不能重名了，否则就值将被替换！
//			System.out.println("writeReplace");
//			return hm;
		}
		{
			System.out.println("StudentGood writeReplace()");
			this.setName("txgl");
			this.setScore(100);
			
			Map<String,Integer> hm=new HashMap<>();
			hm.put(this.getName(),this.getScore());
			return new StudentGood();
		}
	}
	
	private void readObject(java.io.ObjectInputStream in)throws IOException,ClassNotFoundException
	{
		{
//			/*this.name=((StringBuffer)in.readObject()).toString();
//        System.out.println("readObject");*/
//			HashMap hm=((HashMap)in.readObject());
//			hm.put("123",0);
//			System.out.println("777777777");
		}
		{
			System.out.println("StudentGood readObject()");
			in.defaultReadObject();
		}
		
	}
	//此方法在readObject之后调用。
    private Object readResolve() throws ObjectStreamException
    {
	    {
//		    ArrayList hm=new ArrayList();
//		    hm.add(name);
//		    hm.add(score);//那就不能重名了，否则就值将被替换！
//		    System.out.println("readResolve");
//		    return hm;
	    }
	    {
//		    System.out.println("readObject");
//		    HashMap hm=new HashMap();
//		    hm.put("name",0);
//		    return hm;
	    }
	    {
		
		    System.out.println("StudentGood readResolve()");
		    return this;
	    }
	    
    }
	
	@Override
	public String toString() {
		return "StudentGood{" +
				"name='" + name + '\'' +
				", score=" + score +
				'}';
	}
}
