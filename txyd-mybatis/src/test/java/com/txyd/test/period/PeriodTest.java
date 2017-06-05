package com.txyd.test.period;

import com.txyd.mybatis.entity.BillPeriodEntity;
import com.txyd.mybatis.service.BillPeriodService;
import com.txyd.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import txyd.common.ServiceResult;
import txyd.util.JsonUtil;

/**
 * Created by Administrator on 2016/6/17.
 */
public class PeriodTest extends BaseTest {
    
    @Autowired
    private BillPeriodService billPeriodService;



    @Test
    public void test(){
        try{

//        List<BillPeriodEntity> result = this.billPeriodService.select(new BillPeriodEntity());
            BillPeriodEntity result = this.billPeriodService.testTruncate(3);
            System.out.println(getLine());
            System.out.println(JsonUtil.toJson(result));
            System.out.println(getLine());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    
    }
    
    @Test
    public void testSr(){
        try{

//        List<BillPeriodEntity> result = this.billPeriodService.select(new BillPeriodEntity());
            ServiceResult<BillPeriodEntity> result = this.billPeriodService.testTruncateSr(3);
            System.out.println(getLine());
            System.out.println(JsonUtil.toJson(result));
            System.out.println(getLine());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
