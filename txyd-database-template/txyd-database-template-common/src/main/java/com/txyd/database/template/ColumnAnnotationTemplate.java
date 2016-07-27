package com.txyd.database.template;

public class ColumnAnnotationTemplate {
	private static final String template ;
	static{
		String model="";
		model+="{package}\n";
		model+="import {importInterPackage}.NullAble;\n";
		model+="import {importInterPackage}.Primarykey;\n";
		model+="import java.lang.annotation.Documented;\n";
		model+="import java.lang.annotation.ElementType;\n";
		model+="import java.lang.annotation.Inherited;\n";
		model+="import java.lang.annotation.Retention;\n";
		model+="import java.lang.annotation.RetentionPolicy;\n";
		model+="import java.lang.annotation.Target;\n";
		model+="/**\n";
		model+=" *\n";
		model+=" * 注解表的列\n";
		model+=" * @author {author}\n";
		model+=" *\n";
		model+=" */\n";
		model+="@Target({ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE})\n";
		model+="@Retention(RetentionPolicy.RUNTIME)\n";
		model+="@Documented\n";
		model+="@Inherited\n";
		model+="public @interface Column {\n";
		model+="	/**\n";
		model+="	 * 列名\n";
		model+="	 */\n";
		model+="	public String columnName() default \"\";\n";
		model+="	/**\n";
		model+="	 * 列类型\n";
		model+="	 */\n";
		model+="	public String dataType() default \"\";\n";
		model+="	/**\n";
		model+="	 * 列的数据类型的长度\n";
		model+="	 */\n";
		model+="	public String dataLength() default \"\";\n";
		model+="	/**\n";
		model+="	 * 默认值\n";
		model+="	 */\n";
		model+="	public String defaultValue() default \"\";\n";
		model+="	/**\n";
		model+="	 * 列注释\n";
		model+="	 */\n";
		model+="	public String comments() default \"\";\n";
		model+="	/**\n";
		model+="	 * 是否是主键\n";
		model+="	 * yes：是主键\n";
		model+="	 * no：不是主键\n";
		model+="	 */\n";
		model+="	public Primarykey isPrimaryKey() default Primarykey.no;\n";
		model+="	/**\n";
		model+="	 * 是否可以为空\n";
		model+="	 * yes:可以为空\n";
		model+="	 * no:不可以为空\n";
		model+="	 */\n";
		model+="	public NullAble nullAble() default NullAble.no;\n";
		model+="	/**\n";
		model+="	 * 列的类型\n";
		model+="	 * 比如：varchar(255)\n";
		model+="	 * 比如：enum('32323','12213','1222')--mysql；\n";
		model+="	 */\n";
		model+="	public String columnType() default \"\";\n";
		model+="	/**\n";
		model+="	 * 列的扩展\n";
		model+="	 * 比如：auto_increment\n";
		model+="	 * 比如：on update CURRENT_TIMESTAMP\n";
		model+="	 */\n";
		model+="	public String extra() default \"\";\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 是否是虚拟字段\n";
		model+="	 * 所谓虚拟字段，代表数据库中没有此字段，但是表所对应的javaBean中却有，其目的仅仅是辅助其他类调用，方便使用而已；\n";
		model+="	 * true：是虚拟字段\n";
		model+="	 * false：不是虚拟字段\n";
		model+="	 */\n";
		model+="	public boolean isVirtual() default false;\n";
		model+="}\n";
		template=model;
	}
	public static String getTemplate()
	{
		return template;
	}
	public static void main(String[] strs)
	{
		System.out.println(getTemplate());
	}
}
