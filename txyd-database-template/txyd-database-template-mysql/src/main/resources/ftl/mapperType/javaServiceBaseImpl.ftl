<#-- 类文件的package模版 -->
package ${jcb.basePackageServiceImpl?string?lower_case};
<#-- 类名称 -->
<#assign baseServiceClassName = "BaseService" />
<#assign basePackageService = jcb.basePackageService?lower_case/>

<#if (basePackageService??) && ! (basePackageService?string?trim?length == 0)    >
	<#assign basePackageService = basePackageService?string?trim />
	<#assign point =  "." />
	<#assign lastIndexOfPoint =  basePackageService?string?trim?last_index_of(point) />
	<#assign lastIndexOfPoint = lastIndexOfPoint +1  />
	<#assign lastWord = basePackageService?string?substring(lastIndexOfPoint)  />
	<#assign baseServiceClassName = "Base"+StringUtil.toUpperCaseOfFirstChar(lastWord)  />	
</#if>

<#assign baseServiceImplClassName = baseServiceClassName + "Impl" />
<#-- basemapper名称 -->
<#assign BaseMapper = "BaseMapper" />
<#assign baseMapper = "baseMapper" />
<#assign basePackageMapper = jcb.basePackageMapper?string?lower_case  />
<#if (basePackageMapper??) && !(basePackageMapper?string?trim?length == 0) >
	<#assign basePackageMapper = basePackageMapper?string?trim />
	<#assign point = "." />
	<#assign lastIndexOfPoint = basePackageMapper?string?last_index_of(point) +1  />
	<#assign lastWord = basePackageMapper?string?substring(lastIndexOfPoint) />
	<#assign BaseMapper = "Base" +  StringUtil.toUpperCaseOfFirstChar( lastWord ) />
	<#assign baseMapper = "base" +  StringUtil.toUpperCaseOfFirstChar( lastWord )  />

</#if>

import java.util.List;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import ${jcb.basePackageMapper}.${BaseMapper};
import ${jcb.basePackageService}.${baseServiceClassName};

public class ${baseServiceImplClassName}<T,ID> implements ${baseServiceClassName}<T,ID>{
	
	@Autowired
	private ${BaseMapper}<T, ID> ${baseMapper};
	
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
	public int updateById(T t, ID id) {
		return this.${baseMapper}.updateById(t,id);
	}
	@Override
	public int delete(T object) {
		return this.${baseMapper}.delete(object);
	}
	
	@Override
	public int deleteById(ID id) {
		return this.${baseMapper}.deleteById(id);
	}
	
	@Override
	public int deleteByIds(List<ID> list) {;
		return this.${baseMapper}.deleteByIds(list);
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
	
	@Override
	public T getById(ID  id) {
		return this.${baseMapper}.getById(id);
	}
	
	@Override
	public List<T> getByIds(List<ID>  ids) {
		return this.${baseMapper}.getByIds(ids);
	}
	
}
