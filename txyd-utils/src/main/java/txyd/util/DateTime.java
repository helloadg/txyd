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
import java.util.Date;

/**
 * TODO 时间
 * @author ：    
 * @email  ：    @126.com
 */
public class DateTime {
//	public static final String FORMAT="yyyy-MM-dd HH:mm:ss.SSS";
	public static final String FORMAT="yyyy-MM-dd HH:mm:ss";
	/**
	 * 
	 * TODO 将时间格式按format或者"yyyy-MM-dd HH:mm:ss"输出
	 * 创建人：    
	 * 创建时间: 2014-7-7
	 * 联系方式:    @126.com
	 * 修改人：
	 * 修改时间：
	 * @param date   日期时间，如果为null 或者为空，则为now 即：new Date()
	 * @param format 日期时间格式，如果为null或者空，则为"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String toString(Date date,String format)
	{
		String timeString="";
		if(date==null)
		{
			date=new Date();
		}
		if(format==null || format.trim().equals(""))
		{
			format=FORMAT;
		}
		SimpleDateFormat sf = new SimpleDateFormat(format.trim());
		timeString=sf.format(date);			
		return timeString;
	}
	/**
	 * 
	 * TODO 将日期转化为字符串
	 * 创建人：    
	 * 创建时间: 2014-7-7
	 * 联系方式:    @126.com
	 * 修改人：
	 * 修改时间：
	 * @param date  如果date为null，则date默认为当前时间
	 * @param format 如果format为null或者空，则默认为"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static Date toDate(String date,String format)
	{
		Date dateTemp=null;
		if(date==null|| date.trim().equals(""))
		{
			dateTemp=new Date();
		}else
		{
			if(format==null || format.trim().equals(""))
			{
				format=FORMAT;
			}
			SimpleDateFormat sf = new SimpleDateFormat(format.trim());
			try {
				dateTemp=sf.parse(date);
			} catch (ParseException e) {
				dateTemp=null;
				//e.printStackTrace();
			}
		}		
		return dateTemp;
	}
	public static void main(String[] args)
	{
		System.out.println(DateTime.toString(null, null));
		System.out.println(DateTime.toString(DateTime.toDate(null, null), null));
		Date d=DateTime.toDate("2014-08-08 09:32:04", null);
		System.out.println(d.toString());
		
	}

}
