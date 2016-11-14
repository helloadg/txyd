package com.mc.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("rpcConfig")
public class RpcConfig {

	/**
	 * gis url
	 */
	@Value("${gis.url}")
	private String gisUrl;

	/**
	 * product url
	 */
	@Value("${product.url}")
	private String productUrl;
	
	/**
	 * weiXin url
	 */
	@Value("${weiXin.url}")
	private String weiXinUrl;
	
	

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getGisUrl() {
		return gisUrl;
	}

	public void setGisUrl(String gisUrl) {
		this.gisUrl = gisUrl;
	}
	
	public String getWeiXinUrl() {
		return weiXinUrl;
	}
	
	public void setWeiXinUrl(String weiXinUrl) {
		this.weiXinUrl = weiXinUrl;
	}

}
