<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseServiceImpl?string?lower_case};

import java.util.List;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import ${jcb.basePackageBaseMapper}.${BaseMapper};
import ${jcb.basePackageBaseService}.${BaseService};
import ${jcb.basePackageBaseService}.${BaseKeyService};

public abstract class ${BaseServiceImpl}<T> implements ${BaseService}<T> {
	
	@Autowired
	private ${BaseMapper}<T> ${baseMapper};

	@Override
	public int insert(T object) {
		return this.${baseMapper}.insert(object);
	}
	
	@Override
	public int insertNotNull(T object) {
		return this.${baseMapper}.insertNotNull(object);
	}
	
	@Override	
	public int insertBatch(List<T> list) {
		return this.${baseMapper}.insertBatch(list);
	}

    @Override
    public int update(T update, T where) {
    	return this.${baseMapper}.update(update,where);
    }

	@Override
	public int delete(T object) {
		return this.${baseMapper}.delete(object);
	}
	
	@Override
	public List<T> select(T object, LinkedHashMap<String,String> sort, Integer offset,  Integer limit) {
		return this.${baseMapper}.select(object,sort,  offset,  limit);
	}

	@Override
	public List<T> select(T object, Integer offset, Integer limit) {
		return this.${baseMapper}.select(object,null,  offset,  limit);
	}
	
	@Override
	public List<T> select(T object, LinkedHashMap<String,String> sort) {
		return this.${baseMapper}.select(object,sort,  null,  null);
	}
	
	@Override
	public List<T> select(T object) {
		return this.${baseMapper}.select(object,null,  null,  null);
	}
	
	@Override
	public int selectCount(T object) {
		return this.${baseMapper}.selectCount(object);
	}

}
