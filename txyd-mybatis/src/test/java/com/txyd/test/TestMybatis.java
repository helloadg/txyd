package com.txyd.test;

import com.txyd.mybatis.service.BillPeriodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-main-test.xml"})
public class TestMybatis {
    @Autowired
    private BillPeriodService billPeriodService;



    @Test
    public void test(){
    
    }

}
