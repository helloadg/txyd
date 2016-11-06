package com.txyd.database.utils;

import com.txyd.database.bean.ColumnBean;
import com.txyd.database.bean.JavaConfigBean;
import com.txyd.database.bean.SqlDataType;
import com.txyd.database.inter.DatabaseType;


/**
 * 创建Column的对应的get方法和set方法
 * @author     
 *
 */
public class ModelUtil {
	public static ColumnBean  setFieldAndMethodOfColumn(ColumnBean column,JavaConfigBean jcb)
	{
		
		if(column==null||jcb==null)
		{
			return column ;
		}
		String methodDataType="java.lang.String";
		if(jcb.getDatabaseType()==null||jcb.getDatabaseType().trim().equals("")
				||jcb.getDatabaseType().trim().equals(DatabaseType.all.toString()))
		{
			//属性的java数据类型
			methodDataType="java.lang.String";
		}else if(jcb.getDatabaseType().trim().equals(DatabaseType.mysql.toString()))
		{
			//属性的java数据类型
			methodDataType=SqlDataType.getJavaType(column.getDataType());
		}		
		//属性名
		String javabeanFieldName=column.getJavabeanFieldName().trim();
		//get方法名
		String methodGetName="get"+StringUtil.toUpperCaseOfFirstChar(javabeanFieldName);
		//set方法名
		String methodSetName="set"+StringUtil.toUpperCaseOfFirstChar(javabeanFieldName);
		//get方法的注释
		String methodGetComments="";
		methodGetComments+="\t/**\n";
		methodGetComments+="\t * 列名称:{columnName}\n";
		methodGetComments+="\t * 描述:{comments}\n";
		methodGetComments+="\t * @Description 返回{javabeanFieldName}的值\n";
		methodGetComments+="\t * @author {author}\n";
		methodGetComments+="\t * @return\n";
		methodGetComments+="\t */";
		methodGetComments=methodGetComments.replace("{columnName}",column.getColumnName());
		methodGetComments=methodGetComments.replace("{comments}",column.getComments());
		methodGetComments=methodGetComments.replace("{javabeanFieldName}",javabeanFieldName);
		methodGetComments=methodGetComments.replace("{author}",jcb.getAuthor());
		//set方法的注释
		String methodSetComments="";
		methodSetComments+="\t/**\n";
		methodSetComments+="\t * 列名称:{columnName}\n";
		methodSetComments+="\t * 描述:{comments}\n";
		methodSetComments+="\t * @Description 设置{javabeanFieldName}的值\n";
		methodSetComments+="\t * @author {author}\n";
		methodSetComments+="\t * @param {javabeanFieldName}\n";
		methodSetComments+="\t */";
		methodSetComments=methodSetComments.replace("{columnName}",column.getColumnName());
		methodSetComments=methodSetComments.replace("{comments}",column.getComments());
		methodSetComments=methodSetComments.replace("{javabeanFieldName}",javabeanFieldName);
		methodSetComments=methodSetComments.replace("{author}",jcb.getAuthor());
		//get方法
		String methodGet="";
		methodGet+="\tpublic {methodDataType} {methodGetName}(){\n";
		methodGet+="\t	return {javabeanFieldName};\n";
		methodGet+="\t}";
		methodGet=methodGet.replace("{methodDataType}",methodDataType.replace("java.lang.", ""));
		methodGet=methodGet.replace("{methodGetName}",methodGetName);
		methodGet=methodGet.replace("{javabeanFieldName}",javabeanFieldName);
		//set方法
		String methodSet="";
		methodSet+="\tpublic void {methodSetName}({methodDataType} {javabeanFieldName}){\n";
		methodSet+="\t	this.{javabeanFieldName}={javabeanFieldName};\n";
		methodSet+="\t}";
		methodSet=methodSet.replace("{methodSetName}",methodSetName);
		methodSet=methodSet.replace("{methodDataType}",methodDataType.replace("java.lang.", ""));
		methodSet=methodSet.replace("{javabeanFieldName}",javabeanFieldName);
		//javabean 属性
		String javabeanField="";
		javabeanField+="\tprivate {methodDataType} {javabeanFieldName};";
		javabeanField=javabeanField.replace("{methodDataType}",methodDataType.replace("java.lang.", ""));
		javabeanField=javabeanField.replace("{javabeanFieldName}",javabeanFieldName);
		
//		column.setJavabeanField(javabeanField);//设置javabean 属性
		column.setJavabeanFieldDataType(methodDataType);//设置javabean属性的java数据类型
//		column.setJavabeanFieldGetMethodComments(methodGetComments);//设置get方法的注释
//		column.setJavabeanFieldSetMethodComments(methodSetComments);//设置set方法的注释
//		column.setJavabeanFieldGetMethod(methodGet);//设置get方法
//		column.setJavabeanFieldSetMethod(methodSet);//设置set方法
		return column ;
	}
	/**
	 * 
	 * @Description 获得列所对应的javabean的数据类型
	 * @author     
	 * @param column
	 * @param jcb
	 * @return
	 */
	public static String  getColumnJavaDataType(ColumnBean column,JavaConfigBean jcb)
	{
		String methodDataType="java.lang.String";
		
		if(jcb.getDatabaseType().trim().equals(DatabaseType.mysql.toString()))
		{
			//属性的java数据类型
			methodDataType=SqlDataType.getJavaType(column.getDataType());
		}		
		
		return methodDataType ;
	}


}
