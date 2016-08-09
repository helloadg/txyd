package com.txyd.fastjson;

import com.alibaba.fastjson.JSON;
import com.txyd.json.entity.UserEntity;
import com.txyd.json.entity.UserSub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class TestFastjson {
	public static void beanToJson(){
		List<UserEntity> users=new ArrayList<>();
		UserSub userSub=new UserSub();
		userSub.setUsers(users);
		for (int i = 0; i <2 ; i++) {
			UserEntity user=new UserEntity();
			user.setId(Long.valueOf(i));
			user.setName("txyd"+i);
			user.setChName("天雪易懂"+i);
			user.setBirthday(new Date());
			users.add(user);
			
		}
		String json=JSON.toJSONString(userSub);
		System.out.println(json);
		
	}
	public static void jsonToBean(){
		String json = "{\"users\":[{\"birthday\":1470737472843,\"chName\":\"天雪易懂0\",\"id\":0,\"name\":\"txyd0\"},{\"birthday\":1470737472843,\"chName\":\"天雪易懂1\",\"id\":1,\"name\":\"txyd1\"}]}";
		UserSub userSub=JSON.parseObject(json,UserSub.class);
		System.out.println(userSub);
		
	}
	
	public static void main(String[] args) {
		TestFastjson.beanToJson();
		TestFastjson.jsonToBean();
	}
}
