package com.txyd.json.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity implements Serializable  {
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date birthday;
	
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
	
	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", chName='" + chName + '\'' +
				", birthday=" + birthday +
				'}';
	}
}