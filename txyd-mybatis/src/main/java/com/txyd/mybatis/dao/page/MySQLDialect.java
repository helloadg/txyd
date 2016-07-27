package com.txyd.mybatis.dao.page;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分页sql语句Mysql语法支持
 */
public class MySQLDialect implements Dialect {
    private static final Pattern SELECT_PATTERN = Pattern.compile("^select\\s+(\\b[\\s\\S]+?)\\s*\\bfrom\\b([\\S\\s]+?)(\\border\\s+by[\\s\\S]+)$",
            Pattern.CASE_INSENSITIVE);

    public String getPagedSQL(String originalSQL, PageCondition condition) {
        originalSQL = originalSQL.trim();
        if (originalSQL.endsWith(Dialect.SQL_END_DELIMITER)) {
            originalSQL = originalSQL.substring(0, originalSQL.length() - 1);
        }
        int offset = condition.getPageSize() * (condition.getPageNum() - PageCondition.defaultPageStartNum);
        int limit = condition.getPageSize();
        originalSQL += String.format(" limit %d, %d", offset, limit);
        return originalSQL;
    }

    public String getCountSQL(String originalSQL) {
        Matcher matcher = SELECT_PATTERN.matcher(originalSQL);
        if (matcher.find()) {
            String fields = matcher.group(1);
            String fromExpression = matcher.group(2);

            StringBuilder sbSql = new StringBuilder(originalSQL.length() + 20);
            sbSql.append("select ");
            fields = fields.trim();
            if (fields.length() > DISTINCT.length()
                    && DISTINCT.equalsIgnoreCase(fields.substring(0, DISTINCT.length()))) {
                sbSql.append("COUNT ({columns} as cn ) ".replace("{columns}",fields));
            }else{
                sbSql.append("COUNT(1) as cn");
            }
            sbSql.append(" from ");
            sbSql.append(fromExpression);
            return sbSql.toString();
        }

        throw new RuntimeException("pagbatis can not generate count sql");
    }
}