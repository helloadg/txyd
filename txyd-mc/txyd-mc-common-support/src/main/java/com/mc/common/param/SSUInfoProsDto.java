package com.mc.common.param;

import java.io.Serializable;

public class SSUInfoProsDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5803635752790293457L;
	
	
	private String cityId;
	private String classs;
	private String infos;
	private String ciId;
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
	public String getInfos() {
		return infos;
	}
	public void setInfos(String infos) {
		this.infos = infos;
	}
	public String getCiId() {
		return ciId;
	}
	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

}
