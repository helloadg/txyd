<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseServiceImpl?string?lower_case};

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import ${jcb.basePackageBaseMapper}.${BaseMapper};
import ${jcb.basePackageBaseService}.${BaseService};
import ${jcb.basePackageBaseService}.${BaseNoKeyService};

public abstract class ${BaseNoKeyServiceImpl}<T> extends ${BaseImpl} implements ${BaseNoKeyService}<T> {
	
	@Autowired
	private ${BaseNoKeyMapper}<T> ${baseNoKeyMapper};

	@Override
	public int insert(T object) {
		return this.${baseNoKeyMapper}.insert(object);
	}
	
	@Override
	public int insertNotNull(T object) {
		return this.${baseNoKeyMapper}.insertNotNull(object);
	}
	
	@Override	
	public int insertBatch(List<T> tList) {
		List<List<T>> list = splite(tList);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.${baseNoKeyMapper}.insertBatch(list.get(i));
				}
			}
		}
		return count;
	}
	
	@Override
	public Integer insertNotExists( Map<T, T> insert) {
		List<Map<T, T>> list = splite(insert);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null && list.get(i).size() > 0) {
					count += this.${baseNoKeyMapper}.insertNotExists(list.get(i));
				}
			}
		}
		return count;
	}

    @Override
    public int update(T update, T where) {
    	return this.${baseNoKeyMapper}.update(update,where);
    }

	@Override
	public int delete(T object) {
		return this.${baseNoKeyMapper}.delete(object);
	}
	
	@Override
	public List<T> select(T object, LinkedHashMap<String,String> sort, Integer offset,  Integer limit) {
		return this.${baseNoKeyMapper}.select(object,sort,  offset,  limit, null);
	}

	@Override
	public List<T> select(T object, LinkedHashMap<String,String> sort, Integer offset,  Integer limit ,Boolean isForUpdate) {
		return this.${baseNoKeyMapper}.select(object, sort, offset, limit, isForUpdate);
	}

	@Override
	public List<T> select(T object, Integer offset, Integer limit) {
		return this.${baseNoKeyMapper}.select(object, null, offset, limit, null);
	}
	@Override
	public List<T> select(T object, Integer offset, Integer limit, Boolean isForUpdate) {
		return this.${baseNoKeyMapper}.select(object, null, offset, limit, isForUpdate);
	}
	
	@Override
	public List<T> select(T object, LinkedHashMap<String,String> sort) {
		return this.${baseNoKeyMapper}.select(object, sort, null, null, null);
	}

	@Override
	public List<T> select(T object, LinkedHashMap<String,String> sort, Boolean isForUpdate) {
		return this.${baseNoKeyMapper}.select(object, sort, null, null, isForUpdate);
	}

	@Override
	public List<T> select(T object) {
		return this.${baseNoKeyMapper}.select(object, null, null, null, null);
	}

	@Override
	public List<T> select(T object, Boolean isForUpdate) {
		return this.${baseNoKeyMapper}.select(object, null, null, null, isForUpdate);
	}

	@Override
	public int selectCount(T object) {
		return this.${baseNoKeyMapper}.selectCount(object);
	}

}
