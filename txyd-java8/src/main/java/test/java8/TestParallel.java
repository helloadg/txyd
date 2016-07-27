package test.java8;

import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class TestParallel {
	  public static void main( String[] args ) {
	        long[] arrayOfLong = new long [ 2000000 ];

	        Arrays.parallelSetAll( arrayOfLong,
	            index -> ThreadLocalRandom.current().nextInt( 1000000000 ) );
	        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
	            i -> System.out.print( i + " " ) );
	        System.out.println();
	        {
	        	long startTime=Instant.now().toEpochMilli();
		        Arrays.parallelSort( arrayOfLong );
//		        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
//		            i -> System.out.print( i + " " ) );
		        long endTime=Instant.now().toEpochMilli();
		        System.out.println("耗时："+(endTime-startTime));
	        }
	        {
	        	long startTime=Instant.now().toEpochMilli();
		        Arrays.sort( arrayOfLong );
//		        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
//		            i -> System.out.print( i + " " ) );
		        long endTime=Instant.now().toEpochMilli();
		        System.out.println("耗时："+(endTime-startTime));
	        }

	    }
}
