package com.txyd.database.service;

import java.util.List;

import com.txyd.database.bean.ColumnBean;

public interface ColumnService   extends BaseService<ColumnBean, Long>  {

	public List<ColumnBean> select(ColumnBean object);
}
