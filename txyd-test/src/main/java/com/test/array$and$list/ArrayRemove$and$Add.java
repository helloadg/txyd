package com.test.array$and$list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayRemove$and$Add {

	public static void main(String[] args) {
		List<String> array = new ArrayList<String>();
		array.add("1");
		array.add("3");
		array.add("4");
		array.add("5");
		array.add("2");
		array.add("2");
		array.add("2");
		array.add("2");
		array.add("2");


		{

		}
		

		{//foreach 方法有bug		
			/**
			 * 如果被删除的元素不是倒数第二个元素，则会抛出java.util.ConcurrentModificationException异常
			 */
			{
//				for (String str : array) {
//					System.out.println(str);
//					if(array.contains(str)){
//						if (str.equals("2")) {
//							array.remove(str);//
//						}
//					}
//				}
			}
			
			{
//				for (Iterator<String> it = array.iterator(); it.hasNext();) {
//					String str = it.next();
//					if (str.equals("2")) {
////					array.remove(str);
//					}
//				}
			}

		}
		{//无法达到目的

			{
//				for (int i = 0; i < array.size(); i++) {
//					if (array.get(i).equals("2")) {
//						array.remove(i);
//					}
//				}
			}
			{//报错
//				int count=array.size();
//				for (int i = 0; i < count; i++) {
//					if (array.get(i).equals("2")) {
//						array.remove(i);
//					}
//				}
			}
		}
		{//可以达到目的

			{
//				for (int i = 0; i < array.size(); i++) {
//					if (array.get(i).equals("2")) {
//						array.remove(i--);
//					}
//				}
			}
			{
				for (Iterator<String> it = array.iterator(); it.hasNext();) {
					String str = it.next();
					if (str.equals("2")) {
						it.remove();
					}
				}
			}
			

		}

		for (String string : array) {
			System.out.println(string);
		}
	}

}
