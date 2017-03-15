<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseMapper?lower_case};

<#-- 类文件的import模版 -->
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * @author ${jcb.author}
 * @param <T>
 */
public interface ${BaseNoKeyMapper}<T> {
<#--	List<T> select(T whereAlias);-->
	Integer selectCount(@Param("${jcb.whereAlias}") T whereAlias);
	List<T> select(@Param("${jcb.whereAlias}") T whereAlias, @Param("sort") LinkedHashMap<String,String> sort, @Param("offset") Integer offset, @Param("limit") Integer limit, @Param("isForUpdate") Boolean isForUpdate);

	Integer insert(T t);
	Integer insertNotNull(T t);
	Integer insertBatch(List<T> list);
    Integer insertNotExists(@Param("map") Map<T, T> insert);

    Integer update(@Param("${jcb.tableAlias}") T updateAlias,@Param("${jcb.whereAlias}") T whereAlias);

	Integer delete(T whereAlias);
}