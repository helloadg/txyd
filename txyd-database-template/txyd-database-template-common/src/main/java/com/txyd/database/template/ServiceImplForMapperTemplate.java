package com.txyd.database.template;

import java.util.LinkedHashMap;

/**
 * 与mapper所相对应的serviceImpl的模版
 * @author Administrator
 *
 */
public class ServiceImplForMapperTemplate {
	private static final String DaoImpl ;
	static{
		String model="";
		model+="{package}\n";
		model+="\n";
		model+="import java.util.List;\n";
		model+="import java.util.LinkedHashMap;\n";
		model+="import org.springframework.beans.factory.annotation.Autowired;\n";
		model+="{importBaseMapper}\n";
		model+="{importBaseService}\n";
		model+="\n";
		model+="public class {BaseServiceImpl}<T,ID> implements {BaseService}<T,ID>{\n";
		model+="	\n";
		model+="	@Autowired\n";
		model+="	private {BaseMapper}<T, ID> {baseMapper};\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int insert(T object) {\n";
		model+="		return this.{baseMapper}.insert(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int insertNotNull(T object) {\n";
		model+="		return this.{baseMapper}.insertNotNull(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int insertBatch(List<T> list) {\n";
		model+="		return this.{baseMapper}.insertBatch(list);\n";
		model+="	}\n";
		model+="	\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int updateById(T t, ID id) {\n";
		model+="		return this.{baseMapper}.updateById(t,id);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int delete(T object) {\n";
		model+="		return this.{baseMapper}.delete(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int deleteById(ID id) {\n";
		model+="		return this.{baseMapper}.deleteById(id);\n";
		model+="	}\n";
		model+="	\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int deleteByIds(List<ID> list) {\n";
		model+="		return this.{baseMapper}.deleteByIds(list);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public List<T> select(T object,  LinkedHashMap<String,String> sort,  Integer offset,   Integer limit) {\n";
		model+="		return this.{baseMapper}.select(object,sort,  offset,  limit);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int selectCount(T object) {\n";
		model+="		return this.{baseMapper}.selectCount(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public T getById(ID  id) {\n";
		model+="		return this.{baseMapper}.getById(id);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public List<T> getByIds(List<ID>  ids) {\n";
		model+="		return this.{baseMapper}.getByIds(ids);\n";
		model+="	}\n";
		model+="	\n";
		model+="}\n";
		
		DaoImpl=model;
	}
	public static String getTemplate()
	{
		return DaoImpl;
	}
	public static void main(String[] strs)
	{
		System.out.println(getTemplate());
	}
}

