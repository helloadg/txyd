package com.txyd.database.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.txyd.database.annotation.Column;
import com.txyd.database.annotation.Table;
import com.txyd.database.bean.BaseBean;
import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.TableBean;
import com.txyd.database.inter.DatabaseType;
import com.txyd.database.inter.NullAble;
import com.txyd.database.inter.Primarykey;
import com.txyd.database.inter.TableType;



/**
 * 注解处理类
 * @author Administrator
 *
 */
public class AnnotationUtil {
	/**
	 * 
	 * @Description 将注解和其对应的javabean的属性放入map中
	 * @author     
	 * @param cls
	 * @param k
	 * @return
	 */
	private static <T extends Annotation,MODEL extends BaseBean >  Map<Method, Field> parseAnnotation2Map(Class<T> cls,MODEL model)
	{
		Map<Method, Field> map=new HashMap<Method, Field>();
		Field[] fields=model.getClass().getDeclaredFields();
		Field.setAccessible(fields, true);
		
		Method[] methods=cls.getDeclaredMethods();
		Method.setAccessible(methods, true);
		for(Method method:methods)
		{
			for(Field field:fields)
			{
				//注解的可以显示的方法名和类的非只读属性名相同
				if(method.getName().equals(field.getName())
						&&!Modifier.isFinal(field.getModifiers()))
				{
					map.put(method, field);
				}				
//				//注解的可以显示的方法名和类的非只读属性名相同
//				if(method.isAnnotationPresent(AnnotationField.class)
//						&&method.getAnnotation(AnnotationField.class).require()
//						&&method.getName().equals(field.getName())
//						&&!Modifier.isFinal(field.getModifiers()))
//				{
//					map.put(method, field);
//				}
			}		
		}
		return map;
	}
	/**
	 * 
	 * @Description 生成javabean注解的注释
	 * @author     
	 * @param mapA
	 * @return
	 */
	private static <T extends Annotation,MODEL extends BaseBean >  String[]  annotationComments(Class<T> clzz, final MODEL model,final JavaConfigBean jcb)
	{
		
		
		String comments="";
		{//静态添加注释
			if(clzz.isAssignableFrom(Table.class))
			{
				TableBean tableBean=(TableBean)model;
				comments+="/**\n";
				comments+=" *\n";			
				comments+=" *数据库类型：{databaseType}\n";
				comments+=" *表所属schema：{tableSchema}\n";
				comments+=" *表所属用户：{tableOwner}\n";
				comments+=" *表名称：{tableName}\n";
				comments+=" *表注释：{comments}\n";
				comments+=" *类型：{tableType}\n";
				comments+=" *@author：{author}\n";
				comments+=" */";
				comments=comments.replace("{databaseType}", tableBean.getTableSchema());
				comments=comments.replace("{tableSchema}", tableBean.getTableSchema());
				comments=comments.replace("{tableOwner}", tableBean.getTableOwner());
				comments=comments.replace("{tableName}", tableBean.getTableName());
				comments=comments.replace("{comments}", tableBean.getComments());
				comments=comments.replace("{tableType}", tableBean.getTableType().equals(TableType.table)?"基本表":"视图");
				comments=comments.replace("{author}", jcb.getAuthor().trim());
			}else if(clzz.isAssignableFrom(Column.class))
			{
				ColumnBean columnBean=(ColumnBean)model;
				comments+="\t/**\n";
				comments+="\t *\n";	
				comments+="\t * 是否可以为NULL：{nullAble}\n";
				comments+="\t * 列类型：{columnType}\n";
				comments+="\t * 默认值：{defaultValue}\n";
				comments+="\t * 列的数据类型的长度：{dataLength}\n";
				comments+="\t * 列注释：{comments}\n";
				comments+="\t * 列的扩展：{extra}\n";
				comments+="\t * 列名：{columnName}\n";
				comments+="\t * 列的数据类型：{dataType}\n";
				comments+="\t * 是否是主键：{isPrimaryKey}\n";
				comments+="\t */";
				
				comments=comments.replace("{nullAble}", columnBean.getNullAble().equals(NullAble.yes)?"可以":"不可");
				comments=comments.replace("{columnType}", columnBean.getColumnType());
				comments=comments.replace("{defaultValue}", columnBean.getDefaultValue());
				comments=comments.replace("{dataLength}", columnBean.getDataLength());
				
				comments=comments.replace("{comments}", StringUtil.newLine2Html(columnBean.getComments()));
				comments=comments.replace("{extra}", columnBean.getExtra());
				comments=comments.replace("{columnName}", columnBean.getColumnName());
				comments=comments.replace("{dataType}", columnBean.getDataType());
				comments=comments.replace("{isPrimaryKey}", Primarykey.valueOf(columnBean.getPrimaryKey())==Primarykey.yes?"是":"否");			
			}
		}
		
		
		
		return comments.split("\n");
	}
	
	/**
	 * 
	 * @Description 生成javabean的注解
	 * @author     
	 * @param clzz 注解名.class
	 * @param model 类名
	 * @return
	 */
	private static <T extends Annotation,MODEL extends BaseBean > String[] annotationContext(Class<T> clzz, final MODEL model,final JavaConfigBean jcb)
	{
		List<String> comments=new ArrayList<>();		
		
		{//添加jackson的注解模式
			if(model.getClass().isAssignableFrom(ColumnBean.class) )
			{
				ColumnBean cb=(ColumnBean)model;
				comments.add("@"+"JsonProperty(\""+cb.getColumnName()+"\")");
			}else if(model.getClass().isAssignableFrom(TableBean.class) )
			{
				comments.add("@JsonIgnoreProperties(ignoreUnknown = true)");
				comments.add("@JsonInclude(JsonInclude.Include.NON_NULL)");
				
			}			
		}	

		return  comments.toArray(new String[1]);//将list转为字符串数组输出，和1无关系；
	
	}
	/**
	 * 
	 * @Description 返回ColumnBean类实例的注解
	 * @author     
	 * @param cls
	 * @param model
	 * @return
	 */
	public static Column getAnnotation(final ColumnBean model,final JavaConfigBean jcb)
	{
		Column annotation =new  Column() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return this.getClass();
			}
			
			@Override
			public NullAble nullAble() {
				return NullAble.valueOf(model.getNullAble());
			}
			
			@Override
			public boolean isVirtual() {
				return this.isVirtual();
			}
			
			@Override
			public Primarykey isPrimaryKey() {
				return Primarykey.valueOf(model.getPrimaryKey());
			}
			
			@Override
			public String defaultValue() {
				return model.getDefaultValue();
			}
			
			@Override
			public String dataType() {
				return model.getDataType();
			}
			
			@Override
			public String dataLength() {
				return model.getDataLength();
			}
			
			@Override
			public String comments() {
				return model.getComments();
			}
			
			@Override
			public String columnName() {
				return model.getColumnName();
			}

			@Override
			public String columnType() {
				return model.getColumnType();
			}

			@Override
			public String extra() {
				return model.getExtra();
			}
			@Override
			public String[] annotationContext() {
				return AnnotationUtil.annotationContext(Column.class,model,jcb);
			}
			
			@Override
			public String[] annotationComments() {
				return AnnotationUtil.annotationComments(Column.class,model,jcb);
			}

		};			
		return annotation;	
	}
	/**
	 * 
	 * @Description 返回tableBean的注解
	 * @author     
	 * @param cls
	 * @param model
	 * @return
	 */
	public static Table getAnnotation(final TableBean model,final JavaConfigBean jcb)
	{
		Table annotation=new Table() {
			@Override
			public Class<? extends Annotation> annotationType() {
				return this.getClass();
			}
			
			@Override
			public TableType tableType() {
				return Enum.valueOf(TableType.class, model.getTableType());
			}
			
			@Override
			public String tableSchema() {
				return model.getTableSchema();
			}
			
			@Override
			public String tableOwner() {
				return model.getTableOwner();
			}
			
			@Override
			public String tableName() {
				return model.getTableName();
			}
			
			@Override
			public DatabaseType databaseType() {
				return model.getDatabaseType();
			}
			
			@Override
			public String comments() {
				return model.getComments();
			}
			
			@Override
			public String[] annotationContext() {
				return AnnotationUtil.annotationContext(Table.class,model,jcb);
			}
			
			@Override
			public String[] annotationComments() {
				return AnnotationUtil.annotationComments(Table.class,model,jcb);
			}
		};
		return annotation;
	}
	
	public static void main(String[] args)
	{
		try {
			for(Method method:Column.class.getDeclaredMethods())
			{
				
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		{
//			System.out.println(Column.class.getName());
//			System.out.println(Column.class.getCanonicalName());
//			System.out.println(Column.class.getSimpleName());
		}
		{
//			String str="哈哈\n\n嘎嘎嘎\n\n嘻嘻嘻霞霞\n";
//			System.out.println(str);
//			str=str.replace("\n", "<br />");
//			System.out.println(str);
		}

		
//		TableBean table=new TableBean();
//		table.setTableOwner("root");
//		table.setTableName("t_worksheet_client");
//		table.setTableSchema("work_order");
//		table.setTableType("view");
//		table.setComments("客户表");
//		System.out.println(AnnotationUtil.getAnnotation(Table.class, table).annotationComments());
//		System.out.println(AnnotationUtil.getAnnotation(Table.class, table).annotationForField());
//		
//		ColumnBean column=new ColumnBean();
//		column.setTableOwner("root");
//		column.setTableName("t_worksheet_client");
//		column.setTableSchema("work_order");
//		column.setDataType("INTEGER");
//		column.setColumnName("password");
//		column.setComments("用户姓名");
//		System.out.println(AnnotationUtil.getAnnotation(Column.class, column).annotationComments());
//		System.out.println(AnnotationUtil.getAnnotation(Column.class, column).annotationForField());
		
	}

}
