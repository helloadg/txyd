package com.test;

/**
 * Created by Administrator on 2016/8/9.
 */
public class Son extends Father {

	private Integer id;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static void printStatic() {
		System.out.println("Static子类");
	}
	
	@Override
	public void print() {
		System.out.println("子类");
	}
	
	public static void main(String[] args) {
//		Father father =new Son();
//		father.printStatic();
//		father.print();


//		int temp=1<<30;
//		System.out.println(new BigInteger(temp+"").toString(2));
//		System.out.println(new BigInteger(Integer.MAX_VALUE+"").toString(2));
//		System.out.println(new BigInteger(3+"").toString(2));
//		System.out.println(temp);
//		System.out.println(1 << 31);
//		Set<Long> set=new HashSet<>(temp-1);
//		System.out.println("set init");
//		Stream.<Long>iterate(0L,e->e+1L).limit(temp).forEach(e->{
//			set.add(e);
//		});
//		System.out.println("end");
		
		
		{
			Integer i = 188;
			Integer j = 188;
			
			System.out.println(i == j);
			System.out.println(i != j);
		}
		{
			Long i = 99L;
			Long j = 99L;
			
			System.out.println(i == j);
			System.out.println(i != j);
		}
		{
			Boolean i = new Boolean(true);
			Boolean j = new Boolean(true);
			
			System.out.println(i == j);
			System.out.println(i != j);
			
		}
		
	}
}
