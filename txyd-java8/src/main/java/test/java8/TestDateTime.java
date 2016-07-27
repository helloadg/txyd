package test.java8;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Date;

public class TestDateTime {

	public static void main(String[] args){
		{
			{
//		        LocalDateTime dateTime = LocalDateTime.parse("27::Apr::2014 21::39::48",DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));//error
//		        LocalDateTime dateTime = LocalDateTime.parse("27::十二月::2014 21::39::48",DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));//right
//				LocalDateTime dateTime = LocalDateTime.now();
//				LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
//				LocalDateTime dateTime = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30,100);
//				LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));//Asia/Kolkata
				LocalDateTime dateTime = LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("IST")));//Asia/Kolkata
//				LocalDateTime dateTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);//Getting date from the base date i.e 01/01/1970
//				LocalDate dateTime = LocalDate.ofEpochDay(365);//Getting date from the base date i.e 01/01/1970
//				LocalDate dateTime = LocalDate.ofYearDay(2014, 100);
//				System.out.println(dateTime);
//				System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
//				System.out.println(dateTime.format(DateTimeFormatter.ISO_DATE));
//				System.out.println(dateTime.format(DateTimeFormatter.ISO_TIME));
//				System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
//				System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
//				System.out.println(dateTime.format(DateTimeFormatter.ISO_WEEK_DATE));
//				System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS")));
//				System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
//				System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd ")));
//				System.out.println(dateTime.format(DateTimeFormatter.ofPattern("uuuu-MMM-dd ")));
//				System.out.println(dateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd ")));
//				System.out.println("Year " + dateTime.plusYears(1).getYear() + " is Leap Year? " + dateTime.plusYears(1).toLocalDate().isLeapYear());
//				System.out.println(dateTime.isBefore(LocalDateTime.now()));
//				System.out.println(dateTime.plusMonths(12));
//				System.out.println(dateTime.with(TemporalAdjusters.firstDayOfMonth()));
//				System.out.println(dateTime.with(TemporalAdjusters.lastDayOfMonth()));
//				System.out.println(dateTime.with(TemporalAdjusters.lastDayOfYear()));
//				System.out.println(dateTime.plusWeeks(1));
//				System.out.println(dateTime.plus(2, ChronoUnit.WEEKS));
			}
			{
//				LocalDateTime dateTime = LocalDateTime.now();
//				Period period = dateTime.toLocalDate().until(dateTime.with(TemporalAdjusters.lastDayOfYear()).toLocalDate());
//				System.out.println("Period Format= " + period);
//				System.out.println("Months remaining in the year= " + period.getMonths());
			}

			{
				Instant timestamp = Instant.now();
				System.out.println(timestamp);
				System.out.println(Date.from(timestamp));
				System.out.println(new Date().toInstant());
				Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
				System.out.println(specificTime);
			}
			{
//				Duration thirtyDay = Duration.ofDays(30);
//		        System.out.println(thirtyDay);
			}
			{
//				Clock clock =Clock.systemUTC();
//				Clock clock =Clock.systemDefaultZone();
//				System.out.println(clock);
//				System.out.println(clock.instant().getEpochSecond());
//				System.out.println(clock.instant().toEpochMilli());
//				System.out.println(System.currentTimeMillis());
			}
		}

		

	}

}
