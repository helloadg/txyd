package com.mc.common.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CrmRpc extends BaseRpc {
    private static Logger log = LoggerFactory.getLogger(CrmRpc.class);
//
//    public int getCompanyLevel(long companyId){
//    	Map<String,Object> param = new HashMap<String,Object>();
//    	List<Long> idsArray = new ArrayList<Long>();
//    	idsArray.add(companyId);
//    	param.put("idsArr",idsArray);
//    	JSONObject jsonObjec = this.postJson(getRpcConfig().getCrmUrl()+"/crmapi/companyRtSum/getCompanyActiveDegreeByCompanyIds",param);
//    	String ret = jsonObjec.getString("ret");
//    	//默认最高级别，目的是如果接口挂了，还可以中奖
//    	int dengji = 5;
//
//    	if(Integer.parseInt(ret) == 0){
//    		//throw new SystemException("商户接口ret=0,"+JSON.toJSONString(jsonObjec));
//    		this.log.error("商户接口ret=0,"+JSON.toJSONString(jsonObjec));
//    	}
//
//    	JSONArray array = jsonObjec.getJSONArray("data");
//    	if(array == null || array.size() == 0){
//    		//throw new SystemException("商户接口ret=1，但是商户等级不存在,"+JSON.toJSONString(jsonObjec));
//    		this.log.error("商户接口ret=1，但是商户等级不存在,"+JSON.toJSONString(jsonObjec));
//    	}else{
//        	JSONObject json = array.getJSONObject(0);
//        	dengji = Integer.parseInt(json.getString("active_degree"));
//    	}
//
//    	return dengji;
//    }
}
