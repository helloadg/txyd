/**
 * @(#)DateTime.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	版本所有，侵权必究(c) 
 * Create by:	      2014-7-7
 * @email	:
 */
package txyd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;

/**
 * TODO 时间
 * @author ：    
 * @email  ：    @126.com
 */
public class TimeUtil {
	public static final String DATE_TIME_MSEC_PATTERN ="yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_TIME_PATTERN ="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN ="yyyy-MM-dd";
	public static final String TIME_PATTERN ="HH:mm:ss";
	
	
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	private static final int MONDAY = 1;
	private static final int TUESDAY = 2;
	private static final int WEDNESDAY = 3;
	private static final int THURSDAY = 4;
	private static final int FRIDAY = 5;
	private static final int SATURDAY = 6;
	private static final int SUNDAY = 7;
	
	/**
	 * 当前时间
	 * @return
	 */
	public static Date now() {
		return new Date();
	}
	
	/**
	 * 当前时间
	 * @return
	 */
	public static Calendar calendar() {
		final Calendar cal = Calendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}
	
	
	/**
	 * TODO 将时间格式按format
	 * @param date   日期时间
	 * @param format 日期时间格式
	 * @return
	 */
	public static String toStr(final Date date, final String format)
	{
		final SimpleDateFormat customFormat = (SimpleDateFormat) TimeUtil.DATE_TIME_FORMAT.clone();
		customFormat.applyPattern(format.trim());
		return customFormat.format(date);
	}
	
	/**
	 * TODO 将时间格式按format
	 * @param timeStamp   日期时间
	 * @param format 日期时间格式
	 * @return
	 */
	public static String toStr(final int timeStamp, final String format)
	{
		Calendar cal = calendar();
		cal.setTimeInMillis((long)(timeStamp*1000.0));
		
		final SimpleDateFormat customFormat = (SimpleDateFormat) TimeUtil.DATE_TIME_FORMAT.clone();
		customFormat.applyPattern(format.trim());
		return customFormat.format(cal.getTime());
	}
	
	/**
	 * 获取时间的格式化"yyyy-MM-dd HH:mm:ss" 字符串
	 * @return
	 */
	public static String toStr( Date date){
		return toStr(date, TimeUtil.DATE_TIME_PATTERN);
	}
	
	/**
	 * 获取时间的格式化"yyyy-MM-dd HH:mm:ss" 字符串
	 * @return
	 */
	public static String toStr( final int timeStamp){
		return toStr(timeStamp, TimeUtil.DATE_TIME_PATTERN);
	}
	
	/**
	 * 获取当前时间的格式化"yyyy-MM-dd HH:mm:ss" 字符串
	 * @return
	 */
	public static String toStr(){
		return toStr(TimeUtil.now(), TimeUtil.DATE_TIME_PATTERN);
	}
	
	
	/**
	 *
	 * TODO 将字符串转化为日期
	 * @param date  如果date为null，则date默认为当前时间
	 * @param format 如果format为null或者空，则默认为"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static Date toDate(final String date,final String format) throws ParseException {

		final SimpleDateFormat customFormat = (SimpleDateFormat) TimeUtil.DATE_TIME_FORMAT.clone();
		customFormat.applyPattern(format.trim());
		return customFormat.parse(date);
	}
	
	/**
	 * 将字符串转化为日期,格式默认为"yyyy-MM-dd HH:mm:ss"
	 * @param date
	 * @return
	 */
	public static Date toDate(final String date) throws ParseException {
		return toDate(date.trim(), TimeUtil.DATE_TIME_PATTERN);
	}
	
	
	
	/**
	 * 获得当前时间的毫秒数
	 * @return
	 */
	public static long getMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 获得当前时间的秒数
	 * <p>
	 * 详见{@link System#currentTimeMillis()}
	 *
	 * @return
	 */
	public static int getSecs() {
		return (int)(System.currentTimeMillis()/1000);
	}
	
	
	/**
	 *
	 * 获得当前Chinese月份
	 *
	 * @return
	 */
	public static int getMonth() {
		return getMonth(now());
	}
	
	/**
	 *
	 * 获得Chinese月份
	 *
	 * @return
	 */
	public static int getMonth(final Date date) {
		Calendar calendar=calendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获得月份中的第几天
	 *
	 * @return
	 */
	public static int getDayOfMonth() {
		return getDayOfMonth(now());
	}
	
	
	/**
	 * 获得指定月份中的第几天
	 *
	 * @return
	 */
	public static int getDayOfMonth(final Date date) {
		Calendar calendar=calendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	
	/**
	 * 今天是星期几
	 *
	 * @return
	 */
	public static int getDayOfWeek() {
		return getDayOfWeek(now());
	}
	
	/**
	 * 指定日期是星期几
	 *
	 * @return
	 */
	public static int getDayOfWeek(final Date date) {
		Calendar calendar=calendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 今天是年中的第几天
	 *
	 * @return
	 */
	public static int getDayOfYear() {
		return getDayOfYear(now());
	}
	/**
	 * 指定日期是年中的第几天
	 *
	 * @return
	 */
	public static int getDayOfYear(final Date date) {
		Calendar calendar=calendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	
	
	/**
	 * 判断原日期是否在目标日期之前
	 *
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isBefore(final Date src, final Date dst) {
		return src.before(dst);
	}
	
	/**
	 * 判断原日期是否在目标日期之后
	 *
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isAfter(final Date src, final Date dst) {
		return src.after(dst);
	}
	
	/**
	 * 判断两日期是否相同
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(final Date date1, final Date date2) {
		return date1.compareTo(date2) == 0;
	}
	
	/**
	 * 判断某个日期是否在某个日期范围
	 *
	 * @param beginDate
	 *            日期范围开始
	 * @param endDate
	 *            日期范围结束
	 * @param src
	 *            需要判断的日期
	 * @return
	 */
	public static boolean isBetween(final Date beginDate, final Date endDate, final Date src) {
		return beginDate.before(src) && endDate.after(src);
	}
	
	
	/**
	 * 获得当前月的最后一天
	 * <p>
	 * HH:mm:ss为0，毫秒为999
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		return getLastDayOfMonth(now());
	}
	
	/**
	 * 指定日期所在月的最后一天
	 * <p>
	 * HH:mm:ss为0，毫秒为999
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth(final Date date) {
		final Calendar cal = calendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1,如果置0，则为上个月的最后一天，时间部分不变
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
		return cal.getTime();
	}
	
	
	/**
	 * 获得当前月的第一天
	 * <p>
	 * HH:mm:ss SS为零
	 *
	 * @return
	 */
	public static Date getFirstDayOfMonth() {

		return getFirstDayOfMonth(now());
	}
	
	/**
	 * 指定日期所在月的第一天
	 * <p>
	 * HH:mm:ss SS为零
	 *
	 * @return
	 */
	public static Date getFirstDayOfMonth(final Date date) {
		final Calendar cal = calendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return cal.getTime();
	}
	
	/**
	 * 指定日期所在月的第一天
	 * <p>
	 * HH:mm:ss SS为零
	 *
	 * @return
	 */
	public static int getFirstDayOfMonth(final int timestamp) {
		final Calendar cal = calendar();
		cal.setTimeInMillis((long)(timestamp * 1000.0));
		
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return (int)(cal.getTimeInMillis() /1000.0);
	}
	/**
	 * 获得指定日期的所在礼拜的礼拜几的日期
	 * @param week
	 * @return
	 */
	private static Date weekDay(final Date date,final int week) {
		final Calendar cal = calendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 *获得指定日期的礼拜几日期
	 * @param date
	 * @param week:MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
	 * @return
	 */
	public static Date getDateOfWeek(final Date date,int week){
		Date result;
		switch (week){
			case MONDAY : result= weekDay(date,Calendar.MONDAY);break;
			case TUESDAY : result= weekDay(date,Calendar.TUESDAY);break;
			case WEDNESDAY : result= weekDay(date,Calendar.WEDNESDAY);break;
			case THURSDAY : result= weekDay(date,Calendar.THURSDAY);break;
			case FRIDAY : result= weekDay(date,Calendar.FRIDAY);break;
			case SATURDAY : result= weekDay(date,Calendar.SATURDAY);break;
			case SUNDAY : result= weekDay(date,Calendar.SUNDAY);break;
			default:result= weekDay(date,Calendar.MONDAY);break;
		}
		return result;
	}
	
	/**
	 *获得当前日期的礼拜几日期
	 * @param week:MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
	 * @return
	 */
	public static Date getDateOfWeek( int week){
		Date result;
		switch (week){
			case MONDAY : result= weekDay(now(),Calendar.MONDAY);break;
			case TUESDAY : result= weekDay(now(),Calendar.TUESDAY);break;
			case WEDNESDAY : result= weekDay(now(),Calendar.WEDNESDAY);break;
			case THURSDAY : result= weekDay(now(),Calendar.THURSDAY);break;
			case FRIDAY : result= weekDay(now(),Calendar.FRIDAY);break;
			case SATURDAY : result= weekDay(now(),Calendar.SATURDAY);break;
			case SUNDAY : result= weekDay(now(),Calendar.SUNDAY);break;
			default:result= weekDay(now(),Calendar.MONDAY);break;
		}
		return result;
	}
	
	/**
	 * 获得当天的00:00:00
	 * @param date
	 * @return
	 */
	public static int getSecsOfMorning(Date date){
		Calendar cal = calendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis()/1000);
	}
	/**
	 * 根据时间戳获得当天的00:00:00
	 * @param timestamp
	 * @return
	 */
	public static int getSecsOfMorning(int timestamp){
		Calendar cal = calendar();
		cal.setTimeInMillis((long)(timestamp*1000.0));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis()/1000);
	}
	
	/**
	 * 获得当天的23:59:59
	 * @param date
	 * @return
	 */
	public static int getSecsOfNight(Date date){
		Calendar cal =calendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return (int) (cal.getTimeInMillis()/1000);
	}
	/**
	 * 根据时间戳获得当天的23:59:59
	 * @param timeStamp
	 * @return
	 */
	public static int getSecsOfNight(int timeStamp){
		Calendar cal = calendar();
		cal.setTimeInMillis((long)(timeStamp*1000.0));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return (int) (cal.getTimeInMillis()/1000);
	}
	
	/**
	 * 根据时间戳获得日期
	 * @param timeStamp
	 * @return
	 */
	public static Date getDateByTimeStamp(int timeStamp){
		Calendar cal = calendar();
		cal.setTimeInMillis((long)(timeStamp*1000.0));
		return  cal.getTime();
	}
	
	/**
	 * 根据时间戳获得日期
	 * @param date
	 * @return
	 */
	public static int getTimeStampByDate(Date date){
		Calendar cal = calendar();
		cal.setTime(date);
		return   (int) (cal.getTimeInMillis()/1000);
	}
	
	/**
	 *  为日期增加天数
	 * @param date 日期
	 * @param day 天数，为正 则往后,为负 则往前
	 * @return
	 */
	public static Date addDay(Date date,int day){
		Calendar cal = calendar();
		cal.setTime(date);
		cal.add(Calendar.DATE,day);
				
		return cal.getTime();
	}
	
	/**
	 *  为日期增加天数
	 * @param timeStamp 日期
	 * @param day 天数，为正 则往后,为负 则往前
	 * @return
	 */
	public static int addDay(int timeStamp,int day){
		Calendar cal = calendar();
		cal.setTimeInMillis((long)(timeStamp *1000.0));
		cal.add(Calendar.DATE,day);
		
		return (int) (cal.getTimeInMillis()/1000);
	}
	
	/**
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static LinkedHashSet<Date> getDays(Date startDate, Date endDate) throws ParseException {
		if(startDate==null||endDate==null){
			return null;
		}else if(startDate.compareTo(endDate)<=0){
			return  new LinkedHashSet<>();
		}
		
		LinkedHashSet<Integer> list=getDays(getTimeStampByDate(startDate),getTimeStampByDate(endDate));
		
		LinkedHashSet<Date> result=new LinkedHashSet<>();
		list.forEach(e->result.add(getDateByTimeStamp(e)));

		return result;
		
	}
	public static LinkedHashSet<Integer> getDays(int startTimeStamp, int endTimeStamp) throws ParseException {
		if(startTimeStamp>=endTimeStamp){
			return new LinkedHashSet<>();
		}
		int dayInt=24*60*60;
		int startTimeMorning=getSecsOfMorning(startTimeStamp);
		int endTimeNight=getSecsOfNight(endTimeStamp);
		int minus=(int)Math.floor((endTimeNight-startTimeMorning)*1.0/dayInt);//相隔天数
		LinkedHashSet<Integer> result=new LinkedHashSet<>();
		
		for(int i=0;i<=minus;i++){
			result.add(startTimeMorning +(int)(dayInt*i*1.0));
		}
		return result;
	}
	

//	/**
//	 * 根据时间戳间隔获得中间的相差天数
//	 * @param startTime
//	 * @param endTime
//	 * @return
//	 */
//	public static LinkedHashSet<String> getDays(int startTime, int endTime, String format) throws ParseException {
//		LinkedHashSet<String> days=new LinkedHashSet<>();
//		if(startTime>endTime){
//			return days;
//		}
//		int startTimeMorning=getTimesmorning(startTime);
//		int endTimeNight=getTimesnight(endTime);
//		int dayInt=24*60*60;
//		int minus=(int)Math.floor((endTimeNight-startTimeMorning)*1.0/dayInt);//相隔天数
//		for(int i=0;i<=minus;i++){
//			days.add(getDateStringByTimeStamp(startTimeMorning +(int)(dayInt*i*1.0) ,format));
//		}
//
//		return days;
//
//	}
	
	/**
	 *
	 * @param timeStamp
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param milliSecond
	 * @return
	 */
	public static int addDate(int timeStamp,int year,int month,int day,int hour,int minute,int second,int milliSecond){
		Calendar cal =calendar();
		cal.setTimeInMillis((long)(timeStamp *1000.0));
		
		cal.add(Calendar.YEAR,year);
		cal.add(Calendar.MONTH,month);
		cal.add(Calendar.DAY_OF_MONTH,day);
		cal.add(Calendar.HOUR_OF_DAY,hour);
		cal.add(Calendar.MINUTE,minute);
		cal.add(Calendar.SECOND,second);
		cal.add(Calendar.MILLISECOND,milliSecond);
		
		return (int) (cal.getTimeInMillis()/1000);
	}
	
	/**
	 *
	 * @param date
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param milliSecond
	 * @return
	 */
	public static Date addDate(Date date,int year,int month,int day,int hour,int minute,int second,int milliSecond){
		Calendar cal =calendar();
		cal.setTime(date);
		
		cal.add(Calendar.YEAR,year);
		cal.add(Calendar.MONTH,month);
		cal.add(Calendar.DAY_OF_MONTH,day);
		cal.add(Calendar.HOUR_OF_DAY,hour);
		cal.add(Calendar.MINUTE,minute);
		cal.add(Calendar.SECOND,second);
		cal.add(Calendar.MILLISECOND,milliSecond);
		
		return cal.getTime();
	}
	
	public static void main(String[] args) throws ParseException {
//		System.out.println(TimeUtil.toStr(null, null));
//		System.out.println(TimeUtil.toStr(TimeUtil.toDate(null, null), null));
//		Date d=TimeUtil.toDate("2014-08-08 09:32:04", null);
//		System.out.println(d.toString());
		
//		System.out.println(TimeUtil.getDayOfMonth());
//
//		Date date= TimeUtil.toDate("2016-10-17", TimeUtil.DATE_PATTERN);
//		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
//		calendar.setFirstDayOfWeek(Calendar.MONDAY);
//
//		calendar.setTime(date);
//		System.out.println(TimeUtil.toStr(date));
//		System.out.println(calendar.get(Calendar.MONTH)+1);
//		System.out.println(TimeUtil.getDayOfMonth(TimeUtil.getLastDayOfMonth(date)));
//		System.out.println(TimeUtil.toStr(TimeUtil.getDateOfWeek(1)));
		
		System.out.println(TimeUtil.toStr(TimeUtil.addDate(TimeUtil.getSecs(),0,0,0,0,0,0,0)));
		
				 
		
	}

}
