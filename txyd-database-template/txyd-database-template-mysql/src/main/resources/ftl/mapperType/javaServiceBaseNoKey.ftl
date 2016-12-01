<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseService?lower_case};

<#-- 类文件的import模版 -->
import java.util.List;
import java.util.LinkedHashMap;


public interface ${BaseNoKeyService}<T> {

	/**
	 * 保存
	 * @param object
	 * @author ${jcb.author}
	 * @return
	 */
	public int insert(T object);

	/**
	 * 保存
	 * @param object
	 * @author ${jcb.author}
	 * @return
	 */
	public int insertNotNull(T object);

	/**
	 * 保存
	 * @param list
	 * @author ${jcb.author}
	 * @return
	 */
	public int insertBatch(List<T> list);

    /**
     * 通过条件筛选修改
     * @param update
     * @param where
     * @author ${jcb.author}
     * @return
     */
    public int update(T update,T where);

	/**
	 * 删除
	 * @param object
	 * @author ${jcb.author}
	 * @return
	 */
	public int delete(T object);

	/**
	 * 根据条件查询
	 * @param object ：查询条件
	 * @param sort ：排序规则
	 * @param offset ：起始值
	 * @param limit ：返回多少条
	 * @author ${jcb.author}
	 * @return
	 */
	public List<T> select(T object, LinkedHashMap<String,String> sort, Integer offset, Integer limit);

	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @param offset ：起始值
	 * @param limit ：返回多少条
	 * @author ${jcb.author}
	 * @return
	 */
	public List<T> select(T object,Integer offset, Integer limit);
	
	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @param sort ：排序规则
	 * @author ${jcb.author}
	 * @return
	 */
	public List<T> select(T object, LinkedHashMap<String,String> sort);
	
	/**
	 * 分页查询
	 * @param object ：查询条件
	 * @author ${jcb.author}
	 * @return
	 */
	public List<T> select(T object);

	/**
	 * 获取总条数
	 * @param object
	 * @author ${jcb.author}
	 * @return
	 */
	public int selectCount(T object);

}