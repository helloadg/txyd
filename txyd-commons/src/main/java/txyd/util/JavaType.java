package txyd.util;

import java.util.HashMap;
import java.util.Map;

public class JavaType {
	public static final Map<String,String> oracleMap=new HashMap<String, String>();
	static{
		/*************oracleMap初始化*****************/
		oracleMap.put("CHAR", "String");
		oracleMap.put("VARCHAR", "String");
		oracleMap.put("LONGVARCHAR", "String");
		oracleMap.put("NUMERIC", "java.math.BigDecimal");
		oracleMap.put("DECIMAL", "java.math.BigDecimal");
		oracleMap.put("BIT", "Boolean");
		oracleMap.put("TINYINT", "Integer");
		oracleMap.put("SMALLINT", "Integer");
		oracleMap.put("INTEGER", "Integer");
		oracleMap.put("BIGINT", "Long");
		oracleMap.put("REAL", "Float");
		oracleMap.put("FLOAT", "Double");
		oracleMap.put("DOUBLE", "Double");
		oracleMap.put("BINARY", "byte[]");
		oracleMap.put("VARBINARY", "byte[]");
		oracleMap.put("LONGVARBINARY", "byte[]");
		oracleMap.put("DATE", "java.sql.Date");
		oracleMap.put("TIME", "java.sql.Time");
		oracleMap.put("TIMESTAMP", "java.sql.Timestamp");
	}
}
