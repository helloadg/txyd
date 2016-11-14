package com.mc.common.param;

import java.io.Serializable;
import java.util.List;

public class SSUInfoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6909519833727941208L;
	
	
	
	private String total;
	
	private List<SSUInfoDto> rets;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<SSUInfoDto> getRets() {
		return rets;
	}

	public void setRets(List<SSUInfoDto> rets) {
		this.rets = rets;
	}

}
