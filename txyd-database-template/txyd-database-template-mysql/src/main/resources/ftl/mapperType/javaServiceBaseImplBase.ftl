<#-- 类文件的package模版 -->
package ${jcb.basePackageBaseServiceImpl?string?lower_case};

import java.util.List;

public abstract class ${BaseImpl} {

    /**
     * 批量操作时，分批，每批条数
     * @author ${jcb.author}
     */
    public static final int batchNum = 1000;
	
	
    /**
     * 将大集合按照步长分段
     * @param all  大集合
     * @param stepSize 步长，每一个小集合的长度
     * @author ${jcb.author}
     */
	public static <T> List<List<T>> splite(List<T> all, int stepSize) {
		List<List<T>> list = new ArrayList<>();
		if (all == null || all.size() == 0 || stepSize < 1) {
			return list;
		}
		int size = all.size();
		int step = size / stepSize + 1;
		for (int i = 0; i < step; i++) {
			int start = i * stepSize;
			int end = start + stepSize;
			if (start < size) {
				if (end > size) {
					end = size;
				}
				List<T> item = all.subList(start, end);
				if (item != null && item.size() > 0) {
					list.add(item);
				}
			}
		}
		return list;
	}
	
	/**
	 * 将大集合按照步长分段
	 *
	 * @param all 大集合
	 * @author
	 */
	public static <T, V> List<Map<T, V>> splite(Map<T, V> all) {
		List<T> list = new ArrayList<>(all.keySet());
		List<List<T>> steps = splite(list, batchNum);
		List<Map<T, V>> result = new ArrayList<>();
		if (steps != null && steps.size() > 0) {
			for (List<T> item : steps) {
				if (item != null && item.size() > 0) {
					Map<T, V> map = new LinkedHashMap<>();
					for(T t : item){
						map.put(t,all.get(t));
					}
					result.add(map);
				}
			}
		}
		return result;
	}
	
    /**
     * 将大集合按照步长分段
     * @param all  大集合
     * @author ${jcb.author}
     */
	public static <T> List<List<T>> splite(List<T> all) {
		return splite(all,batchNum);
	}

}
