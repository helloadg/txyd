package com.txyd.database.service;

import java.util.List;

import com.txyd.database.bean.DatabaseBean;

public interface DatabaseService  extends BaseService<DatabaseBean, Long>  {
	public List<DatabaseBean> select(DatabaseBean object);

}
