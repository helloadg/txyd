package com.mc.db.dao;


import com.mc.db.dao.base.BaseDao;
import com.mc.db.entity.LogEntity;
import com.mc.db.dao.base.BaseKeyDao;

/**
 * 数据库类型：mysql
 * 表所属schema：test
 * 表所属用户：root
 * 表名称：t_cms_log
 * 表注释：日志表
 * 类型：table
 * @author：
 */
public interface LogDao extends BaseDao<LogEntity>,BaseKeyDao<LogEntity,Long> {
	
	int updateTest(LogEntity logEntity);

}
