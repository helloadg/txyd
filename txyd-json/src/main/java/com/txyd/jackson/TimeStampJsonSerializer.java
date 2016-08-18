package com.txyd.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import txyd.util.TimeUtil;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/18.
 * 将时间戳转化为日期字符串
 */
public class TimeStampJsonSerializer  extends JsonSerializer<Integer> {
	@Override
	public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		if(integer!=null){
			Date date=TimeUtil.getDateByTimeStamp(integer);
			String dateStr=TimeUtil.toStr(date,TimeUtil.DATE_TIME_PATTERN);
			jsonGenerator.writeString(dateStr);
		}else{
			jsonGenerator.writeObject(null);
		}
		
	}
}
