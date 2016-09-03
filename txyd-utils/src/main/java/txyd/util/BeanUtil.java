package txyd.util;

//import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class BeanUtil {
//	private static Logger logger = Logger.getLogger(BeanUtil.class);



    /**
     * 判断是否可以转化
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> boolean is(Class<T> tClass  ){
        if(tClass.isPrimitive()||void.class==tClass ){

        }

        Integer i=new Integer(100);
        return true ; 

    }

	public static <T> void copy(T source,T desc){
		if(source==null||desc==null){
			return;
		}

		if(source instanceof Collection && desc instanceof Collection){
			Collection sourceCol=(Collection)source;
			Collection descCol=(Collection)desc;
			descCol.addAll(sourceCol);
		}else if(source instanceof Object[] && desc instanceof Object[]){
			Object[] sourceCol=(Object[])source;
			Object[] descCol=(Object[])desc;
//			descCol= Arrays.copyOf(sourceCol,descCol.length);
//			Arrays.copyOf()
			
			
		}
	}
	
	public static void main(String[] args) throws Exception {
        {
//            System.out.println(Integer.class.isPrimitive());
//            System.out.println(int.class.isPrimitive());
//            System.out.println(int.class.isAssignableFrom(Integer.class)   );
        }
        {
//            Constructor<Integer> constructor=Integer.class.getConstructor(new Class[]{int.class});
//            constructor.setAccessible(true);
//            Integer integer=constructor.newInstance(100);
//            System.out.println(integer);
        }
		{
			List<Long> source= new ArrayList<Long>(){{add(100000L);add(2L);add(3L);add(3L);}};

			source.forEach(e->{
                System.out.println(e.TYPE);
            });
//
////		List<Integer> desc= new ArrayList<>();
//			Set<String> desc=new HashSet<>();
//			desc.add(new String("ddd"));
//			copy(source,desc);
//			System.out.println(desc);
		}
		
		{
//			Integer[] arr=new Integer[]{1,2,3,4,5,6};
//			Integer[] brr=new Integer[10];
//			List<Integer>[] list = new List[100];
//			System.out.println(arr);
		}



//		Map<String,Object> map =new HashMap<>();
//		map.put("id",111);
//		WidgetEntity entity=new WidgetEntity();
//
//		try {
//			BeanUtils.copyProperties(map,entity);
//			System.out.println(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	
}
