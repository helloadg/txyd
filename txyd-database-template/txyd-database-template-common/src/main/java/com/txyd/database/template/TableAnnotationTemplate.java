package com.txyd.database.template;

public class TableAnnotationTemplate {
	private static final String template ;
	static{
		String model="";
		model+="{package}\n";
		model+="\n";
		model+="import {importInterPackage}.DatabaseType;\n";
		model+="import {importInterPackage}.TableType;\n";
		model+="\n";
		model+="import java.lang.annotation.Documented;\n";
		model+="import java.lang.annotation.ElementType;\n";
		model+="import java.lang.annotation.Inherited;\n";
		model+="import java.lang.annotation.Retention;\n";
		model+="import java.lang.annotation.RetentionPolicy;\n";
		model+="import java.lang.annotation.Target;\n";
		model+="\n";
		model+="/**\n";
		model+=" * 注解数据库的表所对应的类\n";
		model+=" * @author {author}\n";
		model+=" *\n";
		model+=" */\n";
		model+="@Target({ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE})\n";
		model+="@Retention(RetentionPolicy.RUNTIME)\n";
		model+="@Documented\n";
		model+="@Inherited\n";
		model+="public @interface Table {\n";
		model+="\n";
		model+="	/**\n";
		model+="	 * 数据库类型\n";
		model+="	 */\n";
		model+="	public DatabaseType databaseType() default DatabaseType.all;\n";
		model+="	/**\n";
		model+="	 * 数据库所属用户\n";
		model+="	 */\n";
		model+="	public String tableOwner() default \"\";\n";
		model+="	/**\n";
		model+="	 * 数据库schema\n";
		model+="	 * oracle就是数据库用户\n";
		model+="	 * mysql：schema\n";
		model+="	 */\n";
		model+="	public String tableSchema() default \"\";\n";
		model+="	/**\n";
		model+="	 * 表名\n";
		model+="	 */\n";
		model+="	public String tableName() default \"\";\n";
		model+="	/**\n";
		model+="	 * 表注释\n";
		model+="	 */\n";
		model+="	public String comments() default \"\";\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 表类型\n";
		model+="	 * view：视图类型\n";
		model+="	 * table：基本表\n";
		model+="	 */\n";
		model+="	public TableType tableType()default TableType.table;\n";
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
