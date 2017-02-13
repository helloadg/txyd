package com.txyd.database.bean;

import com.txyd.database.annotation.Column;
import com.txyd.database.inter.DatabaseType;



/**
 * 数据库表的列属性
 * @author Administrator
 *
 */
public class ColumnBean extends BaseBean{
	/**
	 * 数据库类型
	 */
	@Column(isVirtual=true)
	private DatabaseType databaseType;
	/**
	 * 表所属的用户
	 */
	@Column(isVirtual=true)
	private String tableOwner;
	/**
	 * 表所属的数据库名称
	 * oracle：就是用户，即owner
	 * mysql：就是shcema
	 */
	private String tableSchema;
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 原列名
	 */
	private String columnName;
	/**
	 * 列的数据类型
	 * 比如：varchar
	 * 比如：enum
	 */
	private String dataType;
	/**
	 * 列的类型
	 * 比如：varchar(255)
	 * 比如：enum('32323','12213','1222')--mysql；
	 */
	private String columnType;
	/**
	 * 列的扩展
	 * 比如：auto_increment
	 * 比如：on update CURRENT_TIMESTAMP
	 */
	private String extra;
	/**
	 * 列的数据类型的长度
	 */
	private String dataLength;
	/**
	 * 列的默认值
	 */
	private String defaultValue;
	/**
	 * 列注释
	 */
	private String comments;

	/**
	 * 此列是否是主键
	 */
	private Boolean isPrimaryKey;
	/**
	 * 是否可以为null
	 */
	private Boolean isNullAble;
	
	/**
	 * 对应的javabean的Field名称
	 */
	@Column(isVirtual=true)
	private String javabeanFieldName;
	
	/**
	 * 对应的javabean的Field所对应的java数据类型
	 */
	@Column(isVirtual=true)
	private String javabeanFieldDataType;
	/**
	 * 对应的javabean的Field所对应的java数据类型的简写
	 * 比如：java.util.String,其简写为String
	 */
	@Column(isVirtual=true)	
	private String javabeanFieldDataTypeSimple;
	/**
	 * 对应的javabean的Field注解
	 */
	@Column(isVirtual=true)
	private Column javabeanFieldAnnotation;
	
	/**
	 * 对应的javabean的Field所对应的java数据类型是否是基础类型
	 */
	@Column(isVirtual=true)
	private Boolean javabeanFieldDataTypeIsPrimitive;
	
	/**
	 * 对应的javabean的Field所对应的java数据类型是否是java.lang包下的类
	 */
	@Column(isVirtual=true)
	private Boolean javabeanFieldDataTypeIsOfLang;
	
	/**
	 * 对应的javabean的Field所对应的java数据类型是否是数字
	 */
	@Column(isVirtual=true)
	private Boolean javabeanFieldDataTypeIsNum;
	
	public DatabaseType getDatabaseType() {
		return databaseType;
	}
	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}
	public String getTableOwner() {
		return tableOwner;
	}
	public void setTableOwner(String tableOwner) {
		this.tableOwner = tableOwner;
	}
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Boolean getIsNullAble() {
		return isNullAble;
	}
	public void setIsNullAble(Boolean isNullAble) {
		this.isNullAble = isNullAble;
	}
	public String getJavabeanFieldName() {
		return javabeanFieldName;
	}
	public void setJavabeanFieldName(String javabeanFieldName) {
		this.javabeanFieldName = javabeanFieldName;
	}
	public String getJavabeanFieldDataType() {
		return javabeanFieldDataType;
	}
	public void setJavabeanFieldDataType(String javabeanFieldDataType) {
		this.javabeanFieldDataType = javabeanFieldDataType;
	}
	public String getJavabeanFieldDataTypeSimple() {
		return javabeanFieldDataTypeSimple;
	}
	public void setJavabeanFieldDataTypeSimple(String javabeanFieldDataTypeSimple) {
		this.javabeanFieldDataTypeSimple = javabeanFieldDataTypeSimple;
	}
	public Column getJavabeanFieldAnnotation() {
		return javabeanFieldAnnotation;
	}
	public void setJavabeanFieldAnnotation(Column javabeanFieldAnnotation) {
		this.javabeanFieldAnnotation = javabeanFieldAnnotation;
	}
	

	public Boolean getJavabeanFieldDataTypeIsPrimitive() {
		return javabeanFieldDataTypeIsPrimitive;
	}
	
	public void setJavabeanFieldDataTypeIsPrimitive(Boolean javabeanFieldDataTypeIsPrimitive) {
		this.javabeanFieldDataTypeIsPrimitive = javabeanFieldDataTypeIsPrimitive;
	}
	
	public Boolean getJavabeanFieldDataTypeIsOfLang() {
		return javabeanFieldDataTypeIsOfLang;
	}
	
	public void setJavabeanFieldDataTypeIsOfLang(Boolean javabeanFieldDataTypeIsOfLang) {
		this.javabeanFieldDataTypeIsOfLang = javabeanFieldDataTypeIsOfLang;
	}
	
	public Boolean getJavabeanFieldDataTypeIsNum() {
		return javabeanFieldDataTypeIsNum;
	}
	
	public void setJavabeanFieldDataTypeIsNum(Boolean javabeanFieldDataTypeIsNum) {
		this.javabeanFieldDataTypeIsNum = javabeanFieldDataTypeIsNum;
	}
	
	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}
	
	public void setIsPrimaryKey(Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
}
