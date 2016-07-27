package com.txyd.mybatis.dao.page;

/**
 * Created by Administrator on 2016/6/13.
 */
public interface Dialect {
    String SQL_END_DELIMITER = ";";
    String DISTINCT = "DISTINCT";

    String getPagedSQL(String originalSQL, PageCondition condition);

    String getCountSQL(String originalSQL);
}
