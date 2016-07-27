package com.txyd.mybatis.service.impl;

import java.util.List;
import com.txyd.mybatis.dao.ActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.txyd.mybatis.entity.ActivityEntity;
import com.txyd.mybatis.service.ActivityService;

@Service
public class ActivityServiceImpl extends BaseServiceImpl<ActivityEntity,Long> implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<ActivityEntity> getByParam(String name) {
        return activityDao.getByParam(name);
    }
}

