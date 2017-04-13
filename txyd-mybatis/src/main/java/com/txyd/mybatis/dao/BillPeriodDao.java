package com.txyd.mybatis.dao;


import com.txyd.mybatis.dao.base.BaseDao;
import com.txyd.mybatis.entity.BillPeriodEntity;

/**
 * 数据库类型：mysql
 * 表所属schema：popbase
 * 表所属用户：root
 * 表名称：t_settlement_bill_period
 * 表注释：系统自动账单期表
 * 类型：table
 * @author：
 */
@MyBatisRepository
public interface BillPeriodDao  extends BaseDao<BillPeriodEntity,Long> {

}

