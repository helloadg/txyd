package com.txyd.database.template;

public class TableTypeInterTemplate {
	private static final String template ;
	static{
		String model="\n";
		model+="{package}\n";
		model+="/**\n";
		model+=" * 数据库视图or数据库表\n";
		model+=" * @author {author}\n";
		model+=" *\n";
		model+=" */\n";
		model+="public enum TableType{\n";
		model+="	table(1,\"table\",\"基础表\"),\n";
		model+="	view(2,\"view\",\"视图\");\n";
		model+="	// 成员变量\n";
		model+="    private String comments;//枚举值的注释\n";
		model+="    private String name;//枚举值的自定义名\n";
		model+="    private int index;//枚举值的索引值\n";
		model+="\n";
		model+="    // 构造方法\n";
		model+="    private TableType(int index,String name,String comments) {\n";
		model+="        this.name=name;\n";
		model+="        this.comments=comments;\n";
		model+="        this.index = index;\n";
		model+="    }\n";
		model+="\n";
		model+="    /**\n";
		model+="     * \n";
		model+="     * @Description 获得枚举的名称\n";
		model+="     * @author {author}\n";
		model+="     * @return\n";
		model+="     */\n";
		model+="    @Override\n";
		model+="    public String toString() {\n";
		model+="    	return super.toString();\n";
		model+="    }\n";
		model+="    /**\n";
		model+="     * \n";
		model+="     * @Description 获得枚举的自定义名\n";
		model+="     * @author {author}\n";
		model+="     * @return\n";
		model+="     */\n";
		model+="    public String getName() {\n";
		model+="        return this.name;\n";
		model+="    }\n";
		model+="    \n";
		model+="    /**\n";
		model+="     * \n";
		model+="     * @Description 获得枚举的注释\n";
		model+="     * @author {author}\n";
		model+="     * @return\n";
		model+="     */\n";
		model+="    public String getComments() {\n";
		model+="        return this.comments;\n";
		model+="    }\n";
		model+="    /**\n";
		model+="     * \n";
		model+="     * @Description 获得枚举的索引\n";
		model+="     * @author {author}\n";
		model+="     * @return\n";
		model+="     */\n";
		model+="    public int getIndex() {\n";
		model+="        return this.index;\n";
		model+="    }\n";
		model+="    /**\n";
		model+="     * \n";
		model+="     * @Description 获得枚举值的全路径，即：\"类名.值名\"\n";
		model+="     * @author {author}\n";
		model+="     * @return\n";
		model+="     */\n";
		model+="    public String getPath()\n";
		model+="    {\n";
		model+="    	return this.getClass().getSimpleName()+\".\"+this.toString();\n";
		model+="    }\n";
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
