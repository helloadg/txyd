package txyd.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/7.
 */
public class JavabeanUtil {
//	public static void test() throws Exception {
//		Class<?> tClass=UserEntity.class;
//		BeanInfo info= Introspector.getBeanInfo(tClass);
//		PropertyDescriptor[] props=info.getPropertyDescriptors();
//		Arrays.stream(props).limit(2).forEach(e-> {
//
//			System.out.println(e.getName());
//			System.out.println("\t"+e.getPropertyType().getPackage());
//			System.out.println("\t"+e.getPropertyType().getName());
//			System.out.println("\t"+e.getPropertyType().isAssignableFrom(Date.class));
//			System.out.println("\t"+e.getPropertyType());
//			System.out.println("\t"+e.getReadMethod());
//			System.out.println("\t"+e.getWriteMethod());
//			System.out.println("\t"+e.getWriteMethod().getName());
//		});
//	}
	public static <T,U> void copy(Class<T> sourceClass,Class<U> descClass) throws Exception {
		String method ="\tpublic static {descClassName} copy({sourceClassName} source , {descClassName} desc ) {\n {methodItems} \n\t}";
		String methodItem="\t\tdesc.{writeMethodName}(source.{getMethodName}());\n";
		
		String sourceClassName=sourceClass.getCanonicalName().substring(sourceClass.getCanonicalName().lastIndexOf(".")+1);
		String descClassName=descClass.getCanonicalName().substring(descClass.getCanonicalName().lastIndexOf(".")+1);
		StringBuilder methodItems=new StringBuilder("");
		
		PropertyDescriptor[] sourceProperties= Introspector.getBeanInfo(sourceClass).getPropertyDescriptors();
		PropertyDescriptor[] descProperties= Introspector.getBeanInfo(descClass).getPropertyDescriptors();
		Arrays.stream(sourceProperties).forEach(source-> {
			Arrays.stream(descProperties).forEach(desc->{
				if(source!=null&&desc!=null
						&&source.getName().equals(desc.getName())
						&&source.getPropertyType().isAssignableFrom(desc.getPropertyType())
						&&source.getReadMethod()!=null
						&&desc.getWriteMethod()!=null
						){//属性名一致
					methodItems.append(methodItem.replace("{writeMethodName}",desc.getWriteMethod().getName())
							.replace("{getMethodName}",source.getReadMethod().getName()));
				}
			});
		});
		String result=method.replace("{sourceClassName}",sourceClassName)
				.replace("{descClassName}",descClassName)
				.replace("{methodItems}",methodItems.toString());
		System.out.println(result);
	}
}
