package com.txyd.agent.test;

/**
 * Created by Administrator on 2017/6/13.
 */
public class AgentTest {
	
	public static void main(String[] args) {
//		public static final Class<Void> TYPE = (Class<Void>) Class.getPrimitiveClass("void");
		System.out.println(Float.SIZE);
		System.out.println(0.11d);
		float f= Long.MAX_VALUE;
		System.out.println(f);
		System.out.println(f-Long.MAX_VALUE);
		System.out.println(0.1f==0.1);
		System.out.println(1.1f==1.1d);
	}

}
