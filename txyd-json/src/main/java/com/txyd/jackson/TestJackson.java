package com.txyd.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.txyd.json.entity.UserEntity;
import com.txyd.json.entity.UserSub;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Jackson提供了一系列注解，方便对JSON序列化和反序列化进行控制，下面介绍一些常用的注解。
 *
 * @JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性。
 *
 * @JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")。
 *
 * @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，@JsonProperty("name")。
 *
 * 当使用泛型时，最好使用TypeReference，  Url:http://blog.csdn.net/faicm/article/details/48321597
 *
 */
public class TestJackson {

	public static void test() throws Exception {
		Class<?> tClass=UserEntity.class;
		BeanInfo info= Introspector.getBeanInfo(tClass);
		PropertyDescriptor[] props=info.getPropertyDescriptors();
		Arrays.stream(props).limit(2).forEach(e-> {
			
			System.out.println(e.getName());
			System.out.println("\t"+e.getPropertyType().getPackage());
			System.out.println("\t"+e.getPropertyType().getName());
			System.out.println("\t"+e.getPropertyType().isAssignableFrom(Date.class));
			System.out.println("\t"+e.getPropertyType());
			System.out.println("\t"+e.getReadMethod());
			System.out.println("\t"+e.getWriteMethod());
			System.out.println("\t"+e.getWriteMethod().getName());
		});
	}
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

	/**
	 *
	 * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
	 * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
	 * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
	 * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
	 * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
	 * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
	 * @throws JsonProcessingException
	 */
	public static void beanToJson() throws JsonProcessingException{
		List<UserEntity> users=new ArrayList<>();
		UserSub userSub=new UserSub();
		userSub.setUsers(users);
		for (int i = 0; i <2 ; i++) {
			UserEntity user=new UserEntity();
			user.setId(Long.valueOf(i));
			user.setName("txyd"+i);
			user.setChName("天雪易懂"+i);
			user.setBirthday(new Date());
			user.setCt(1471489290);
			user.setUt(null);
			users.add(user);
			
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String json=mapper.writeValueAsString(userSub);
		System.out.println(json);
	}
	public static void jsonToBean() throws IOException {
		String json = "{\"users\":[{\"id\":0,\"name\":\"txyd0\",\"ch_name\":\"天雪易懂0\",\"birthday\":\"2016-08-09 09:48:35.012\"},{\"id\":1,\"name\":\"txyd1\",\"ch_name\":\"天雪易懂1\",\"birthday\":\"2015-08-09 09:48:35.012\"}]}";
		
		ObjectMapper mapper = new ObjectMapper();
		UserSub userSub=mapper.readValue(json,UserSub.class);
		
		System.out.println(userSub.toString());
	}
	
	
	public static <T , U  > void copy(T source,U desc, Class uClass){
		if(source==null || desc==null ){
			return;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			JavaType javaType =  mapper.getTypeFactory().constructParametricType(List.class, UserEntity.class) ;
			desc=mapper.readValue(mapper.writeValueAsString(source),javaType);
//			desc=mapper.readValue(mapper.writeValueAsString(source),uClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		{
//			test();
//			copy(UserSub.class,UserSub.class);
//			copy(UserSub.class,UserEntity.class);
			copy(UserEntity.class,UserSub.class);
		}
		{
			int length=2;
			List<UserEntity> users=new ArrayList<>();
			UserSub userSub=new UserSub();
			userSub.setId((long)(Math.random()*100+100));
			userSub.setName("人员大组合");
			userSub.setUsers(users);
			UserEntity[] userArray=new UserEntity[length];
			for (int i = 0; i <length ; i++) {
				UserEntity<Long> user=new UserEntity<>();
				user.setId(Long.valueOf(i));
				user.setName("txyd"+i);
				user.setChName("天雪易懂"+i);
				user.setBirthday(new Date());
				user.setCt(1471489290);
				user.setUt(null);
				user.setIsDeleted(0);
				user.setTinstance(100L);
				users.add(user);
				userArray[i]=user;
				
			}
			{
//				ObjectMapper mapper = new ObjectMapper();
//				String json=mapper.writeValueAsString(userArray);
//				System.out.println(json);
//				TypeReference<?> typeReference=new TypeReference<UserEntity<Long>[]>() {};
//				UserEntity[] userArray2=mapper.readValue(json,typeReference);
//				System.out.println(typeReference);
//				System.out.println(typeReference.getType());
//				System.out.println(userArray2);
				
				
			}
			{
//				ObjectMapper mapper = new ObjectMapper();
//				String json=mapper.writeValueAsString(userArray);
//				System.out.println(json);
//				JavaType javaType =mapper.getTypeFactory().constructParametricType(userArray.getClass(), UserEntity.class);
//				UserEntity[] userArray2=mapper.readValue(json,javaType);
//				System.out.println(userArray2);
				
			}
			{
//				ObjectMapper mapper = new ObjectMapper();
////			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
//				String json=mapper.writeValueAsString(userSub);
//				System.out.println(json);
//				JavaType javaType =mapper.getTypeFactory().constructParametricType(UserSub.class, UserEntity.class);
//				UserSub<UserEntity> userSub2=mapper.readValue(json,javaType);
//				UserEntity userEntity2=userSub2.getUsers().get(0);
//				System.out.println(userSub2);
				
			}
			
			
			
		}
		{
//			TestJackson.beanToJson();
//			TestJackson.jsonToBean();
		}
		{
//			ObjectMapper mapper = new ObjectMapper();
//			String json=mapper.writeValueAsString(new Integer(10));
//			System.out.println(json);
		}
		
		{
//			Class<ArrayList> uC=ArrayList.class;
//			List<UserEntity> source=new ArrayList<>();
//			for (int i = 0; i <2 ; i++) {
//				UserEntity user=new UserEntity();
//				user.setId(Long.valueOf(i));
//				user.setName("txyd"+i);
//				user.setChName("天雪易懂"+i);
//				user.setBirthday(new Date());
//				user.setCt(1471489290);
//				user.setUt(null);
//				source.add(user);
//			}
//			List<UserEntity> desc=new ArrayList<>();
//			copy(source,desc,ArrayList.class);
//			System.out.println(desc);
		}

		
		
	}
}
