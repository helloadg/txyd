package com.mc.db.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
//import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 数据库类型：mysql
 * 表所属schema：mall_cms
 * 表所属用户：root
 * 表名称：t_cms_log
 * 表注释：日志表
 * 类型：table
 * @author：
 */
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogEntity  implements Serializable  {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：bigint(20) unsigned
	 * 默认值：
	 * 列的数据类型的长度：20.0
	 * 列注释：主键
	 * 列的扩展：auto_increment
	 * 列名：id
	 * 列的数据类型：bigint
	 * 是否是主键：是
	 */
	//@JsonProperty("id")
	private Long id;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：bigint(20) unsigned
	 * 默认值：
	 * 列的数据类型的长度：20.0
	 * 列注释：方案id
	 * 列的扩展：
	 * 列名：f_plan_id
	 * 列的数据类型：bigint
	 * 是否是主键：否
	 */
	//@JsonProperty("f_plan_id")
	private Long fplanId;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4)
	 * 默认值：
	 * 列的数据类型的长度：3.0
	 * 列注释：Log类型：1创建、2修改、3提审、4审核通过、5审核打回、6发布、7删除、8移入回收站、9弃审
	 * 列的扩展：
	 * 列名：type
	 * 列的数据类型：tinyint
	 * 是否是主键：否
	 */
	//@JsonProperty("type")
	private Integer type;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：text
	 * 默认值：
	 * 列的数据类型的长度：65535
	 * 列注释：操作内容
	 * 列的扩展：
	 * 列名：content
	 * 列的数据类型：text
	 * 是否是主键：否
	 */
	//@JsonProperty("content")
	private String content;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11) unsigned
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：创建时间
	 * 列的扩展：
	 * 列名：c_t
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	//@JsonProperty("c_t")
	private Integer ct;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11) unsigned
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：修改时间
	 * 列的扩展：
	 * 列名：u_t
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	//@JsonProperty("u_t")
	private Integer ut;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4) unsigned
	 * 默认值：0
	 * 列的数据类型的长度：3.0
	 * 列注释：删除标识，0正常，1删除
	 * 列的扩展：
	 * 列名：is_deleted
	 * 列的数据类型：tinyint
	 * 是否是主键：否
	 */
	//@JsonProperty("is_deleted")
	private Integer isDeleted;	

	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	
	public Long getFplanId(){
		return this.fplanId;
	}
	
	public void setFplanId(Long fplanId){
		this.fplanId = fplanId;
	}

	
	public Integer getType(){
		return this.type;
	}
	
	public void setType(Integer type){
		this.type = type;
	}

	
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}

	
	public Integer getCt(){
		return this.ct;
	}
	
	public void setCt(Integer ct){
		this.ct = ct;
	}

	
	public Integer getUt(){
		return this.ut;
	}
	
	public void setUt(Integer ut){
		this.ut = ut;
	}

	
	public Integer getIsDeleted(){
		return this.isDeleted;
	}
	
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}


	/**
	* 深度复制
	*/
	public  LogEntity deepClone() {
		LogEntity entity = new LogEntity();
		entity.setId(this.getId());
		entity.setFplanId(this.getFplanId());
		entity.setType(this.getType());
		entity.setContent(this.getContent());
		entity.setCt(this.getCt());
		entity.setUt(this.getUt());
		entity.setIsDeleted(this.getIsDeleted());
		return entity;
	}

	@Override
	public String toString() {
		return "LogEntity{"
        	+ " \"id\":" + this.getId() +","
        	+ " \"fplanId\":" + this.getFplanId() +","
        	+ " \"type\":" + this.getType() +","
        	+ " \"content\":\"" + this.getContent() +"\","
        	+ " \"ct\":" + this.getCt() +","
        	+ " \"ut\":" + this.getUt() +","
        	+ " \"isDeleted\":" + this.getIsDeleted() +""
		+"}";
	}
}