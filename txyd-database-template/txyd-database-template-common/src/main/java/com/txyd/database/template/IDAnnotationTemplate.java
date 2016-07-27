package com.txyd.database.template;

public class IDAnnotationTemplate {
	private static final String template ;
	static{
		String model="";
		model+="{package}\n";
		model+="\n";
		model+="import java.lang.annotation.Documented;\n";
		model+="import java.lang.annotation.ElementType;\n";
		model+="import java.lang.annotation.Inherited;\n";
		model+="import java.lang.annotation.Retention;\n";
		model+="import java.lang.annotation.RetentionPolicy;\n";
		model+="import java.lang.annotation.Target;\n";
		model+="\n";
		model+="/**\n";
		model+=" * 表的主键\n";
		model+=" * @author     \n";
		model+=" *\n";
		model+=" */\n";
		model+="@Target({ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE})\n";
		model+="@Retention(RetentionPolicy.RUNTIME)\n";
		model+="@Documented\n";
		model+="@Inherited\n";
		model+="public @interface ID {\n";
		model+="	/**\n";
		model+="	 * 是否是主键\n";
		model+="	 * true：是主键\n";
		model+="	 * false：不是主键\n";
		model+="	 */\n";
		model+="	public boolean value() default true;\n";
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
