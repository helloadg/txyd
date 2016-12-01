package com.mc.common.rpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mc.common.exception.BusynessException;
import com.mc.common.param.CityDto;
import com.mc.common.param.SaleAreaDto;
import com.mc.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class  GisRpc extends BaseRpc {
	private static Logger log = LoggerFactory.getLogger(GisRpc.class);
	
	/**
	 * 获得所有已开通的城市
	 *
	 * @return
	 */
	public List<CityDto> getAllOpenCity() {
		
		String url = this.getRpcConfig().getGisUrl();
		String method = "/gis/areadivision/operatingcity";
		//入参
		Map<String, Object> params = new HashMap<>();
		params.put("status", 1);
		
		//调用远程接口
		Map<String, Object> result = this.postJson(url + method, params);
		
		//出参
		List<CityDto> data = new ArrayList<>();
		try {
			
			JSONArray cityArray = (JSONArray) result.get("data");
			data = JSONObject.parseArray(cityArray.toJSONString(), CityDto.class);
		} catch (Throwable te) {
			String inJson = JsonUtil.toJson(params);
			String outJson = JsonUtil.toJson(result);
			log.error("调用接口[{urlMethod}失败，入参[{inJson}],出参[{outJson}]"
					.replace("{urlMethod}", url + method)
					.replace("{inJson}", inJson)
					.replace("{outJson}", outJson));
			//异常中，不添加url，只有method，防止抛给前端
			throw new BusynessException("调用接口[{method}失败，入参[{inJson}],出参[{outJson}]"
					.replace("{method}", method)
					.replace("{inJson}", inJson)
					.replace("{outJson}", outJson));
		}
		return data;
	}
	
	
	/**
	 * 获得某个城市下的有效区域
	 *
	 * @return
	 */
	public List<SaleAreaDto> getSaleAreaByCityId(Long cityId) {
		
		String url = this.getRpcConfig().getGisUrl();
		String method = "/gis/salearea/getbycityid";
		//入参
		Map<String, Object> params = new HashMap<>();
		params.put("city_id", cityId);
		
		//调用远程接口
		Map<String, Object> result = this.postJson(url + method, params);
		
		//出参
		List<SaleAreaDto> data = new ArrayList<>();
		try {
			
			JSONArray cityArray = (JSONArray) result.get("data");
			data = JSONObject.parseArray(cityArray.toJSONString(), SaleAreaDto.class);
		} catch (Throwable te) {
			String inJson = JsonUtil.toJson(params);
			String outJson = JsonUtil.toJson(result);
			log.error("调用接口[{urlMethod}失败，入参[{inJson}],出参[{outJson}]"
					.replace("{urlMethod}", url + method)
					.replace("{inJson}", inJson)
					.replace("{outJson}", outJson));
			//异常中，不添加url，只有method，防止抛给前端
			throw new BusynessException("调用接口[{method}失败，入参[{inJson}],出参[{outJson}]"
					.replace("{method}", method)
					.replace("{inJson}", inJson)
					.replace("{outJson}", outJson));
		}
		return data;
	}
}
