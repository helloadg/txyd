package com.txyd.database.template;

import java.util.List;

public class ServiceImplTemplate {
	private static final String DaoImpl ;
	static{
		String model="";
		model+="{package}\n";
		model+="\n";
		model+="import java.util.List;\n";
		model+="import org.springframework.beans.factory.annotation.Autowired;\n";
		model+="{importBaseDao}\n";
		model+="{importBaseService}\n";
		model+="\n";
		model+="public class BaseServiceImpl<T,ID> implements BaseService<T,ID>{\n";
		model+="	\n";
		model+="	@Autowired\n";
		model+="	private BaseDao<T, ID> baseDao;\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int insert(T object) {\n";
		model+="		return this.baseDao.insert(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int insertNotNull(T object) {\n";
		model+="		return this.baseDao.insertNotNull(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int insertBatch(Class<T>cls,List<T> list) {\n";
		model+="		return this.baseDao.insertBatch(cls, list);\n";
		model+="	}\n";
		model+="	\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int updateById(ID id) {\n";
		model+="		return this.baseDao.updateById(id);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int delete(T object) {\n";
		model+="		return this.baseDao.delete(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int deleteById( Class<T> cls,ID id) {\n";
		model+="		return this.baseDao.deleteById(cls,id);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public List<T> select(T object) {\n";
		model+="		return this.baseDao.select(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public List<T> select(T object, int offset, int limit) {\n";
		model+="		return this.baseDao.select(object,offset,limit);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public int selectCount(T object) {\n";
		model+="		return this.baseDao.selectCount(object);\n";
		model+="	}\n";
		model+="	\n";
		model+="	@Override\n";
		model+="	public T getById( Class<T> cls,ID  id) {\n";
		model+="		return this.baseDao.getById(cls,id);\n";
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

