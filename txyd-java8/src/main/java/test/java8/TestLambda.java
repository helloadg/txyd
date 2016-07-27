package test.java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestLambda {
	@Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
        String value();
    };

    @Filter( "filter1" )
    @Filter( "filter2" )
    @Filter( "filter3" )
    public interface Filterable {
    }
    public void test(Integer id,String name,String password){

    }
//    public static void filter(List<String> names, Predicate<String> condition) {
//        for(String name: names)  {
//            if(condition.test(name)) {
//                System.out.println(name + " ");
//            }
//        }
//    }
 // 更好的办法
    public static void filter(List<String> names, Predicate<String> condition) {
//    	names.stream().filter((name)->(condition.test(name))).forEach(System.out::println);
    	names.stream().filter((name)->(condition.test(name))).forEach((name)->{
    		System.out.println(name);
    	});
    }
	public static void main( String[] args ) throws Exception {
		{
//			 //Date to Instant
//	        Instant timestamp = new Date().toInstant();
//	        //Now we can convert Instant to LocalDateTime or other similar classes
//	        LocalDateTime date = LocalDateTime.ofInstant(timestamp,
//	                        ZoneId.of(ZoneId.SHORT_IDS.get("PST")));
//	        System.out.println("Date = "+date);
//
//	        //Calendar to Instant
//	        Instant time = Calendar.getInstance().toInstant();
//	        System.out.println(time);
//	        //TimeZone to ZoneId
//	        ZoneId defaultZone = TimeZone.getDefault().toZoneId();
//	        System.out.println(defaultZone);
//
//	        //ZonedDateTime from specific Calendar
//	        ZonedDateTime gregorianCalendarDateTime = new GregorianCalendar().toZonedDateTime();
//	        System.out.println(gregorianCalendarDateTime);
//
//	        //Date API to Legacy classes
//	        Date dt = Date.from(Instant.now());
//	        System.out.println(dt);
//
//	        TimeZone tz = TimeZone.getTimeZone(defaultZone);
//	        System.out.println(tz);
//
//	        GregorianCalendar gc = GregorianCalendar.from(gregorianCalendarDateTime);
//	        System.out.println(gc);
		}
		{
//			//获取数字的个数、最小值、最大值、总和以及平均值
//			List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
//			IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
//			System.out.println("Highest prime number in List : " + stats.getMax());
//			System.out.println("Lowest prime number in List : " + stats.getMin());
//			System.out.println("Sum of all prime numbers : " + stats.getSum());
//			System.out.println("Average of all prime numbers : " + stats.getAverage());
		}
		{
//			// 用所有不同的数字创建一个正方形列表
//			List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
//			List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//			System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
		}
		{
//			// 将字符串换成大写并用逗号链接起来
//			List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
//			String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
//			System.out.println(G7Countries);
		}
		{
//			// 创建一个字符串列表，每个字符串长度大于2
//			List<String> strList= Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//			List<String> filtered = strList.stream().filter(x -> x.length()> 5).collect(Collectors.toList());
//			System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
		}
		{
//			{
//				// 为每个订单加上12%的税
//				// 老方法：
//				List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//				double total = 0;
//				for (Integer cost : costBeforeTax) {
//				    double price = cost + .12*cost;
//				    total = total + price;
//				}
//				System.out.println("Total : " + total);
//			}
//			{
//				// 新方法：
//				List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//				double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum1, cost1) -> sum1 + cost1).get();
//				System.out.println("Total : " + bill);
//			}
		}
		{
//			{
////				// 不使用lambda表达式为每个订单加上12%的税
////				List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
////				for (Integer cost : costBeforeTax) {
////				    double price = cost + .12*cost;
////				    System.out.println(price);
////				}
//			}
//			{
////				// 使用lambda表达式
////				List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
////				costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
//			}
		}
		{
//			// 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
//			// 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
//			List<String> names=Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//			Predicate<String> startsWithJ = (n) -> n.startsWith("J");
//			Predicate<String> fourLetterLong = (n) -> n.length() == 4;
//			names.stream()
//			    .filter(startsWithJ.and(fourLetterLong))
//			    .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
		}
		{
//			List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//
//		    System.out.println("Languages which starts with J :");
//		    filter(languages, (str)->str.startsWith("J"));
//
//		    System.out.println("Languages which ends with a ");
//		    filter(languages, (str)->str.endsWith("a"));
//
//		    System.out.println("Print all languages :");
//		    filter(languages, (str)->true);
//
//		    System.out.println("Print no language : ");
//		    filter(languages, (str)->false);
//
//		    System.out.println("Print language whose length greater than 4:");
//		    filter(languages, (str)->str.length() > 4);
		}
		{
//			{
////				// Java 8之前：
////				List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
////				for (String feature : features) {
////				    System.out.println(feature);
////				}
//			}
//			{
////				// Java 8之后：
////				List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
////				features.forEach(n -> System.out.println(n));
////
////				// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
////				// 看起来像C++的作用域解析运算符
////				features.forEach(System.out::println);
//			}
		}
		{
//			// Java 8之前：
//			new Thread(new Runnable() {
//			    @Override
//			    public void run() {
//			    System.out.println("Before Java8, too much code for too little to do");
//			    }
//			}).start();
//			//Java 8方式：
//			new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
		}
		{
//			Method method = Test.class.getMethod( "test", Integer.class,String.class,String.class );
//	        for( Parameter parameter: method.getParameters() ) {
//	            System.out.println( "Parameter: " + parameter.getName() );
//	        }
		}
		{
//			for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
//	            System.out.println( filter.value() );
//	        }
//			Filters Filters=Filterable.class.getAnnotation(Filters.class);
//            System.out.println( Filters.value() );
		}

		{
//		    Defaulable defaulable = DefaulableFactory.create( DefaultableImpl::new );
//		    System.out.println( defaulable.notRequired() );
//
//		    defaulable = DefaulableFactory.create( OverridableImpl::new );
//		    System.out.println( defaulable.notRequired() );
		}

		{
//		    Car car = Car.create( Car::new );
//		    List< Car > cars = Arrays.asList( car );
//		    cars.forEach( Car::collide );
//		    cars.forEach( Car::repair );
//		    Car police = Car.create( Car::new );
//		    cars.forEach( police::follow );
		}

	}
}

class Car {
	public Car( ){

	}
	public static Car create(final Supplier<Car> supplier) {
		return supplier.get();
	}

	public static void collide( Car car) {
		System.out.println("Collided " + car.toString());
	}

	public void follow( Car another) {
		System.out.println("Following the " + another.toString());
	}

	public void repair() {
		System.out.println("Repaired " + this.toString());
	}
}