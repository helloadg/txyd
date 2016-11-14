package com.mc.common.param;

import java.io.Serializable;
import java.util.List;

public class SSUInfoParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 189642264709718072L;
	
	private Integer ssuId;//商品id
	private List<Integer> ssuIds;// 集合
	private Integer cityId;// 城市id
	private Integer areaId;// 区域id
	//private Integer company_id;// 商户id （目前可以随便填一个正整数）
	//private String platform;// 平台信息
	//private Boolean only_online;// 是否过滤下线商品
	
	
	public Integer getSsuId() {
		return ssuId;
	}
	
	public void setSsuId(Integer ssuId) {
		this.ssuId = ssuId;
	}
	
	public List<Integer> getSsuIds() {
		return ssuIds;
	}
	
	public void setSsuIds(List<Integer> ssuIds) {
		this.ssuIds = ssuIds;
	}
	
	public Integer getCityId() {
		return cityId;
	}
	
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Integer getAreaId() {
		return areaId;
	}
	
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
}
