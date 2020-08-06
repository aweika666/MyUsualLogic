package com.aweika.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

	private final static Logger logger = LogManager.getLogger(JsonUtil.class);
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String args) {
		Map<String, Object> argsMap = null;
		try {
			ObjectMapper om = new ObjectMapper();
	    	argsMap = om.readValue(args, Map.class);
		} catch(Exception ex) {
			argsMap = new HashMap<String, Object>();
		}
		
		return argsMap;
	}
	
	
	
	public static String toJsonString(Object obj) {
		if(obj == null) {
			return "";
		}
		ObjectMapper om = new ObjectMapper();
		try {
			om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			return om.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("转Json出错。。。。。。");
			logger.error("error msg:", e);
			return null;
		}
	}


	public static <T> T fromString(String jsonString, Class<T> c) {
		ObjectMapper om = new ObjectMapper();

		try {
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return om.readValue(jsonString, c);
		} catch (Exception var4) {
			return null;
		}
	}
//
//	public static <T> List<T> toList(String jsonString, Class<T> c) {
//		ObjectMapper om = new ObjectMapper();
//		TypeFactory typeFactory = TypeFactory.defaultInstance();
//		try {
//		    //当字段不匹配时，保证转化其他字段能够正常转化
//		    om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			return om.readValue(jsonString, typeFactory.constructCollectionType(List.class, c));
//		} catch (Exception e) {
//			logger.error("Json转对象出错，jsonString=" + jsonString);
//			logger.error("error msg:", e);
//			return null;
//		}
//	}
}
