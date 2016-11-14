package com.mc.common.param;

import java.io.Serializable;

public class SaleAreaDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;//售卖区自增id
	private String city_id;//所属城市id
	private String name;//名称
	private String status;//状态
	private String c_t;//创建时间
	private String u_t;//修改时间
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCity_id() {
		return city_id;
	}
	
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getC_t() {
		return c_t;
	}
	
	public void setC_t(String c_t) {
		this.c_t = c_t;
	}
	
	public String getU_t() {
		return u_t;
	}
	
	public void setU_t(String u_t) {
		this.u_t = u_t;
	}
}
