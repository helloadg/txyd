package com.txyd.database.service.impl;

import java.util.List;
import com.txyd.database.bean.DatabaseBean;
import com.txyd.database.service.DatabaseService;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl extends BaseServiceImpl<DatabaseBean, Long> implements DatabaseService {

    @Override
    public List<DatabaseBean> select(DatabaseBean object) {

        return this.select(object, null, null, null);
    }

}
