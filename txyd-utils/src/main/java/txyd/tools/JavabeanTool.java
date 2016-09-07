package txyd.tools;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Administrator on 2016/9/7.
 */
public class JavabeanTool {
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
	public static <T,U> void copy(Class<T> sourceClass, Class<U> descClass, Class<? extends Collection> sCollection) throws Exception {
		String method = "\tpublic static {collectionClassName}<{descClassName}> copy({collectionClassName}<{sourceClassName}> source){\n"
				+"\t\t{collectionClassName}<{descClassName}> desc = new {collectionClassName}<>();\n"
				+"\t\tfor({sourceClassName} item : source ){\n"
				+"\t\t\t{descClassName} temp = copy(item);\n "
				+"\t\t\tdesc.add(temp);\n "
				+"\t\t}\n "
				+"\t\treturn desc; \n "
				+"\t}";
		String sourceClassName=sourceClass.getSimpleName();
		String descClassName=descClass.getSimpleName();
		String collectionClassName=sCollection.getSimpleName();
		String resultCollection=method.replace("{sourceClassName}",sourceClassName)
				.replace("{descClassName}",descClassName)
				.replace("{collectionClassName}",collectionClassName);
		
		
		System.out.println(resultCollection);
		
		String resultBean=copy(  sourceClass,  descClass);
//		System.out.println(resultBean);
		
	}
	public static <T,U> String copy(Class<T> sourceClass,Class<U> descClass) throws Exception {
		String method ="\tpublic static {descClassName} copy({sourceClassName} source ) {\n"
				+"\t\t{descClassName} desc = new {descClassName}();\n"
				+"{methodItems} "
				+"\t\treturn desc; \n "
				+"\t}";
		String methodItem="\t\tdesc.{writeMethodName}(source.{getMethodName}());\n";
		
		String sourceClassName=sourceClass.getSimpleName();
		String descClassName=descClass.getSimpleName();
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
		return result;
	}
	
	public static void main(String[] args) throws Exception {
//		Class<?> tClass=WidgetEntity.class;
//		Class<?> dClass=WidgetParam.class;
//		Class<ArrayList> cClass=ArrayList.class;
//		copy(tClass,dClass);
		
		
		
//		copy(tClass,dClass,cClass);
		
	}
}
