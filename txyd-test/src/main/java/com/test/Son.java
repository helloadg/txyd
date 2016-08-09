package com.test;

/**
 * Created by Administrator on 2016/8/9.
 */
public class Son extends Father {
	public static void printStatic() {
		System.out.println("Static子类");
	}
	
	@Override
	public void print() {
		System.out.println("子类");
	}
	
	public static void main(String[] args) {
		Father father =new Son();
		father.printStatic();
		father.print();
	}
}
