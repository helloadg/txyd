<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseServiceImpl?string?lower_case};

import java.util.List;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import ${jcb.basePackageBaseMapper}.${BaseMapper};
import ${jcb.basePackageBaseService}.${BaseService};
import ${jcb.basePackageBaseService}.${BaseNoKeyService};

public abstract class ${BaseServiceImpl}<T, ID> extends ${BaseNoKeyServiceImpl}<T>  implements ${BaseService}<T, ID> {
	
	@Autowired
	private ${BaseMapper}<T, ID> ${baseMapper};

	@Override
	public int updateById(T t, ID id) {
		return this.${baseMapper}.updateById(t,id);
	}

    @Override
    public int updateByIds(T t, List<ID> ids) {
		List<List<ID>> list = splite(ids);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.${baseMapper}.updateByIds(t,list.get(i));
				}
			}
		}
		return count;
    }

	@Override
	public int deleteById(ID id) {
		return this.${baseMapper}.deleteById(id);
	}
	
	@Override
	public int deleteByIds(List<ID> ids) {
		List<List<ID>> list = splite(ids);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.${baseMapper}.deleteByIds(list.get(i));
				}
			}
		}
		return count;
	}

	@Override
	public T getById(ID id) {
		return this.${baseMapper}.getById(id, null);
	}

	@Override
	public T getById(ID id, Boolean isForUpdate) {
		return this.${baseMapper}.getById(id, isForUpdate);
	}
	
	@Override
	public List<T> getByIds(List<ID> ids) {
		List<T> result = new ArrayList<>();
		
		List<List<ID>> list = splite(ids);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					result.addAll(this.${baseMapper}.getByIds(list.get(i), null)) ;
				}
			}
		}
		
		return result;
	}

	@Override
	public List<T> getByIds(List<ID> ids, Boolean isForUpdate) {
		List<T> result = new ArrayList<>();
		
		List<List<ID>> list = splite(ids);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					result.addAll(this.${baseMapper}.getByIds(list.get(i), isForUpdate)) ;
				}
			}
		}
		
		return result;
	}
	
}
