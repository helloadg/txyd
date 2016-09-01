package com.txyd.jackson;

import com.txyd.json.entity.UserEntity;
import com.txyd.json.entity.UserSub;
//import org.dozer.DozerBeanMapper;
//import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class BeanUtil{
	
	
	public static void main(String[] args) {
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
//				UserSub userSubDes=new UserSub();
//				Mapper mapper = new DozerBeanMapper();
//				mapper.map(userSub,userSubDes);
//
//				System.out.println(userSubDes.getUsers().size());
			}
			{
//				List<UserEntity> usersDes=new ArrayList<>();
//				Mapper mapper = new DozerBeanMapper();
//				mapper.map(users,usersDes);
//
//				System.out.println(usersDes.size());
			}
			{//false
//				UserEntity[] userArrayDes=new UserEntity[length];
//				Mapper mapper = new DozerBeanMapper();
//				mapper.map(userArray,userArrayDes);
//
//				System.out.println(userArrayDes.length);
			}
			{
				UserEntity<Long> source=new UserEntity<>();
				source.setId(100L);
				source.setName("hou后吼吼");
				UserSub desc=new UserSub();
//				Mapper mapper = new DozerBeanMapper();
				long startTime=System.currentTimeMillis();
				for(int i=0;i<10000000;i++){
					
//					mapper.map(source,desc);
					desc.setId(source.getId());
					desc.setName(source.getName());
				}
				long endTime=System.currentTimeMillis();
				System.out.println("耗时："+(endTime-startTime)+"ms");
				System.out.println(desc);
				
			}
			
			
		}

	}
}
