package com.txyd.database.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.txyd.database.bean.TableBean;
import com.txyd.database.service.TableService;
@Service
public class TableServiceImpl  extends BaseServiceImpl<TableBean, Long> implements TableService  {
	@Override
	public List<TableBean> select(TableBean object) {
		return this.select(object, null, null, null);
	}
	

}
