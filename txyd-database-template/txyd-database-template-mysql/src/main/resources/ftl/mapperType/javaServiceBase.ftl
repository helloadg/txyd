<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseService?lower_case};

<#-- 类文件的import模版 -->
import java.util.List;
import java.util.LinkedHashMap;


public interface ${BaseService}<T,ID>  extends ${BaseNoKeyService}<T> {

	/**
	 * 通过id单条修改
	 * @param t
	 * @param id
	 * @author ${jcb.author}
	 * @return
	 */
	public int updateById(T t, ID id);

    /**
     * 通过ids批量修改
     * @param t
     * @param ids
     * @author ${jcb.author}
     * @return
     */
    public int updateByIds(T t, List<ID> ids);

	/**
	 * 删除
	 * @param id
	 * @author ${jcb.author}
	 * @return
	 */
	public int deleteById(ID id);

	/**
	 * 删除
	 * @param ids
	 * @author ${jcb.author}
	 * @return
	 */
	public int deleteByIds(List<ID> ids);

	/**
	 * 
	 * @param id
	 * @author ${jcb.author}
	 * @return
	 */
	public T getById(ID id);

	/**
	 * 
	 * @param ids
	 * @author ${jcb.author}
	 * @return
	 */
	public List<T> getByIds(List<ID> ids);

}