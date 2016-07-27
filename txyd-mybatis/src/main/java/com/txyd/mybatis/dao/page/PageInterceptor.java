//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.txyd.mybatis.dao.page;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class}
)})
public class PageInterceptor implements Interceptor {
    private static final Logger LOGGER = Logger.getLogger(PageInterceptor.class);
    private static final String BOUND_SQL_KEY = "delegate.boundSql.sql";
    private static final String MAPPED_STATEMENT_KEY = "delegate.mappedStatement";
    private final Properties properties = new Properties();

    public PageInterceptor() {
    }

    public Object intercept(Invocation invocation) throws Throwable {

        LOGGER.debug("PageInterceptor intercept");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        PageCondition condition = null;
        Object object = statementHandler.getBoundSql().getParameterObject();
        if (object instanceof Map) {
            Map originalSql = (Map) object;
            Iterator dbDialect = originalSql.values().iterator();

            while (dbDialect.hasNext()) {
                Object dbPagedSql = dbDialect.next();
                if (dbPagedSql instanceof PageCondition) {
                    condition = (PageCondition) dbPagedSql;
                    break;
                }
            }
        } else if (object instanceof PageCondition) {
            condition = (PageCondition) object;
        }

        if (condition != null && !condition.isDisablePagePlugin()) {
            String originalSql1 = statementHandler.getBoundSql().getSql();
            Dialect dbDialect1 = this.getDialect();
            LOGGER.debug("origin sql: " + originalSql1);
            String dbPagedSql1 = dbDialect1.getPagedSQL(originalSql1, condition);
            LOGGER.debug("paged sql: " + dbPagedSql1);
            MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
            metaObject.setValue("delegate.boundSql.sql", dbPagedSql1);
            this.setTotalCount(invocation, statementHandler, metaObject, dbDialect1, condition, originalSql1);
            return invocation.proceed();
        } else {
            LOGGER.debug("PageCondition parameter is null, exit");
            return invocation.proceed();
        }
    }

    private void setTotalCount(Invocation invocation, StatementHandler statementHandler, MetaObject metaStatementHandler, Dialect dbDialect, PageCondition condition, String originalSql) throws SQLException {
        String countSql = dbDialect.getCountSQL(originalSql);
        LOGGER.debug("count sql " + countSql);
        Connection connection = (Connection) invocation.getArgs()[0];
        BoundSql boundSql = statementHandler.getBoundSql();
        PreparedStatement countStmt = null;
        ResultSet rs = null;

        try {
            countStmt = connection.prepareStatement(countSql);
            List parameterMappings = boundSql.getParameterMappings();
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, boundSql.getParameterObject());
            if (parameterMappings != null && parameterMappings.size() > 0) {
                Iterator parameterHandler = parameterMappings.iterator();

                while (parameterHandler.hasNext()) {
                    ParameterMapping totalCount = (ParameterMapping) parameterHandler.next();
                    String propertyName = totalCount.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    Object propertyValue;
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        propertyValue = boundSql.getAdditionalParameter(propertyName);
                        countBS.setAdditionalParameter(propertyName, propertyValue);
                    } else if (propertyName.startsWith("__frch_") && boundSql.hasAdditionalParameter(prop.getName())) {
                        propertyValue = boundSql.getAdditionalParameter(prop.getName());
                        countBS.setAdditionalParameter(propertyName, propertyValue);
                    }
                }
            }

            DefaultParameterHandler parameterHandler1 = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
            parameterHandler1.setParameters(countStmt);
            rs = countStmt.executeQuery();
            int totalCount1 = 0;
            if (rs.next()) {
                totalCount1 = rs.getInt(1);
            }

            condition.setTotalCount(totalCount1);
        } finally {
            if (rs != null) {
                rs.close();
                rs = null;
            }

            if (countStmt != null) {
                countStmt.close();
                countStmt = null;
            }

        }

    }

    private Dialect getDialect() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String dialectType = this.properties.getProperty("dialectType");
        if (dialectType == null) {
            throw new RuntimeException("can not find \"pageMybatis.dialectType\" in mybatis config file");
        } else {
            Class dialectClass = Class.forName(dialectType);
            Constructor[] ctors = dialectClass.getDeclaredConstructors();
            Constructor ctor = null;

            for (int i = 0; i < ctors.length; ++i) {
                ctor = ctors[i];
                if (ctor.getGenericParameterTypes().length == 0) {
                    break;
                }
            }

            if (ctor == null) {
                throw new RuntimeException(dialectType + " has no default ctor");
            } else {
                ctor.setAccessible(true);
                return (Dialect) ctor.newInstance(new Object[0]);
            }
        }
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties propertiesArg) {
        if (propertiesArg != null) {
            Iterator var2 = propertiesArg.keySet().iterator();

            while (var2.hasNext()) {
                Object key = var2.next();
                String strKey = (String) key;
                this.properties.put(strKey, propertiesArg.get(key));
            }
        }

    }
}
