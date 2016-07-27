package com.txyd.mybatis.dao;

import java.util.List;
import com.txyd.mybatis.entity.ActivityEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 数据库类型：mysql
 * 表所属schema：test
 * 表所属用户：root
 * 表名称：t_lottery_activity
 * 表注释：
 * 类型：table
 * @author：
 */
public interface ActivityDao  extends BaseDao <ActivityEntity,Long> {
	List<ActivityEntity>  getByParam(@Param("name")String name);
}
