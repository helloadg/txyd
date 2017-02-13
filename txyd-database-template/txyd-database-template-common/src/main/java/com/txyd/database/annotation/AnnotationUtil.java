package com.txyd.database.annotation;

import com.txyd.database.bean.BaseBean;
import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.TableBean;
import com.txyd.database.inter.DatabaseType;
import com.txyd.database.inter.NullAble;
import com.txyd.database.inter.Primarykey;
import com.txyd.database.inter.TableType;
import com.txyd.database.utils.StringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;







/**
 * 注解处理类
 * @author Administrator
 *
 */
public class AnnotationUtil {

	/**
	 * @Description 将注解和其对应的javabean的属性放入map中
	 * @param cls
	 * @param model
	 * @param <T>
	 * @param <MODEL>
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
	 * @param clzz
	 * @param model
	 * @param jcb
	 * @param <T>
	 * @param <MODEL>
	 * @return
	 */
	private static <T extends Annotation,MODEL extends BaseBean >  String[]  annotationComments(Class<T> clzz, final MODEL model,final JavaConfigBean jcb)
	{


		{//动态添加注释
//			Map<Method, Field> mapA= AnnotationUtil.parseAnnotation2Map(clzz,model);
//			Map<String, Object> map=new HashMap<String, Object>();
//			for(Method method:mapA.keySet())
//			{
//				Field field=mapA.get(method);
//				if(method.isAnnotationPresent(AnnotationField.class))
//				{
//					AnnotationField afield=method.getAnnotation(AnnotationField.class);	
//					Class<?> cls=method.getReturnType();
//					
//					try{
//						Object fieldValue=field.get(model);
//						if(fieldValue!=null)
//						{
//							if(cls.isAssignableFrom(String.class))
//							{
//								map.put(afield.nameChinese(),fieldValue.toString());
//							}else if(cls.isAssignableFrom(DatabaseType.class))
//							{
//								map.put(afield.nameChinese(),DatabaseType.valueOf(fieldValue.toString()).getComments() );
//								
//							}else if(cls.isAssignableFrom(TableType.class))
//							{
//								map.put(afield.nameChinese(),TableType.valueOf(fieldValue.toString()).getComments() );										
//							}else if(cls.isAssignableFrom(NullAble.class))
//							{
//								map.put(afield.nameChinese(),NullAble.valueOf(fieldValue.toString()).getComments() );										
//							}else if(cls.isAssignableFrom(Primarykey.class))
//							{
//								map.put(afield.nameChinese(),Primarykey.valueOf(fieldValue.toString()).getComments() );										
//							}
//						}
//					}catch(Exception e)
//					{
//						e.printStackTrace();
//					}
//					
//				}
//			}
//			
//			String comments="";
//			if(clzz.isAssignableFrom(Table.class))
//			{
//				comments+="/**\n";
//				for(String key:map.keySet())
//				{
//					comments+=" * "+key+":"+StringUtil.newLine2Html(map.get(key).toString())+"\n";
//				}
//				comments+=" */";
//			}else if(clzz.isAssignableFrom(Column.class))
//			{
//				comments+="\t/**\n";
//				for(String key:map.keySet())
//				{
//					comments+="\t * "+key+":"+StringUtil.newLine2Html(map.get(key).toString())+"\n";
//				}
//				comments+="\t */";
//				
//			}
		}
		
		
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
				
				comments=comments.replace("{nullAble}", (columnBean.getIsNullAble()!=null&&columnBean.getIsNullAble())?"可以":"不可");
				comments=comments.replace("{columnType}", columnBean.getColumnType());
				comments=comments.replace("{defaultValue}", columnBean.getDefaultValue());
				comments=comments.replace("{dataLength}", columnBean.getDataLength());
				
				comments=comments.replace("{comments}", StringUtil.newLine2Html(columnBean.getComments()));
				comments=comments.replace("{extra}", columnBean.getExtra());
				comments=comments.replace("{columnName}", columnBean.getColumnName());
				comments=comments.replace("{dataType}", columnBean.getDataType());
				comments=comments.replace("{isPrimaryKey}", columnBean.getIsPrimaryKey()?"是":"否");
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
		String comments="";

		{//动态添加自定义注解
//			Map<Method, Field> mapA= AnnotationUtil.parseAnnotation2Map(clzz,model);
//			Map<String, Object> map=new HashMap<String, Object>();
//			for(Method method:mapA.keySet())
//			{
//				Field field=mapA.get(method);
//				if(method.isAnnotationPresent(AnnotationField.class))
//				{
//					AnnotationField af=method.getAnnotation(AnnotationField.class);
//					if(!af.require())
//					{
//						continue;
//					}
//					try{
//						Object fieldValue=field.get(model);//表bean的值
//						Class<?> cls=method.getReturnType();
//						if(fieldValue!=null)
//						{
//							if(cls.isAssignableFrom(String.class))
//							{
//								map.put(method.getName(), fieldValue.toString());
//							}else if(cls.isAssignableFrom(DatabaseType.class))
//							{
//								
//								map.put(method.getName(), DatabaseType.valueOf(fieldValue.toString()));
//							}else if(cls.isAssignableFrom(TableType.class))
//							{
//								map.put(method.getName(), TableType.valueOf(fieldValue.toString()));
//							}else if(cls.isAssignableFrom(NullAble.class))
//							{
//								map.put(method.getName(), NullAble.valueOf(fieldValue.toString()));
//							}else if(cls.isAssignableFrom(Primarykey.class))
//							{
//								map.put(method.getName(), Primarykey.valueOf(fieldValue.toString()));
//							}
//						}
//					}catch(Exception e)
//					{
//						e.printStackTrace();
//					}
//					
//				}
//			}
//			
//			String comments="";
//			if(model.getClass().isAssignableFrom(ColumnBean.class) )
//			{
//				comments+="\t@"+Column.class.getSimpleName()+"(";//列的注解
//			}else if(model.getClass().isAssignableFrom(TableBean.class) )
//			{
//				comments+="@"+Table.class.getSimpleName()+"(";	//表的注解
//				
//			}
//			for(String key:map.keySet())
//			{
//				Object object=map.get(key);
//				if(object.getClass().isAssignableFrom(String.class))
//				{		
//					comments+=key+"=\""+StringUtil.newLine2Html(map.get(key).toString())+"\",";
//				}else if(object.getClass().isAssignableFrom(DatabaseType.class))
//				{
//					comments+=key+"="+((DatabaseType)(map.get(key))).getPath()+",";
//				}else if(object.getClass().isAssignableFrom(TableType.class))
//				{
//					comments+=key+"="+((TableType)(map.get(key))).getPath()+",";
//				}else if(object.getClass().isAssignableFrom(NullAble.class))
//				{
//					comments+=key+"="+((NullAble)(map.get(key))).getPath()+",";	
//				}else if(object.getClass().isAssignableFrom(Primarykey.class))
//				{
//					comments+=key+"="+((Primarykey)(map.get(key))).getPath()+",";	
//				}
//			}
//			if(comments.contains(","))
//			{
//				comments=comments.substring(0,comments.lastIndexOf(","));//去除最后一个","			
//			}
//			comments+=" )";
//			if(model.getClass().isAssignableFrom(ColumnBean.class))
//			{
//				ColumnBean cb=(ColumnBean)model;
//				if(cb!=null&&Primarykey.valueOf(cb.getIsPrimaryKey())==Primarykey.yes)
//				{
//					comments+="\n\t@"+ID.class.getSimpleName();
//				}
//			}
		}
		
		
		{//添加jackson的注解模式
			if(model.getClass().isAssignableFrom(ColumnBean.class) )
			{
				ColumnBean cb=(ColumnBean)model;
				comments+="\t@"+"JsonProperty(\""+cb.getColumnName()+"\")";
			}else if(model.getClass().isAssignableFrom(TableBean.class) )
			{
				comments+="@JsonIgnoreProperties(ignoreUnknown = true)\n";
				comments+="@JsonInclude(JsonInclude.Include.NON_NULL)";
				
			}
			
		}
		{//静态添加自定义注解
//			if(model.getClass().isAssignableFrom(TableBean.class))
//			{
//				TableBean tableBean=(TableBean)model;
//				comments="@Table(databaseType={databaseType},tableOwner={tableOwner},tableSchema={tableSchema},tableName={tableName},comments={comments},tableType={tableType})";		
//				comments=comments.replace("{", "\"{").replace("}", "}\"");
//				comments=comments.replace("{databaseType}", tableBean.getTableSchema());
//				comments=comments.replace("{tableSchema}", tableBean.getTableSchema());
//				comments=comments.replace("{tableOwner}", tableBean.getTableOwner());
//				comments=comments.replace("{tableName}", tableBean.getTableName());
//				comments=comments.replace("{comments}", tableBean.getComments());
//				comments=comments.replace("{tableType}", tableBean.getTableType());
//			}else if(model.getClass().isAssignableFrom(ColumnBean.class) )
//			{
//				ColumnBean columnBean=(ColumnBean)model;
//				comments="\t@Column(columnName={columnName})";
//				comments=comments.replace("{", "\"{").replace("}", "}\"");
//				comments=comments.replace("{columnName}",columnBean.getColumnName());
//				
//			}
		}
		

		return comments.split("\n");
	
	}
	/**
	 * 
	 * @Description 返回ColumnBean类实例的注解
	 * @author     
	 * @param model
	 * @param jcb
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
				return(model.getIsNullAble()!=null&&model.getIsNullAble())? NullAble.yes:NullAble.no;
			}
			
			@Override
			public boolean isVirtual() {
				return this.isVirtual();
			}
			
			@Override
			public Primarykey isPrimaryKey() {
				return model.getIsPrimaryKey()? Primarykey.yes:Primarykey.no;
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
	 * @param model
	 * @param jcb
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
