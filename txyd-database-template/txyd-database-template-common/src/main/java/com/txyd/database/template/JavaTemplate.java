package com.txyd.database.template;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

import com.txyd.database.inter.JavaType;
import com.txyd.database.utils.StringUtil;



public final class JavaTemplate {
	/**
	 * java类模版
	 */
	private static final String classTemplate;
	/**
	 * java接口模版
	 */
	private static final String intefaceTemplate;
	/**
	 * java注解模版
	 */
	private static final String annotationTemplate;
	/**
	 * java枚举模版
	 */
	private static final String enumTemplate;
	/**
	 * java属性模版
	 */
	private static final String fieldTemplate;
	/**
	 * java方法模版
	 */
	private static final String methodTemplate;
	static{//java枚举模版
		String template="";
		template+="{tab}{package}\n";//注解的package模版
		template+="{tab}{import}\n";//注解的import模版
		template+="{tab}{comments}\n";//注解的class注释模版
		template+="{tab}{annotation}\n";//注解的class注解模版
		template+="{tab}{modify} enum {enumName} {superIntefaceList} {\n";//注解的class注解模版
		template+="{tab}{fields}\n";//注解的属性模版
		template+="{tab}{methods}\n";//注解的方法模版
		template+="{tab}}\n";//
		enumTemplate=template;
	}
	static{//java方法模版
		String template="";
		template+="{tab}{comments}\n";//注解的class注释模版
		template+="{tab}{annotation}\n";//注解的class注解模版
		template+="{tab}{modify} {method}\n";//注解的package模版	
		methodTemplate=template;
	}
	static{//java属性模版
		String template="";
		template+="{tab}{comments}\n";//注解的class注释模版
		template+="{tab}{annotation}\n";//注解的class注解模版
		template+="{tab}{modify} {fieldName};\n";//注解的package模版	
		fieldTemplate=template;
	}
	static{//注解模版
		String template="";
		template+="{tab}{package}\n";//注解的package模版
		template+="{tab}{import}\n";//注解的import模版
		template+="{tab}{comments}\n";//注解的class注释模版
		template+="{tab}{annotation}\n";//注解的class注解模版
		template+="{tab}{modify} @inteface {intefaceName} {superIntefaceList} {\n";//注解的class注解模版
		template+="{tab}{fields}\n";//注解的属性模版
		template+="{tab}{methods}\n";//注解的方法模版
		template+="{tab}}\n";//
		annotationTemplate=template;
	}
	static{//接口模版
		String intefaceModel="";
		intefaceModel+="{tab}{package}\n";//接口的package模版
		intefaceModel+="{tab}{import}\n";//接口的import模版
		intefaceModel+="{tab}{comments}\n";//接口的class注释模版
		intefaceModel+="{tab}{annotation}\n";//接口的class注解模版
		intefaceModel+="{tab}{modify} inteface {intefaceName} {superIntefaceList} {\n";//接口的class注解模版
		intefaceModel+="{tab}{fields}\n";//接口的属性模版
		intefaceModel+="{tab}{methods}\n";//接口的方法模版
		intefaceModel+="{tab}}\n";//
		intefaceTemplate=intefaceModel;
	}
	static{//类模版		
		String template="";
		template+="{tab}{package}\n";//类的package模版
		template+="{tab}{import}\n";//类的import模版
		template+="{tab}{comments}\n";//类的class注释模版
		template+="{tab}{annotation}\n";//类的class注解模版
		template+="{tab}{modify} class {className} {superClassName} {superIntefaceList} {\n";//类的class注解模版
		template+="{tab}{fields}\n";//类的属性模版
		template+="{tab}{methods}\n";//类的方法模版
		template+="{tab}}\n";//
		classTemplate=template;		
	}
	/**
	 * 
	 * @Description 创建注解
	 * @author     
	 * @param map
	 * @return
	 */
	private static <T> String createAnnotation(Map<String, T> map)
	{
		return "";
	}
	/**
	 * 
	 * @Description 创建类
	 * @author     
	 * @param map
	 * @return
	 */
	private static   String createClass(Map<String,  String> map)
	{
		return "";
	}
	/**
	 * 
	 * @Description 创建属性
	 * @author     
	 * @param map
	 * @return
	 */
	private static  String createField(Map<String,  String> map)
	{
		Set<String> set=new HashSet<String>();
		set.add("tab");
		set.add("comments");
		set.add("annotation");
		set.add("modify");
		set.add("fieldName");
		String template=fieldTemplate;
		if(!set.equals(map.keySet())||mapExistsNull(map))
		{
			return "";
		}
		return StringUtil.getStringFromTemplate(template, map);
	}
	/**
	 * 
	 * @Description 创建方法
	 * @author     
	 * @param map
	 * @return
	 */
	private static   String createMethod(Map<String, String> map)
	{
		Set<String> set=new HashSet<String>(){
			private static final long serialVersionUID = 1L;
			{
				add("tab");
				add("comments");
				add("annotation");
				add("modify");
				add("method");
			}
		};
		String template=methodTemplate;
		if(!set.equals(map.keySet())||mapExistsNull(map))
		{
			return "";
		}
		String method=map.get("method");
		if(method.contains("{"))//说明是已经实现的方法
		{
			
		}
		return StringUtil.getStringFromTemplate(template, map);
	}
	/**
	 * 
	 * @Description 创建接口
	 * @author     
	 * @param map
	 * @return
	 */
	private static    String createInteface(Map<String,  String > map)
	{
		return "";
	}
	/**
	 * 
	 * @Description 创建枚举
	 * @author     
	 * @param map
	 * @return
	 */
	private static    String createEnum(Map<String,String > map)
	{
		return "";
	}

	/**
	 * 
	 * @Description 判断map是否存在null值
	 * @author     
	 * @param map
	 * @return
	 */
	private static <K,V> boolean mapExistsNull(Map<K, V> map)
	{
		if(map==null||map.entrySet().size()==0)
		{
			return true;
		}
		for(K key:map.keySet())
		{
			if(map.get(key)==null)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @Description 创建指定的java类型
	 * @author     
	 * @param map
	 * @return
	 */
	public static String create(Map<String, String> map,JavaType javaType)
	{
		if(map==null||javaType==null)
		{
			return null;
		}
		if(javaType==JavaType.Annotation)
		{
			return createAnnotation(map);
		}else if(javaType==JavaType.Class)
		{
			return createClass(map);
		}else if(javaType==JavaType.Field)
		{
			return createField(map);
		}else if(javaType==JavaType.Method)
		{
			return createField(map);
		}else if(javaType==JavaType.Enum)
		{
			return createEnum(map);
		}else
		{
			return null;		
		}
	}
	/**
	 * 
	 * @Description 去除javatemplate中的"\n"、"\t"、"\r"、"\f"和多余的字符，比如";"
	 * @author     
	 * @param str
	 * @return
	 */
	private static String removeSpecialCharAndDoubleSpace(String str)
	{
		StringBuffer sbf=new StringBuffer();
		Pattern p=Pattern.compile("\\/\\*(\\s|.)*?\\*\\/");
		Matcher m=p.matcher(str);
		while(m.find())
		{
			sbf.append(m.group()+"\n");
			System.out.println(m.group());
		}
		p=Pattern.compile("\\/\\/.*");
		m=p.matcher(str);
		while(m.find())
		{
			sbf.append(m.group()+"\n");
			System.out.println(m.group());
		}
		return sbf.toString();
		
//		StringBuffer sbu=new StringBuffer();
//		String[] strs=str.split("\"");
//		for(int i=0;i<strs.length;i+=2)
//		{
//			if(strs[i]==null||strs[i].equals(""))
//			{
//				continue;
//			}
//			strs[i]=strs[i].replaceAll("(\\n)", " ");
//			strs[i]=strs[i].replaceAll("(\\t)", " ");
//			strs[i]=strs[i].replaceAll("(\\f)", " ");
//			strs[i]=strs[i].replaceAll("(\\r)", " ");
//			strs[i]=strs[i].replaceAll("([\\s]{2})", " ");
//			strs[i]=strs[i].replaceAll("([\\s]+[;]{1})", ";");
//			strs[i]=strs[i].replaceAll("([;]{1}[\\s]+)", ";");
//			strs[i]=strs[i].replaceAll("(^[\\s]+)", "");
//			strs[i]=strs[i].replaceAll("([\\s]+$)", "");
//			sbu.append(strs[i]);
//			if(i+1<strs.length)
//			{
//				sbu.append("\""+strs[i+1]+"\"");				
//			}
//		}
//		return sbu.toString().replaceAll("([\"]{2})", "");
	}
	/**
	 * 
	 * @Description 格式化java代码
	 * @author     
	 * @param str
	 * @return
	 */
	private static String formatJavaTemplate(String str)
	{
		str=removeSpecialCharAndDoubleSpace(str);
		str=str.replace(";", ";\n");
		
		return str;
	}
	public static void main(String[] args)
	{
		{
//			String str="呵呵呵 ; 哈 哈  ; 哈哈dd  ' '; ; ;嘿嘿    ;   ; 	; ;\"d d	d\"  嘿嘿\"	\";\"\" ;";
			String str="";
			try {
				StringBuffer stb=new StringBuffer();
				BufferedReader br=new BufferedReader(new FileReader(FileSystemView.getFileSystemView().getHomeDirectory()+"/old.java"));
				while((str=br.readLine()) != null)
				{
					stb.append(str);
				}
				str=removeSpecialCharAndDoubleSpace(stb.toString());
				System.out.println(str);
				BufferedWriter bw=new BufferedWriter(new FileWriter(FileSystemView.getFileSystemView().getHomeDirectory()+"/new.java"));
				bw.write(str);
				bw.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		{
//			String str="呵呵呵;\n哈哈\n\n\n			\n\n\n;哈哈\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n嘿嘿	;嘿嘿";
//			System.out.println(formatJavaTemplate(str));
		}
		{
//			Set<String> set=new HashSet<String>(){
//			private static final long serialVersionUID = 1L;
//			{
//				add("tab");
//				add("comments");
//				add("annotation");
//				add("modify");
//				add("method");
//			}
//		};
//		Set<String> set2=new HashSet<String>(Arrays.asList("tab","comments","annotation","modify","method"));
//		for(String key:set2)
//		{
//			System.out.println(key);
//		}
		}

		
	}
}
