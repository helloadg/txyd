package test.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestStream {
	private static void displayPeople(List<People> people, Predicate<People> pred) {

	     System.out.println("Selected:");
	     people.forEach(p -> {
	         if (pred.test(p)) {
	             System.out.println(p.getName());
	         }
	     });
	}
	public static void main(String[] args){
		List<People> list=Arrays.asList(
				new People("p1",21),
				new People("p2",22),
				new People("p3",23),
				new People("p4",24));
		People[] array = {
		        new People("p1", 21),
		        new People("p2", 22),
		        new People("p3", 23),
		        new People("p4", 24)};
		{
//			Predicate<People> pred = (p) -> p.getAge() > 22;
//			list.forEach(p->{if(pred.test(p)){
//				System.out.println(p.getName());
//			}});
		}
		{
//			Predicate<People> pred = (p) -> p.getAge() > 22;
//			list.stream().filter(pred).forEach(p->{
//				System.out.println(p.getName());
//			});
		}
		{//串行流、并行流
			{//串行流
//				Predicate<People> pred = (p) -> p.getAge() > 22;
//				list.stream().filter(pred).forEach(p->{
//					System.out.println(p.getName());
//				});
			}
			{//并行流
//				Predicate<People> pred = (p) -> p.getAge() > 22;
//				list.parallelStream().filter(pred).forEach(p->{
//					System.out.println(p.getName());
//				});
			}
			{//先串后并流
//				Predicate<People> pred = (p) -> p.getAge() > 22;
//				list.stream().parallel().filter(pred).forEach(p->{
//					System.out.println(p.getName());
//				});
			}
		}
		{
//			Stream<People> stream=Stream.of(array);
//			stream.forEach(p->{System.out.println(p.getName());});
		}
		{
			{
//				Stream<People> stream=Arrays.stream(array);
////				System.out.println(stream.count());
//				stream.sorted(new Comparator<People>() {
//					public int compare(People p1,People p2){
////						return p1.getAge()-p2.getAge();
//						return p2.getAge()-p1.getAge();
//					}
//				}).forEach(p->{System.out.println(p.getName());});
			}
			{
//				Stream<People> streamParallel=Arrays.stream(array).parallel();
//				streamParallel.sorted(new Comparator<People>() {
//					@Override
//					public int compare(People p1 ,People p2){
//						return p1.getAge()-p2.getAge();
//
//					}
//				}).forEach(p->{System.out.println(p.getName());});
			}
		}
		{
			Stream<People> stream=list.stream();
			{
//				long sum=stream.mapToLong(p->p.getAge()).sum();
//				long sum=stream.map(People::getAge).reduce(0, Integer::sum);
//				System.out.println(sum);
			}
			{
//				OptionalDouble avg=stream.mapToLong(People::getAge).average();
//				OptionalDouble avg=stream.mapToLong(p->p.getAge()).average();
//				if(avg.isPresent()){
//					System.out.println(avg.getAsDouble());
//				}else{
//					System.out.println("除数为0");
//				}

			}
		}
		{
			String s="";
			for(int i=0;i<10;i++)
			{
				s+=String.valueOf(i);
			}
			Stream<String> stream=Stream.of(s);
			stream.forEach(p->{System.out.println(p);});
			Stream<String> streamParallel=Stream.of(s).parallel();
			streamParallel.forEach(p->{System.out.println(p);});
		}



	}

}

class People{
	private Integer id;
	private String name;
	private String password;
	private Integer age;
	public People(String name){
		this.name=name;

	}
	public People(String name,Integer age){
		this.name=name;
		this.age=age;

	}

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}



}
