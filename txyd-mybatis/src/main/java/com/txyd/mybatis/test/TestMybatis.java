package com.txyd.mybatis.test;

import java.util.List;
import com.txyd.mybatis.entity.ActivityEntity;
import com.txyd.mybatis.service.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-main.xml"})
public class TestMybatis {
    @Autowired
    private ActivityService activityService;



    @Test
    public void test(){
//        ActivityEntity activity=activityService.getById(15L);
//        System.out.println(activity.getName());
        List<ActivityEntity> list = activityService.getByParam("活动\\'");
        if(list!=null&&list.size()>0){
            for(ActivityEntity temp:list){
                System.out.println(temp.getName());
            }
        }
    }

}
