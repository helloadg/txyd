<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseServiceImpl?string?lower_case};

import java.util.List;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import ${jcb.basePackageBaseMapper}.${BaseMapper};
import ${jcb.basePackageBaseService}.${BaseService};
import ${jcb.basePackageBaseService}.${BaseKeyService};

public abstract class ${BaseWithKeyServiceImpl}<T,ID> extends ${BaseServiceImpl}<T>  implements ${BaseService}<T>, ${BaseKeyService}<T,ID> {

	
	@Autowired
	private ${BaseKeyMapper}<T, ID> ${baseKeyMapper};


	@Override
	public int updateById(T t, ID id) {
		return this.${baseKeyMapper}.updateById(t,id);
	}

    @Override
    public int updateByIds(T t, List<ID> ids) {
		List<List<ID>> list = splite(ids);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.${baseKeyMapper}.updateByIds(t,list.get(i));
				}
			}
		}
		return count;
    }

	@Override
	public int deleteById(ID id) {
		return this.${baseKeyMapper}.deleteById(id);
	}
	
	@Override
	public int deleteByIds(List<ID> ids) {
		List<List<ID>> list = splite(ids);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					count += this.${baseKeyMapper}.deleteByIds(list.get(i));
				}
			}
		}
		return count;
	}

	@Override
	public T getById(ID  id) {
		return this.${baseKeyMapper}.getById(id);
	}
	
	@Override
	public List<T> getByIds(List<ID>  ids) {
		List<T> result = new ArrayList<>();
		
		List<List<ID>> list = splite(ids);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i) != null && list.get(i).size() > 0){
					result.addAll(this.${baseKeyMapper}.getByIds(list.get(i))) ;
				}
			}
		}
		
		return result;
	}
	
}
