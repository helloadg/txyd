package txyd.util;


/**
 * 字符串处理类
 * @author Administrator
 *
 */
public class StringUtil {
	/**
	 * 去除左边空格
	 * @param string
	 * @return
	 */
	public static String leftTrim(String string){
		if(string==null){
			return "";
		}
		return string.replaceFirst("^\\s+", "");
	}
	/**
	 * 去除右边空格
	 * @param string
	 * @return
	 */
	public static String rightTrim(String string){
		if(string==null){
			return "";
		}
		return string.replaceFirst("\\s+$", "");
	}
	
	/**
	 * 
	 * @Description 首字母大写
	 * @author     
	 * @param str
	 * @return
	 */
	public static String toUpperCaseOfFirstChar(String string)
	{
		if(string==null||string.trim().equals(""))
		{
			return "";
		}else
		{
			string=string.trim();
			String firstChar=string.substring(0, 1).toUpperCase();
			string=firstChar+string.substring(1);			
		}
		return string;
	}
	
	public static void main(String[] args)
	{
		{
			String str="\t\tddd\t\t";
			System.out.println(str);
			System.out.println(str.trim());
			System.out.println(StringUtil.leftTrim(str));
			System.out.println(StringUtil.rightTrim(str));
		}
		{
//			String string="12344dsdsdfsdffdssa_f$gtt";
//			String regex="^[0-9]+[a-zA-Z0-9_$]*$";
//			Pattern p=Pattern.compile(regex);
//			Matcher m=p.matcher(string);
//			System.out.println(m.matches());
//			System.out.println(m.regionStart());
//			System.out.println(string.startsWith("[0-9]*"));
		}
		{
////			String string="dsdsd-fsdf_fdssafg_$$--tt";
//			String string="dsdsdfsdf_fdssafg_$$tt";
//			String regex="([a-zA-z$_]+)";
//			System.out.println(string);
//			Pattern p=Pattern.compile(regex);
//			Matcher m=p.matcher(string);
//			while(m.find())
//			{
//				System.out.println(m.group(1));
////				System.out.println(string.substring(m.start(),m.end()));
//			}
		}
		{
//			String string="c___Time";
//			System.out.println(StringUtil.getJavabeanFieldName(string));			
		}
		{
//			String str="c_c_dd_ee";
//			System.out.println(StringUtil.getJavabeanFieldName(str));
//			System.out.println(StringUtil.getJavabeanClassName(str));
		}
		
		{
//			String str="c:/abc/bcd/def.txt/tt";
//			String regex=".+/(.+)$";
//			String result="";
//			Matcher matcher=Pattern.compile(regex).matcher(str);
//			if(matcher.find())
//			{
//				result=matcher.group(1);
//			}
//			System.out.println(result);
		}
		{
//			String str="dogdogdog";
//			String regex="(dog)\\1";
//			String result="";
//			Matcher matcher=Pattern.compile(regex).matcher(str);
//			if(matcher.find())
//			{
//				result=matcher.group(1);
//			}			
//			System.out.println(result);
//			if(matcher.find())
//			{
//				result=matcher.group(1);
//			}
//			System.out.println(result);
		}
		{
//			String str="窝窝窝窝窝窝咯咯哒咯咯哒咯咯哒我我我我我要要";
//			String regex="(.+)\\1+";
//			String result="";
//			Matcher matcher=Pattern.compile(regex).matcher(str);
//			result=matcher.replaceAll("$1");
//			System.out.println(result);
		}
		{
//			System.out.println("\t".matches("\\s(1)"));
//			System.out.println("\t".matches("\\s{1}"));
//			System.out.println("\n\r\t".matches("\\s(4)"));
//			System.out.println("\\v");
//			System.out.println("192".matches("[0-2][0-9][0-9]"));
//			System.out.println("192".matches("[0-2][0-2][0-9][0-9]"));
//			String str="123-34345-234-00";
//			Pattern p = Pattern.compile("\\d{3,5}");
//		    Matcher m = p.matcher(str);
//		    System.out.println( m.matches());
//		    System.out.println( m.reset());
//		    System.out.println( str);
//		    System.out.println( m.find());
//		    System.out.println( m.start()+"---"+m.end());
//		    System.out.println( str.substring(m.start(),m.end()));
//		    
//		    System.out.println( m.find());
//		    System.out.println( m.start()+"---"+m.end());
//		    System.out.println( str.substring(m.start(),m.end()));
//		    
//		    System.out.println( m.find());
//		    System.out.println( m.start()+"---"+m.end());
//		    System.out.println( str.substring(m.start(),m.end()));
//		    
//		    System.out.println( m.find());
//		    
//		    
//		    System.out.println( m.lookingAt());
//		    System.out.println( m.lookingAt());
		}
		{//字符串替换
//			String str="java  Java  jAva  ILoveJavA youHateJAVA adsdsfd";	        
//	        Pattern p = Pattern.compile("java",Pattern.CASE_INSENSITIVE);//Pattern.CASE_INSENSITIVE大小写不敏感
//	        Matcher m = p.matcher(str);	        
//	        StringBuffer  buf = new StringBuffer();//存放字符串	        
//	        int i  = 0;//计数奇偶数
//	        while(m.find()){
//	            i++;
//	            if(i%2 == 0){
//	                m.appendReplacement(buf, "java");
//	    		    System.out.println( "["+buf+"]["+str.substring(m.start(),m.end())+"]");
//	            }else{
//	                m.appendReplacement(buf, "JAVA");
//	    		    System.out.println( "["+buf+"]["+str.substring(m.start(),m.end())+"]");
//	            }
//	        }	        
//	        m.appendTail(buf);//不加这句话，字符串adsdsfd将会被遗弃
//		    System.out.println( buf);
		}
		{//group分组,用()分组			
//	        Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})(-{1})");
//	        String s = "123aa-34345bb-234cc-00";
//	        Matcher m = p.matcher(s);
//	        System.out.println( m.groupCount());
//	        while(m.find()){
//		        System.out.println(m.group());//数字字母都有
//		        System.out.println(m.group(1));//只有数字
//		        System.out.println(m.group(2));//只有字母
//		        System.out.println(m.group(3));//只有-
//	        }
		}
		
		
		
	}

}
