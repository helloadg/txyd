package com.txyd.database.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.txyd.database.bean.ColumnBean;
import com.txyd.database.service.ColumnService;
@Service
public class ColumnServiceImpl  extends BaseServiceImpl<ColumnBean, Long> implements ColumnService {
	@Override
	public List<ColumnBean> select(ColumnBean object) {
		return this.select(object, null, null, null);
	}
	

}
