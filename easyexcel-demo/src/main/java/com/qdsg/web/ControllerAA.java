package com.qdsg.web;

import com.qdsg.data.ComplexHeadData;
import com.qdsg.data.DemoData;
import com.qdsg.data.WidthAndHeightData;
import com.qdsg.util.EasyExcelExportUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Aweika_Fang
 * @date: 2020/9/17
 * @description:
 */
@RequestMapping
@RestController
public class ControllerAA {

    @GetMapping("testExport")
    public Object testExport(){
        HashMap<Class, List> hashMap = new HashMap<>();
        hashMap.put(DemoData.class,data());
        hashMap.put(WidthAndHeightData.class,data2());
        EasyExcelExportUtil.export(hashMap,"aaa");
        return "成功";
    }

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i+1);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


    private List<WidthAndHeightData> data2() {
        List<WidthAndHeightData> list = new ArrayList<WidthAndHeightData>();
        for (int i = 0; i < 10; i++) {
            WidthAndHeightData data = new WidthAndHeightData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(22D);
            list.add(data);
        }
        return list;
    }

    public static void main(String[] args) {
        Field[] fields = ComplexHeadData.class.getDeclaredFields();
        Field[] field2 = ComplexHeadData.class.getDeclaredFields();
        System.out.println(fields[0] == field2[0]);
        System.out.println("aa");

    }
}
