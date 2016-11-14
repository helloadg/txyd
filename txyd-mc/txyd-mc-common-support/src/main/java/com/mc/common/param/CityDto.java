package com.mc.common.param;

import java.io.Serializable;

public class CityDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;//开通城市自增id
	private String code;//国标码
	private String name;//名称
	private String short_name;//简称
	private String status;//状态：0不开通，1开通
	private String phone_prefix;//区号
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getShort_name() {
		return short_name;
	}
	
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPhone_prefix() {
		return phone_prefix;
	}
	
	public void setPhone_prefix(String phone_prefix) {
		this.phone_prefix = phone_prefix;
	}
}
