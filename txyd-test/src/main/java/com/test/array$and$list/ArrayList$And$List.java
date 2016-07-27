package com.test.array$and$list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayList$And$List {
	
	public static void main(String[] args){
		{//ArrayList-->数组
//			List<String> list =new ArrayList<>();
//			for(int i=0;i<10;i++){
//				list.add("姓名:"+i);
//			}
//			String[] array= new String[list.size()];
//			list.toArray(array);
////			String[] array= (String[])list.toArray();//出错
//			
//			for(String str:array){
//				System.out.println(str);				
//			}
			
		}
		{//数组-->ArrayList
			String[] array=new String[10];
			for(int i=0;i<10;i++){
				array[i]="姓名:"+i;
			}
			List<String> list;
			{
				/**
				 * 用Arrays.asList()方法得到的List对象是长度固定的  
				 * 当对list进行remove、add等操作时会出UnsupportedOperationException异常
				 * 可用下述方式解决  
				 * 		new ArrayList<String>(Arrays.asList(array));  
				 */
//				list = Arrays.asList(array) ;				
				list = new ArrayList<String>(Arrays.asList(array));  
				list.remove(0);
				

			}
			for(String str:list){
				System.out.println(str);
			}
		}
	}

}
