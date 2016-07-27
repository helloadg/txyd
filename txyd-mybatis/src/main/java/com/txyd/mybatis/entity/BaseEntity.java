package com.txyd.mybatis.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * model类扩展
 * @author：
 */
public   class  BaseEntity implements Serializable {

	private static final long serialVersionUID = 5672499310805031314L;
	/**
	 * 排序
	 */
	private LinkedHashMap<String, String> sort;


	public LinkedHashMap<String, String> getSort() {
		return sort;
	}

	public void setSort(LinkedHashMap<String, String> sort) {
		this.sort = sort;
	}
}