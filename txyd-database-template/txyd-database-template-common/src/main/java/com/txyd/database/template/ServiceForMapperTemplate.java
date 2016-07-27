package com.txyd.database.template;

import java.util.LinkedHashMap;

/**
 * mapper对应的service模版
 * @author Administrator
 *
 */
public class ServiceForMapperTemplate {
	private static final String template ;
	static{
		String model="";
		model+="{package}\n";
		model+="\n";
		model+="import java.util.List;\n";
		model+="import java.util.LinkedHashMap;\n";
		model+="\n";
		model+="public interface {BaseService}<T,ID> {\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 保存\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public int insert(T object);\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 保存\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public int insertNotNull(T object);\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 保存\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public int insertBatch(List<T> list);\n";
		model+="	\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 修改\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public int updateById(T t, ID id);\n";
		model+="	\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 删除\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public int delete(T object);\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 删除\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public int deleteById(ID id);\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 删除\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public int deleteByIds(List<ID> list);\n";
		model+="	/**\n";
		model+="	 * \n";
		model+="	 * @param id\n";
		model+="	 * @param cla\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public T getById(ID id);\n";
		model+="	/**\n";
		model+="	 * \n";
		model+="	 * @param id\n";
		model+="	 * @param cla\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public List<T> getByIds(List<ID> id);\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 根据条件查询\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public List<T> select(T object, LinkedHashMap<String,String> sort,  Integer offset,   Integer limit);\n";
//		model+="	\n";
//		model+="	/**\n";
//		model+="	 * 分页查询\n";
//		model+="	 * @param object\n";
//		model+="	 * @param offset\n";
//		model+="	 * @param limit\n";
//		model+="	 * @author {author}\n";
//		model+="	 * @return\n";
//		model+="	 */\n";
//		model+="	public List<T> select(T object,int offset, int limit);\n";
		model+="	\n";
		model+="	/**\n";
		model+="	 * 获取总条数\n";
		model+="	 * @param object\n";
		model+="	 * @author {author}\n";
		model+="	 * @return\n";
		model+="	 */\n";
		model+="	public  int selectCount(T object);\n";
		model+="\n";
		model+="}";
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
