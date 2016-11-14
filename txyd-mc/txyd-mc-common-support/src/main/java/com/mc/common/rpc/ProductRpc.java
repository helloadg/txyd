package com.mc.common.rpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mc.common.exception.BusinessException;
import com.mc.common.param.SSUBasicInfoDto;
import com.mc.common.param.SSUInfoDto;
import com.mc.common.param.SSUInfoParam;
import com.mc.common.param.VerifySSUParam;
import com.mc.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductRpc extends BaseRpc {

	private static Logger log = LoggerFactory.getLogger(ProductRpc.class);

	/**
	 * 通过ssu_id,city_id,area_id获得某个城市,区域下商品信息
	 *
	 * @return
	 */
	public List<SSUInfoDto> searchSsuBySsuIds(SSUInfoParam ssuInfo) {

		String url = this.getRpcConfig().getProductUrl();

		String method = "/api/gwss/com.sprucetec.gwss.search.service.SearchService/searchSsuBySsuIds";

		// 入参
		Map<String, Object> params = new HashMap<>();

		params.put("ssu_ids", ssuInfo.getSsuIds());
		params.put("city_id", ssuInfo.getCityId());
		params.put("company_id",0);
		params.put("platform", "cms");
		params.put("only_online", true);
		params.put("area_id", ssuInfo.getAreaId());

		// 调用远程接口
		Map<String, Object> result = this.postJson(url + method, params);

		// 出参
		List<SSUInfoDto> data = new ArrayList<SSUInfoDto>();
		try {

			JSONObject pruductObject = (JSONObject) result.get("data");
			JSONArray pruductArray = (JSONArray) pruductObject.getJSONArray("rets");

			data = JSONObject.parseArray(pruductArray.toJSONString(), SSUInfoDto.class);
		} catch (Throwable te) {
			String inJson = JsonUtil.toJson(params);
			String outJson = JsonUtil.toJson(result);
			log.error("调用接口[{urlMethod}失败，入参[{inJson}],出参[{outJson}]".replace("{urlMethod}", url + method)
					.replace("{inJson}", inJson).replace("{outJson}", outJson));
			// 异常中，不添加url，只有method，防止抛给前端
			throw new BusinessException("调用接口[{method}失败，入参[{inJson}],出参[{outJson}]".replace("{method}", method)
					.replace("{inJson}", inJson).replace("{outJson}", outJson));
		}
		return data;
	}

	/**
	 * 通过ssu_id,city_id,area_id获得某个城市,区域下商品信息
	 *
	 * @return
	 */
	public List<SSUBasicInfoDto> queryBasicSsu(VerifySSUParam verifySsu) {

		String url = this.getRpcConfig().getProductUrl();

		String method = "/api/gwsapi/com.sprucetec.gwsapi.product.service.IProductService/queryBasicSsu";

		// 入参
		Map<String, Object> params = new HashMap<>();

		params.put("ssuIds", verifySsu.getSsuIds());

		// 调用远程接口
		Map<String, Object> result = this.postJson(url + method, params);

		// 出参
		List<SSUBasicInfoDto> data = new ArrayList<SSUBasicInfoDto>();
		try {

			JSONArray pruductArray = (JSONArray) result.get("data");

			data = JSONObject.parseArray(pruductArray.toJSONString(), SSUBasicInfoDto.class);
		} catch (Throwable te) {
			String inJson = JsonUtil.toJson(params);
			String outJson = JsonUtil.toJson(result);
			log.error("调用接口[{urlMethod}失败，入参[{inJson}],出参[{outJson}]".replace("{urlMethod}", url + method)
					.replace("{inJson}", inJson).replace("{outJson}", outJson));
			// 异常中，不添加url，只有method，防止抛给前端
			throw new BusinessException("调用接口[{method}失败，入参[{inJson}],出参[{outJson}]".replace("{method}", method)
					.replace("{inJson}", inJson).replace("{outJson}", outJson));
		}
		return data;
	}

}
