<#-- basemapper名称 -->
<#assign BaseMapper = "BaseMapper" />
<#assign basePackageMapper = jcb.basePackageMapper?string?lower_case  />
<#if (basePackageMapper??) && !(basePackageMapper?string?trim?length == 0) >
	<#assign basePackageMapper = basePackageMapper?string?trim />
	<#assign point = "." />
	<#assign lastIndexOfPoint = basePackageMapper?string?last_index_of(point) +1  />
	<#assign lastWord = basePackageMapper?string?substring(lastIndexOfPoint) />
	<#assign BaseMapper = "Base" +  StringUtil.toUpperCaseOfFirstChar( lastWord ) />
</#if>
<#-- 类文件的package模版 -->
package ${jcb.basePackageMapper?lower_case};
<#-- 类文件的import模版 -->
import java.util.List;
import java.util.LinkedHashMap;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @author ${jcb.author}
 * @param <T>
 * @param <ID>
 */
public interface ${BaseMapper}<T,ID> {
	public T getById(ID id);
	public List<T> getByIds(List<ID> ids);
<#--	public List<T> select(T whereAlias);-->
	public Integer selectCount(@Param("${jcb.whereAlias}") T whereAlias);
	public List<T> select(@Param("${jcb.whereAlias}") T whereAlias,@Param("sort") LinkedHashMap<String,String> sort, @Param("offset") Integer offset,  @Param("limit") Integer limit);

	public Integer insert(T t);
	public Integer insertNotNull(T t);
	public Integer insertBatch(List<T> list);

    public Integer update(@Param("${jcb.tableAlias}") T updateAlias,@Param("${jcb.whereAlias}") T whereAlias);
	public Integer updateById(@Param("${jcb.tableAlias}") T updateAlias, @Param("id") ID id);
    public Integer updateByIds(@Param("${jcb.tableAlias}") T updateAlias, @Param("ids")List<ID> ids);

	public Integer delete(T whereAlias);
	public Integer deleteById(ID id);
	public Integer deleteByIds(List<ID> list);
}