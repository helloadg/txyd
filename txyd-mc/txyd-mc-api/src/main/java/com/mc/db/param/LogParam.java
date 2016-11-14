package com.mc.db.param;
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
public class LogParam implements Serializable  {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	//@JsonProperty("id")
	private Long id;	
	
	//@JsonProperty("f_plan_id")
	private Long fplanId;	
	
	//@JsonProperty("type")
	private Integer type;	
	
	//@JsonProperty("content")
	private String content;	
	
	//@JsonProperty("c_t")
	private Integer ct;	
	
	//@JsonProperty("u_t")
	private Integer ut;	
	
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
	public LogParam deepClone() {
		LogParam entity = new LogParam();
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
		return "LogParam{"
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