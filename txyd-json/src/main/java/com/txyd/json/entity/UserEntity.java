package com.txyd.json.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity<T>  extends BaseEntity implements Serializable  {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("ch_name")
	private String chName;
	
	@JsonProperty("birthday")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date birthday;
	
	@JsonProperty("c_t")
//	@JsonSerialize(using = TimeStampJsonSerializer.class)
	private Integer ct;
	
	@JsonProperty("u_t")
	private Integer ut;
	
	@JsonProperty("is_deleted")
	private Integer isDeleted;
	
	@JsonProperty("t_instance")
	private T tinstance;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getChName() {
		return chName;
	}
	
	public void setChName(String chName) {
		this.chName = chName;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Integer getCt() {
		return ct;
	}
	
	public void setCt(Integer ct) {
		this.ct = ct;
	}
	
	public Integer getUt() {
		return ut;
	}
	
	public void setUt(Integer ut) {
		this.ut = ut;
	}
	
	public Integer getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public T getTinstance() {
		return tinstance;
	}
	
	public void setTinstance(T tinstance) {
		this.tinstance = tinstance;
	}
	
	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", chName='" + chName + '\'' +
				", birthday=" + birthday +
				", ct=" + ct +
				", ut=" + ut +
				", isDeleted=" + isDeleted +
				", tinstance=" + tinstance +
				'}';
	}
}