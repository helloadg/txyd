package com.txyd.database.annotation;

import java.lang.annotation.*;
import com.txyd.database.inter.DatabaseType;
import com.txyd.database.inter.TableType;

/**
 * 元注解
 * 		元注解是指注解的注解。包括  @Retention @Target @Document @Inherited四种。
 * @Retention: 定义注解的保留策略
 *		@Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
 *  	@Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
 *  	@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * @Target：定义注解的作用目标
 * 		@Target(ElementType.TYPE)   //接口、类、枚举、注解
 * 		@Target(ElementType.FIELD) //字段、枚举的常量
 * 		@Target(ElementType.METHOD) //方法
 *		@Target(ElementType.PARAMETER) //方法参数
 * 		@Target(ElementType.CONSTRUCTOR)  //构造函数
 * 		@Target(ElementType.LOCAL_VARIABLE)//局部变量 * 
 * 		@Target(ElementType.ANNOTATION_TYPE)//注解
 *		@Target(ElementType.PACKAGE) ///包   
 * @Document：说明该注解将被包含在javadoc中
 * @Inherited：说明子类可以继承父类中的该注解
 */
/**
 * 为pojo添加注解
 * 用处：filed、method、constructor、parameter
 * 运行时
 * 注释
 * 可继承
 * @author     
 *
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Table {

	/**
	 * 数据库类型
	 */
	@AnnotationField(nameChinese="数据库类型")
	public DatabaseType databaseType() default DatabaseType.all;
	/**
	 * 数据库所属用户
	 */
	@AnnotationField(nameChinese="表所属用户")
	public String tableOwner() default "";
	/**
	 * 数据库schema
	 * oracle就是数据库用户
	 * mysql：schema
	 */
	@AnnotationField(nameChinese="表所属schema")
	public String tableSchema() default "";
	/**
	 * 表名
	 */
	@AnnotationField(nameChinese="表名称",require=true)
	public String tableName() default "";
	/**
	 * 表注释
	 */
	@AnnotationField(nameChinese="表注释")
	public String comments() default "";
	
	/**
	 * 表类型
	 * view：视图类型
	 * table：基本表
	 */
	@AnnotationField(nameChinese="表类型")
	public TableType tableType()default TableType.table;
	/**
	 * 注解的注释
	 */
	@AnnotationField(nameChinese="类的注解的注释")
	public String[] annotationComments() default {};
	/**
	 * 类的注解
	 */
	@AnnotationField(nameChinese="类的注解文本")
	public String[] annotationContext() default {};
	
//	public List<String> annotationContext1() default null;//此句报错
	


}
