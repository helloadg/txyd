package com.txyd.database.bean;

import java.util.ArrayList;
import java.util.List;

import com.txyd.database.annotation.Column;

/**
 * 数据库
 * @author     
 *
 */
public class DatabaseBean extends BaseBean  {
	/**
	 * 数据库所属的用户
	 */
	@Column(isVirtual=true)
	private String owner;
	/**
	 * 数据库类型
	 */
	@Column(isVirtual=true)
	private String databaseType;
	/**
	 * 数据库名称
	 * oracle：就是数据库所属的用户
	 * mysql：schema
	 */
	private String schemaName;
	/**
	 * 数据库注释
	 */
	private String comments;
	private List<TableBean> listTable=new ArrayList<TableBean>();
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<TableBean> getListTable() {
		return listTable;
	}
	public void setListTable(List<TableBean> listTable) {
		this.listTable = listTable;
	}
	public String getDatabaseType() {
		return databaseType;
	}
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
	
	
	
	
	
}
