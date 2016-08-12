package com.txyd.database.annotation;

import com.txyd.database.inter.NullAble;
import com.txyd.database.inter.Primarykey;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


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
 * 注解的注解
 * 为注解的元素添加中文注释
 * @author     
 *
 */
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Column {
	/**
	 * 列名
	 */
	@AnnotationField(nameChinese="列名",require=true)
	public String columnName() default "";
	/**
	 * 列类型
	 */
	@AnnotationField(nameChinese="列类型")
	public String dataType() default "";
	/**
	 * 列的数据类型的长度
	 */
	@AnnotationField(nameChinese="列的数据类型的长度")
	public String dataLength() default "";
	/**
	 * 默认值
	 */
	@AnnotationField(nameChinese="默认值")
	public String defaultValue() default "";
	/**
	 * 列注释
	 */
	@AnnotationField(nameChinese="列注释")
	public String comments() default "";
	/**
	 * 是否是主键
	 * yes：是主键
	 * no：不是主键
	 */
	@AnnotationField(nameChinese="是否是主键")
	public Primarykey isPrimaryKey() default Primarykey.no;
	/**
	 * 是否可以为空
	 * yes:可以为空
	 * no:不可以为空
	 */
	@AnnotationField(nameChinese="是否可以为NULL")
	public NullAble nullAble() default NullAble.no;
	
	

	/**
	 * 列的类型
	 * 比如：varchar(255)
	 * 比如：enum('32323','12213','1222')--mysql；
	 */
	@AnnotationField(nameChinese="列的数据类型")
	public String columnType() default "";
	/**
	 * 列的扩展
	 * 比如：auto_increment
	 * 比如：on update CURRENT_TIMESTAMP
	 */
	@AnnotationField(nameChinese="列的扩展")
	public String extra() default "";
	/**
	 * 注解的注释
	 */
	@AnnotationField(nameChinese="注解的注释")
	public String[] annotationComments() default {};
	/**
	 * 注解类属性
	 */
	@AnnotationField(nameChinese="注解类属性")
	public String[] annotationContext() default {};
	
	/**
	 * 是否是虚拟字段
	 * 所谓虚拟字段，代表数据库中没有此字段，但是表所对应的javaBean中却有，其目的仅仅是辅助其他类调用，方便使用而已；
	 * true：是虚拟字段
	 * false：不是虚拟字段
	 */
	@AnnotationField(nameChinese="是否是虚拟字段")
	public boolean isVirtual() default false;
}
