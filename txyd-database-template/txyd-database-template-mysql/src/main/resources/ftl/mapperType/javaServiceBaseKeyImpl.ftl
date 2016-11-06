<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseServiceImpl?string?lower_case};

import java.util.List;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import ${jcb.basePackageBaseMapper}.${BaseKeyMapper};
import ${jcb.basePackageBaseMapper}.${BaseMapper};
import ${jcb.basePackageBaseService}.${BaseKeyService};

public abstract class ${BaseKeyServiceImpl}<T,ID> implements ${BaseKeyService}<T,ID>{

	@Autowired
	private ${BaseKeyMapper}<T, ID> ${baseKeyMapper};

	@Override
	public int updateById(T t, ID id) {
		return this.${baseKeyMapper}.updateById(t,id);
	}

    @Override
    public int updateByIds(T t, List<ID> ids) {
    	return this.${baseKeyMapper}.updateByIds(t,ids);
    }

	@Override
	public int deleteById(ID id) {
		return this.${baseKeyMapper}.deleteById(id);
	}
	
	@Override
	public int deleteByIds(List<ID> list) {;
		return this.${baseKeyMapper}.deleteByIds(list);
	}

	@Override
	public T getById(ID  id) {
		return this.${baseKeyMapper}.getById(id);
	}
	
	@Override
	public List<T> getByIds(List<ID>  ids) {
		return this.${baseKeyMapper}.getByIds(ids);
	}
	
}
