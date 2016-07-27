<#-- 类文件的package模版 -->
package ${jcb.basePackageModel?lower_case};

import java.util.LinkedHashMap;
/**
 * model类扩展
 * @author：${jcb.author}
 */
public abstract class  ${baseModelName}  {
	
	/**
	 * 排序
	 */
	private LinkedHashMap<String, String> sort;

	public LinkedHashMap<String, String> getSort() {
		return sort;
	}

	public void setSort(LinkedHashMap<String, String> sort) {
		this.sort = sort;
	}

}