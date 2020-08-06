package com.aweika.common.utils;

import com.yscredit.jsmj.config.WebConfig;
import com.yscredit.jsmj.service.EntForOpenService;

import java.util.List;
import java.util.Map;

/**
 * @author: Michael
 * @date: 2020/5/20
 * @description: 通过工商接口获取企业详细信息
 */
public class BusinessApi {

    /**
     * 获取企业信息 若不匹配则回转NULL
     * @param creditCode 统代
     * @return
     */
    public static Map<String,String> getEntForOpen(String creditCode){
        EntForOpenService entForOpenService = ApplicationContextUtil.getApplicationContext().getBean(EntForOpenService.class);
        Map<String,Object> map = entForOpenService.getEntForOpen(creditCode, WebConfig.OPEN_UID, WebConfig.OPEN_URL, WebConfig.OPEN_KEY);
        if(map.get("code") == null || !"0000".equals(map.get("code").toString())){
            return null;
        }
        Map ls = (Map) map.get("data");
        List<Map<String,String>> msg = (List<Map<String,String>>) ls.get("basicList");
        return msg.get(0);
    }
}
