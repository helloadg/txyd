package com.mc.common.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mc.common.config.RpcConfig;
import com.mc.common.exception.SystemException;
import com.mc.common.utils.HttpClientUtil;
import com.mc.common.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class BaseRpc {

	@Autowired
	private RpcConfig rpcConfig;

	public RpcConfig getRpcConfig() {
		return rpcConfig;
	}

	public void setRpcConfig(RpcConfig rpcConfig) {
		this.rpcConfig = rpcConfig;
	}
	
	
	public JSONObject postJson(String url, Map<String,Object> params){
		
		String jsonParam = JSON.toJSONString(params);
		String responseStr=null;
		try{
			responseStr = HttpClientUtil.postJson(url, jsonParam);
		}catch (Throwable e){
			throw new SystemException("调用接口异常,url:[{url}],入参:[{jsonParam}]".replace("{url}",url).replace("{jsonParam}",jsonParam));
		}
		
		
		JSONObject result = null;
		if(responseStr!=null && !"".equals(responseStr.trim())){
			try {
				result = JSONObject.parseObject(responseStr.trim(), JSONObject.class);
			} catch (Exception e) {
				throw new SystemException("调用接口返回值转换，异常,url:[{url}],入参:[{jsonParam}]".replace("{url}",url).replace("{jsonParam}",jsonParam));
			}
		}
		return result;
		
	}
	
	public String postJsonStr(String url , Map<String, Object> params){
	    String jsonStr = JsonUtil.toJson(params);
        String responseStr = HttpClientUtil.postJson(url, jsonStr);
        return responseStr;
	}
}
