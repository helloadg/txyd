package com.test.single;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/8.
 */

/**
 * serialVersionUID 生成规则：只有静态属性以及构造方法变化时不改变其值，其余皆改变
 * 当使用writeObject 与 readObject时：请注意写入属性的顺序和读取属性的顺序应该一致
 * 反序列化时：通过二进制流直接生成对象，并不调用构造方法；
 * 生成类的方式：new、Class.forName("xxx")、反射、反序列化；
 *      其中new、Class.forName("xxx")、反射都是调用构造器，而反序列化不是；
 *      反序列化：不调用构造器，自身相当于构造器的另一种写法；
 */

/**
 * java类初始化顺序：
 * 父类--静态变量、静态初始化块：按照定义的顺序初始化
 * 子类--静态变量、静态初始化块：按照定义的顺序初始化
 * 子类main方法
 * 父类--变量、初始化块：按照定义的顺序初始化
 * 父类--构造器
 * 子类--变量、初始化块：按照定义的顺序初始化
 * 子类--构造器
 */
public class SerSingleton implements Serializable {
//	private static final long serialVersionUID = -1L;
//	private static final long serialVersionUID = -2354310520781001049L;
//	private static final long serialVersionUID = -2354310520781001049L;
//	private static final long serialVersionUID = 3856435468875729402L;
//	private static final long serialVersionUID = -2354310520781001049L;
//	private static final long serialVersionUID = 3856435468875729402L;
//	private static final long serialVersionUID = 8086079289027587075L;
//	private static final long serialVersionUID = 3856435468875729402L;
//	private static final long serialVersionUID = -4951987105271831320L;
//	private static final long serialVersionUID = -4951987105271831320L;
//	private static final long serialVersionUID = 6678085114096070897L;
//	private static final long serialVersionUID = -4951987105271831320L;
//	private static final long serialVersionUID = -4951987105271831320L;
//	private static final long serialVersionUID = -4951987105271831320L;
//	private static final long serialVersionUID = -4951987105271831320L;
//	private static final long serialVersionUID = -4951987105271831320L;
	
	
	
	
	
	private static final SerSingleton instance=new SerSingleton();
	private static final long serialVersionUID = -4951987105271831320L;

//	public static SerSingleton getInstance1(){
//		return  instance1;
//	}
	
	private String name;
	private Integer sex;
	private double score;
	private int id;
	private transient double salary;
	private transient double salary2;
	
	public SerSingleton(){
		System.out.println("SerSingleton()");
		name="name"+Math.random();
		sex=(int)(Math.random()*100);
		score=Math.random()*100;
		salary=Math.random()*100;
		salary2=Math.random()*100;
	}
	
	public SerSingleton(String name, Integer sex, double score, int id, double salary, double salary2) {
		this.name = name;
		this.sex = sex;
		this.score = score;
		this.id = id;
		this.salary = salary;
		this.salary2 = salary2;
	}
	
//	public static SerSingleton getInstance(){
//		return instance;
//	}
//	public static void createString(){
//		System.out.println("createString()");
//	}
	
	//系统总是先调用writeReplace()方法
	private Object writeReplace()throws ObjectStreamException
	{
		//如果没有这几行赋值语句，那么反序列化后得到的仅仅是一个初始化的对象；
		instance.setName(name);
		instance.setSex(sex);
		instance.setScore(score);
		instance.setId(id);
		instance.setSalary2(salary2);
		instance.setSalary(salary);
		System.out.println("writeReplace()");
		return instance;
	}
	/**
	 * 此方法可以阻止生成新的实例
	 * 缺点：为保证序列化之后的对象和序列化之前的对象所有值都一致，必须在方法中将所有的字段值重新赋一遍
	 * @return
	 */
	private Object readResolve(){
		//如果没有这几行赋值语句，那么反序列化后得到的仅仅是一个初始化的对象；
		instance.setName(name);
		instance.setSex(sex);
		instance.setScore(score);
		instance.setId(id);
		instance.setSalary(salary);
		instance.setSalary2(salary2);
		System.out.println("readResolve()");
		return instance;
	}
//
	private void writeObject(ObjectOutputStream out) throws  Exception {
		out.defaultWriteObject();
		out.writeObject(salary2);
		out.writeObject(salary);
		System.out.println("writeObject()");
	}

	private void readObject(ObjectInputStream in) throws  Exception {
		in.defaultReadObject();
		salary=(double)in.readObject();
		salary2=(double)in.readObject();
		System.out.println("readObject()");

	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSex() {
		return sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "SerSingleton{" +
				"name='" + name + '\'' +
				", sex=" + sex +
				", score=" + score +
				", id=" + id +
				", salary=" + salary +
				", salary2=" + salary2 +
				'}';
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public double getSalary2() {
		return salary2;
	}
	
	public void setSalary2(double salary2) {
		this.salary2 = salary2;
	}
}
