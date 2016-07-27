package com.txyd.mybatis.service;

import java.util.List;
import com.txyd.mybatis.entity.ActivityEntity;


public interface ActivityService extends BaseService<ActivityEntity,Long> {
    List<ActivityEntity> getByParam(String name);

}