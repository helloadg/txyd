package txyd.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/11/9.
 */
public class CollectionUtils {
	/**
	 * 将大集合按照步长分段
	 *
	 * @param all      大集合
	 * @param stepSize 步长，每一个小集合的长度
	 * @author
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
	
	public static <T> List<List<T>> splite2(List<T> all, int stepSize) {
		List<List<T>> list = new ArrayList<>();
		List<T> item = new ArrayList<>();
		for (T t : all) {
			if (item.size() == stepSize) {
				list.add(item);
				item = new ArrayList<>();
			}
			item.add(t);
		}
		if (item.size() > 0) {
			list.add(item);
		}
		return list;
	}
	
	public static <C extends Collection> C splite(C all, int stepSize, Class<C> cClass) {
		try {
			C result = cClass.newInstance();
			C item = cClass.newInstance();
			
			for (Object t : all) {
				if (item.size() == stepSize ) {
					result.add(item);
					item = cClass.newInstance();
				}
				item.add(t);
			}
			if (item.size() > 0) {
				result.add(item);
			}
			return result;
		} catch (Throwable throwable) {
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		{
//			ArrayList<Integer> list = Stream.iterate(1, e -> ++e).limit(13).collect(Collectors.toCollection(ArrayList::new));
//			List<List<Integer>> c = splite(list, 5, ArrayList.class);
//			System.out.println(c);
		}
		{
//			HashSet<Integer> list = Stream.iterate(1, e -> ++e).limit(13).collect(Collectors.toCollection(HashSet::new));
//			Set<Set<Integer>> c = splite(list, 5, HashSet.class);
//			System.out.println(c);
		}
		{
			LinkedHashSet<Integer> list = Stream.iterate(1, e -> ++e).limit(13).collect(Collectors.toCollection(LinkedHashSet::new));
			Set<Set<Integer>> c = splite(list, 5, LinkedHashSet.class);
			System.out.println(c);
		}
	}
	
}
