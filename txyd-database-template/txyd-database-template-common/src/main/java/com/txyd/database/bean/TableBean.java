package com.txyd.database.bean;

import java.util.ArrayList;
import java.util.List;

import com.txyd.database.annotation.Column;
import com.txyd.database.annotation.Table;
import com.txyd.database.inter.DatabaseType;

/**
 * 数据库表
 * @author Administrator
 *
 */
public class TableBean extends BaseBean{
	/**
	 * 表所属的用户
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
	 * 表注释
	 */
	private String comments;
	
	/**
	 * 表类型
	 * table：基础表
	 * view：视图
	 */
	private String tableType;
	/**
	 * 对应的javabean名称
	 */
	private String javabeanName;
	/**
	 * 对应的javabean所对应的model类名称
	 */
	private String javabeanModelClassName;

	/**
	 * 对应的javabean所对应的BaseModel类名称
	 */
	private String javabeanBaseModelClassName;
	/**
	 * 对应的javabean所对应的key类名称
	 * 所对应的表有联合主键
	 */
	private String javabeanKeyClassName;
	
	/**
	 * 对应的javabean所对应的param类名称
	 */
	private String javabeanParamClassName;
	/**
	 * 对应的javabean所对应的Service类名称
	 */
	private String javabeanServiceClassName;
	/**
	 * 对应的javabean所对应的Service的实现类名称
	 */
	private String javabeanServiceImplClassName;
	/**
	 * 对应的javabean所对应的Mapper类名称
	 */
	private String javabeanMapperClassName;
	/**
	 * 对应的javabean所对应的Dao类名称
	 */
	private String javabeanDaoClassName;
	/**
	 * 对应的javabean所对应的Dao的实现类名称
	 */
	private String javabeanDaoImplClassName;
	/**
	 * 对应的javabean注解
	 */
	private Table javabeanAnnotation;
	/**
	 * 该表的主键个数
	 */
	private Integer primaryKeyNum;
	/**
	 * 表中的列
	 */
	@Column(isVirtual=true)
	private List<ColumnBean> listColumn=new ArrayList<ColumnBean>();
	



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
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<ColumnBean> getListColumn() {
		return listColumn;
	}
	public void setListColumn(List<ColumnBean> listColumn) {
		this.listColumn = listColumn;
	}


	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getJavabeanName() {
		return javabeanName;
	}
	public void setJavabeanName(String javabeanName) {
		this.javabeanName = javabeanName;
	}

	public Table getJavabeanAnnotation() {
		return javabeanAnnotation;
	}
	public void setJavabeanAnnotation(Table javabeanAnnotation) {
		this.javabeanAnnotation = javabeanAnnotation;
	}
	public String getTableType() {
		return tableType;
	}
	public Integer getPrimaryKeyNum() {
		return primaryKeyNum;
	}
	public void setPrimaryKeyNum(Integer primaryKeyNum) {
		this.primaryKeyNum = primaryKeyNum;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getJavabeanModelClassName() {
		return javabeanModelClassName;
	}
	public void setJavabeanModelClassName(String javabeanModelClassName) {
		this.javabeanModelClassName = javabeanModelClassName;
	}
	public String getJavabeanServiceClassName() {
		return javabeanServiceClassName;
	}
	public void setJavabeanServiceClassName(String javabeanServiceClassName) {
		this.javabeanServiceClassName = javabeanServiceClassName;
	}
	public String getJavabeanServiceImplClassName() {
		return javabeanServiceImplClassName;
	}
	public void setJavabeanServiceImplClassName(String javabeanServiceImplClassName) {
		this.javabeanServiceImplClassName = javabeanServiceImplClassName;
	}
	public String getJavabeanDaoClassName() {
		return javabeanDaoClassName;
	}
	public void setJavabeanDaoClassName(String javabeanDaoClassName) {
		this.javabeanDaoClassName = javabeanDaoClassName;
	}
	public String getJavabeanDaoImplClassName() {
		return javabeanDaoImplClassName;
	}
	public void setJavabeanDaoImplClassName(String javabeanDaoImplClassName) {
		this.javabeanDaoImplClassName = javabeanDaoImplClassName;
	}
	public String getJavabeanMapperClassName() {
		return javabeanMapperClassName;
	}
	public void setJavabeanMapperClassName(String javabeanMapperClassName) {
		this.javabeanMapperClassName = javabeanMapperClassName;
	}
	public String getJavabeanParamClassName() {
		return javabeanParamClassName;
	}
	public void setJavabeanParamClassName(String javabeanParamClassName) {
		this.javabeanParamClassName = javabeanParamClassName;
	}
	public String getJavabeanBaseModelClassName() {
		return javabeanBaseModelClassName;
	}
	public void setJavabeanBaseModelClassName(String javabeanBaseModelClassName) {
		this.javabeanBaseModelClassName = javabeanBaseModelClassName;
	}
	public String getJavabeanKeyClassName() {
		return javabeanKeyClassName;
	}
	public void setJavabeanKeyClassName(String javabeanKeyClassName) {
		this.javabeanKeyClassName = javabeanKeyClassName;
	}




}
