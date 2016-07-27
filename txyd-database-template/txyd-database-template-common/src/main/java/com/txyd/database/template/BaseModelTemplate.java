package com.txyd.database.template;

public class BaseModelTemplate {
	private static final String daoTemplate ;
	static{
		String model="";
		model+="{package}\n\n";
		model+="import java.util.LinkedHashMap;\n";
		model+="\n";
		model+="// model类扩展\n";
		model+="// 作者：{author}\n";
		model+="public abstract class BaseModel {\n";
		model+="	/**\n";
		model+="	 * 排序\n";
		model+="	 */\n";
		model+="	private LinkedHashMap<String, String> sort;\n";
		model+="\n";
		model+="	public LinkedHashMap<String, String> getSort() {\n";
		model+="		return sort;\n";
		model+="	}\n";
		model+="\n";
		model+="	public void setSort(LinkedHashMap<String, String> sort) {\n";
		model+="		this.sort = sort;\n";
		model+="	}\n";
		model+="\n";
		model+="}\n";

		
		daoTemplate=model;
	}
	public static String getTemplate()
	{
		return daoTemplate;
	}
	public static void main(String[] strs)
	{
		System.out.println(getTemplate());
	}
}
