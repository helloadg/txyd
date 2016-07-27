package com.txyd.database.service;

import java.util.List;

import com.txyd.database.bean.TableBean;

public interface TableService   extends BaseService<TableBean, Long>  {
	public List<TableBean> select(TableBean object);
}
