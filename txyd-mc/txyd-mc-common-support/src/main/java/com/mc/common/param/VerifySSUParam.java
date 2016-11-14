package com.mc.common.param;

import java.io.Serializable;
import java.util.List;

public class VerifySSUParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6504896768758214511L;
	private Integer ssuId;
	
	private List<Integer> ssuIds;
	
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

}
