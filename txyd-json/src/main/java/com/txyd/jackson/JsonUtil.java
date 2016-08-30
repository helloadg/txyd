package com.txyd.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.txyd.json.entity.UserEntity;
import com.txyd.json.entity.UserSub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public final class JsonUtil   {
	private static final ObjectMapper objectMapper = new ObjectMapper();
//	private static final Map<String,TypeReference<?>> map =new LinkedHashMap<>();
	
	private JsonUtil() {}
	private static final class JsonUtilHolder{
		private static final JsonUtil INSTANCE=new JsonUtil();
	}
	public static JsonUtil getInstance(){
		return JsonUtilHolder.INSTANCE;
	}
	public static ObjectMapper getObjectMapper(){
		return getInstance().objectMapper;
	}
//	public static Map<String,TypeReference<?>> getMap(){
//		return getInstance().map;
//	}
//	public static <T> T fromJson(String json) throws Exception {
//		Class<? > tClass=Class.<T>forName("");
//		TypeReference<?> typeReference = map.get(tClass.getCanonicalName());
//		if(typeReference == null){
//			typeReference=new TypeReference<T>() {};
//			map.put(tClass.getCanonicalName(),typeReference);
//		}
//		try {
//			return JsonUtil.objectMapper.readValue(json,typeReference);
//		} catch (IOException e) {
//			throw new Exception();
//		}
//	}

//	public static <T> T fromJson(String json,Class<T> tClass) throws Exception {
//		TypeReference<?> typeReference = map.get(tClass.getCanonicalName());
//		if(typeReference == null){
//			typeReference=new TypeReference<T>() {};
//			map.put(tClass.getCanonicalName(),typeReference);
//		}
//		try {
//			return JsonUtil.objectMapper.readValue(json,typeReference);
//		} catch (IOException e) {
//			throw new Exception();
//		}
//	}
//
//	public static <T> T fromJson(String json,ObjectMapper objectMapper,Class<T> tClass) throws Exception {
//		TypeReference<?> typeReference = map.get(tClass.getCanonicalName());
//		if(typeReference == null){
//			typeReference=new TypeReference<T>() {};
//			map.put(tClass.getCanonicalName(),typeReference);
//		}
//		try {
//			return objectMapper.readValue(json,typeReference);
//		} catch (IOException e) {
//			throw new Exception();
//		}
//	}
	public static <T> T fromJson(String json,TypeReference<T> typeReference) throws Exception {
		try {
			return JsonUtil.objectMapper.readValue(json,typeReference);
		} catch (IOException e) {
			throw new Exception();
		}
	}
	
	public static <T> T fromJson(String json,TypeReference<T> typeReference,ObjectMapper objectMapper) throws Exception {
		try {
			return objectMapper.readValue(json,typeReference);
		} catch (IOException e) {
			throw new Exception();
		}
	}
	
	public static <T> String toJson(T t)throws Exception {
		try {
			return JsonUtil.objectMapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			throw new Exception();
		}
		
	}
	public static <T> String toJson(T t,ObjectMapper objectMapper)throws Exception {
		try {
			return objectMapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			throw new Exception();
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
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
				String jsonUsers=toJson(users);
				String jsonUserSub=toJson(userSub);
				String jsonUserArray=toJson(userArray);
				System.out.println(jsonUsers);
				System.out.println(jsonUserSub);
				System.out.println(jsonUserArray);
				{
					UserSub temp=fromJson(jsonUserSub, new TypeReference<UserSub<UserEntity<Long>>>() {});
					System.out.println(temp.getUsers().size());
				}
				{
					UserEntity[] temp=fromJson(jsonUserArray, new TypeReference<UserEntity<Long>[]>() {});
					System.out.println(temp.length);
				}
				{
					List temp=fromJson(jsonUserArray, new TypeReference<List<UserEntity<Long>>>() {});
					System.out.println(temp.size());
				}
				
				{
					List temp=fromJson(jsonUsers, new TypeReference<List<UserEntity<Long>>>() {});
					System.out.println(temp.size());
				}

				
				
			}
		}
		{
			
//			Integer integer=new Integer(19);
//			Integer[] integers=new Integer[10];
//			System.out.println(integer.getClass().getCanonicalName());
//			System.out.println(integers.getClass().getCanonicalName());
		}
	}
	
	
	
}
