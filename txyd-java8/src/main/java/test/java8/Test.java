package test.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Test {

	public static void main(String[] args) {
		Map<String,Integer> map1=null;
		Object object=null;
		System.out.println("object == map1 :"+(object == map1));
		System.out.println("null == null :"+(null == null));
		int count = 100;
		List<Integer> list = new ArrayList<Integer>() {{
			for (int i = 0; i < count; i++) {
				add((int) (i * Math.random()));
				add(i);
			}
		}};
		List<Person> persons =new ArrayList<Person>(){{
			for (int i = 0; i < count; i++) {
				Person person=new Person();
				person.setId(i);
				person.setName("name"+i);
				add(person);
			}
		}};
		Scanner scanner= new Scanner(System.in);
		String inStr=scanner.nextLine();
		while (!"stop".equals(inStr)){
			{
				{
//					Stream.of("one", "two", "three", "four")
//							.filter(e -> e.length() > 0)
//							.peek(e -> System.out.println("Filtered value: " + e))
//							.map(String::toUpperCase)
//							.peek(e -> System.out.println("Mapped value: " + e))
//							.collect(Collectors.toList());
				}
				{
					Stream<Integer> stream=Stream.<Integer>iterate(0, e->++e).limit(count);
					Map<Boolean, List<Integer>> map = stream.collect(Collectors.partitioningBy(e->e<=10));
					System.out.println(map.get(true));
					System.out.println(map.get(false));
					System.out.println(map.get(true).stream().reduce(Integer::sum).get());
					System.out.println(map.get(false).stream().reduce(Integer::sum).get());
					
				}
				{
//					String str=Stream.of("a","b","c","d").reduce(",",(x,y)->x+y);
//					System.out.println(str);
//					int sum=Stream.of(1,2,3,4,5).reduce((x,y)->x+y).get();
//					System.out.println(sum);
////					double min=Stream.of(1.0,2.0,3.0,4.0,5.22).reduce((x,y)->x.compareTo(y.doubleValue())>0?y:x).get();
//					double min=Stream.of(1.0,2.0,3.0,4.0,5.22).reduce(Double::min).get();
//					System.out.println(min);
//					{
//						Stream<Long> generate = Stream.generate(new Supplier<Long>() {
//							long value = 0;
//							@Override
//							public Long get() {
//								return ++this.value;
//							}
//						});
//						generate.limit(10).forEach(System.out::println);
//					}
//					{
//						Stream<Long> generate = Stream.generate(()->{return 1L;});
//						generate.limit(10).forEach(System.out::println);
//					}
//
//					{
//						persons.stream().sorted((x,y)-> y.getId().compareTo(x.getId())).collect(Collectors.toCollection(ArrayList::new)).forEach(
//								x-> System.out.println(x.getId()+":"+x.getName())
//						);
//
//					}
				}
				{
////				list.stream().distinct().forEach(System.out::println);
////				list.stream().collect(Collectors.toSet()).forEach(System.out::println);
//					String sumStr=list.stream().distinct().map(e->String.valueOf(e)).collect(Collectors.joining()).toString();
//					System.out.println(sumStr);
//					List<String> listStr=Stream.of("one", "two", "three", "four")
//							.filter(e -> e.length() > 2)
//							.peek(e -> System.out.print(" " + e))
//							.map(String::toUpperCase)
//							.peek(e -> System.out.print(" " + e))
//							.collect(Collectors.toList());
//					System.out.println(listStr.size());
//
////				list.stream().filter(e-> e != null);
				}

			}


			System.out.println("****************************************************");
			inStr=scanner.nextLine();
		}
	}
}
class Person {
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
