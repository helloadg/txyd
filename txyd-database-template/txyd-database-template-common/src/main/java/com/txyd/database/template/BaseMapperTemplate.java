package com.txyd.database.template;

public class BaseMapperTemplate {
	private static final String daoTemplate ;
	static{
		String model="";
		model+="{package}\n";
		model+="\n";
		model+="import java.util.List;\n";
		model+="import java.util.LinkedHashMap;\n";
		model+="import org.apache.ibatis.annotations.Param;\n";
		model+="/**\n";
		model+=" * \n";
		model+=" * @author {author}\n";
		model+=" * @param <T>\n";
		model+=" * @param <ID>\n";
		model+=" */\n";
		model+="public interface {BaseMapper}<T,ID> {\n";
		model+="	public T getById(ID id);\n";
		model+="	public List<T> getByIds(List<ID> ids);\n";
//		model+="	public List<T> select(T t);\n";
		model+="	public Integer selectCount(@Param(\"{tableAlias}\") T t);\n";
		model+="	public List<T> select(@Param(\"{tableAlias}\") T {tableAlias},@Param(\"sort\") LinkedHashMap<String,String> sort, @Param(\"offset\") Integer offset,  @Param(\"limit\") Integer limit);\n";
		model+="	\n";
		model+="	public Integer insert(T t);\n";
		model+="	public Integer insertNotNull(T t);\n";
		model+="	public Integer insertBatch(List<T> list);\n";
		model+="	\n";
		model+="	public Integer updateById(@Param(\"{tableAlias}\") T {tableAlias}, @Param(\"id\") ID id);\n";
		model+="	\n";
		model+="	public Integer delete(T t);\n";
		model+="	public Integer deleteById(ID id);\n";
		model+="	public Integer deleteByIds(List<ID> list);\n";
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
