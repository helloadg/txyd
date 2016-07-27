package com.txyd.database.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理类
 * @author Administrator
 *
 */
public class StringUtil {
	/**
	 * 
	 * @Description 生成模版
	 * @author     
	 * @param template
	 * @param map
	 * @return
	 */
	public static   String getStringFromTemplate(String template,Map<String, String>map)
	{
		for(String key:map.keySet())
		{
			template=template.replace("{"+key+"}", map.get(key)==null?"":map.get(key).trim());
		}
		
		return "";
	}
	/**
	 * 
	 * @Description 将java中的换行转化为hmtl的"<br />"标签
	 * @author     
	 * @param string
	 * @return
	 */
	public static String newLine2Html(String string)
	{
		if(string==null)
		{
			return "";
		}
		if(string.contains("\n")||string.contains("\r")||string.contains("\f"))
		{
			string=string.replace("\n", "<br />").replace("\r", "<br />").replace("\f", "<br />");
		}
		if(string.contains("<br /><br />"))
		{
			string=string.replace("<br /><br />", "<br />");
		}
		return string;
	}
	/**
	 * 描述：按照java命名规则来生成，不符合规则的字符用"$"来代替，如果是java关键字的加前缀"$"
	 * @Description 返回str对应的javabean的class名称
	 * @author     
	 * @param string
	 * @return
	 */
	public static String getJavabeanClassName(String string)
	{
		if(string==null||string.trim().equals(""))
		{
			return "";
		}else
		{
			string=getJavabeanFieldName(string.trim());
			if(string.length()==1){
				string=string.substring(0,1).toUpperCase()+string.substring(1);				
			}else {
				string=string.substring(0,2).toLowerCase()+string.substring(2);	//前两个字母小写
				string=string.substring(0,1).toUpperCase()+string.substring(1);	//首字母大写
			}
			string=JavaKeywordSet.getInstance().contains(string.trim())?"$"+string:string;//如果是java的关键字，则添加前缀"$"
			return string;
		}
	}
	/**
	 * 描述：按照java命名规则来生成，不符合规则的字符用"$"来代替，如果是java关键字的加前缀"$"
	 * @Description 返回str对应的javabean属性名称
	 * @author     
	 * @param str
	 * @return
	 */
	public static String getJavabeanFieldName(String string)
	{
		if(string==null||string.trim().equals(""))
		{
			return "";
		}else
		{
			string=StringUtil.string2JavaString(string.trim());
			String[] strArray=string.split("_");
			String result="";
			for(int i=0;i<strArray.length;i++)
			{
				result+=StringUtil.toUpperCaseOfFirstChar(strArray[i]);
			}
			result=StringUtil.startWithNumber2JavaString(result);
			if(result.length()==1){
				result=result.toLowerCase();
			}else{
				if(Character.isUpperCase(result.charAt(0))||Character.isUpperCase(result.charAt(1))){//前两个字母只要有一个是大写
					result=result.substring(0, 2).toLowerCase()+result.substring(2);//保证前两个字母都是小写（javabean规范，第二个字母必须小写）
//					result=result.substring(0, 1).toLowerCase()+result.substring(1);//保证第一个字母是小写
				}
			}

			result=JavaKeywordSet.getInstance().contains(result.trim())?"$"+result:result;//如果是java的关键字，则添加前缀"$"
			return result;
		}
	}
	/**
	 * 描述：按照java命名规则来生成javabean的set与get方法中的属性名，
	 *     比如：属性名为name，则改为Name；属性名为cName，则改为cName;
	 * @Description 返回str对应的javabean属性名称
	 * @author     
	 * @param str
	 * @return
	 */
	public static String getJavabeanFieldNameOfSetGetMethod(String str){
		if(str==null||str.trim().equals("")){
			return "";
		}else{
			str=StringUtil.getJavabeanFieldName(str);
			if(str.length()==1){
				str=str.toUpperCase();
			}else{
				if(!Character.isUpperCase(str.charAt(0))&&!Character.isUpperCase(str.charAt(1))){
					str= StringUtil.toUpperCaseOfFirstChar(str);
				}
			}
			return str;

		}
		
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
	/**
	 * 
	 * @Description 首字母小写
	 * @author     
	 * @param str
	 * @return
	 */
	public static String toLowerCaseOfFirstChar(String string)
	{
		if(string==null||string.trim().equals(""))
		{
			return "";
		}else
		{
			string=string.trim();
			String firstChar=string.substring(0, 1).toLowerCase();
			string=firstChar+string.substring(1);			
		}
		return string;
	}
	/**
	 * 
	 * @Description 如果string中有不符合java变量命名规范的字符，则改为"$"
	 * @author     
	 * @param string
	 * @return
	 */
	public static String string2JavaString(String string)
	{
		StringBuffer stringBuffer =new StringBuffer();
		//String string="dsdsd-fsdf_fdssafg_$$--tt";
		String regex="([0-9a-zA-Z$_]?)([^0-9a-zA-Z$_]?)";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(string);
		while(m.find())
		{
			stringBuffer.append(m.group(1)+m.group(2).replaceAll("([^0-9a-zA-Z$_]{1})", "\\$"));
		}
		return stringBuffer.toString();
	}
	/**
	 * 
	 * @Description 去掉string中不符合java变量命名规范的字符，如果字符串以数字开头，则在字符串开头添加"$"
	 * @author     
	 * @param string
	 * @return
	 */
	public static String startWithNumber2JavaString(String string)
	{
		string=StringUtil.string2JavaString(string);
		String regex="^[0-9]+[a-zA-Z0-9_$]*$";
		if(Pattern.compile(regex).matcher(string).matches())
		{
			string="$"+string;
		}
		return string;
	}
	/**
	 * 
	 * @Description 去除双斜线
	 * @author     
	 * @param string
	 * @return
	 */
	public static String removeDoubleSlash(String string)
	{
		if(string.contains("//"))
		{
			return removeDoubleSlash(string.replace("//", "/"));
		}if(string.contains("\\\\")){

			return removeDoubleSlash(string.replace("\\\\", "\\"));
		}if(string.contains("\\/")){

			return removeDoubleSlash(string.replace("\\/", "/"));
		}if(string.contains("/\\")){

			return removeDoubleSlash(string.replace("/\\", "/"));
		}else
		{
			return string;
		}
	}
	/**
	 * 
	 * @Description 根据java的包名，来推断java类的后缀名，如掉后缀名的".",返回以便添加到javabean的名称之后形成此包下的类名
	 * @author     
	 * @param string
	 * @return
	 */
	public static String getClassPrefixFromPackage(String string)
	{
		if(Pattern.compile("^[a-zA-Z0-9]+[a-zA-Z0-9.]+[a-zA-Z0-9]+$").matcher(string).matches()
				&&!Pattern.compile("([.]{2,})").matcher(string).find())//判断是否是包名
		{
			return StringUtil.toUpperCaseOfFirstChar(string.substring(string.lastIndexOf(".")+1));
		}else
		{
			return "";
		}
	}
	public static void main(String[] args)
	{
		{
			String str="cName";
			System.out.println(StringUtil.getJavabeanFieldNameOfSetGetMethod(str));
		}
		{
			String str="aa(bb)dd(cc)ee(ff)gg(*)hh";
			String[] strs=str.split("[(\\(\\*\\))]");
			for(String ss:strs)
			{
				System.out.println(ss);
				
			}
		}
		{
//			String str2="      ";
//			String str="      (#{item.id},#{item.phone},#{item.clientType},#{item.name},#{item.sex},#{item.companyName},#{item.cityId},#{item.detailAdress},#{item.ypzw},#{item.ghType},#{item.ct},#{item.ut},#{item.isDeleted})";
//			Pattern p=Pattern.compile("([\\S])");
//			Matcher m=p.matcher(str2);
//			int end=0;
//			if(m.find())
//			{
//				end=m.start();
//				System.out.println(m.start());				
//			}
//			System.out.println(str.substring(0, end));	
		}
		{
//			List<String> list=new ArrayList<String>();
//			System.out.println(list.getClass().isInstance(new ArrayList<String>()));
//			System.out.println(list instanceof ArrayList);
//			System.out.println(ArrayList.class.isInstance(list));
//			System.out.println(list.getClass().isAssignableFrom(ArrayList.class));
//			System.out.println(ArrayList.class.isAssignableFrom(list.getClass()));
		}
		{
//			StringBuffer stringBuffer =new StringBuffer();
//			String string="dsdsd-fsdf_fdssafg_$$--tt";//dsdsd$fsdf_fdssafg_$$$$tt
////			String regex="([a-zA-Z$_]+)";
////			String regex="([^a-zA-Z$_]{1})";
//			String regex="([a-zA-Z$_]?)([^a-zA-Z$_]?)";
//			Pattern p=Pattern.compile(regex);
//			Matcher m=p.matcher(string);
//			int count=0;
//			while(m.find())
//			{
//				count++;
//				stringBuffer.append(m.group(1));
//				stringBuffer.append(m.group(2).replaceAll("([^a-zA-Z$_]{1})", "\\$"));
//				System.out.println(count+":"+m.group(1)+m.group(2).replaceAll("([^a-zA-Z$_]{1})", "\\$"));
//			}
//			System.out.println(stringBuffer.toString());
		}
		{
//			String string="java.lang.tt";
//			System.out.println(string.startsWith("java.lang"));
		}
		{
//			String string="xxx.dd.tt.bb.ddd";
//			System.out.println(getClassPrefixFromPackage(string));
		}
		{
//			String outModel="tt//ddf/////eee///\\";
//			System.out.println(removeDoubleSlash(outModel));
		}
		{
//			String outModel="com.txyd.model";
//			System.out.println(outModel.replace(".","/"));
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
