package com.qdsg.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.management.resources.agent;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Aweika_Fang
 * @date: 2020/9/17
 * @description:
 */
@Component
public class EasyExcelExportUtil {

    /**
     * 转为静态方便调用
     */
    private static HttpServletRequest request;
    private static HttpServletResponse response;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @PostConstruct
    public void init() {
        EasyExcelExportUtil.request = httpServletRequest;
        EasyExcelExportUtil.response = httpServletResponse;
    }

    /**
     * excel导出 ,如果有多个需要导出多个sheet,则往map中添加多个数据即可,注意: Class的泛型需要与List的泛型一致
     *
     * 举例:
     *     HashMap<Class, List> hashMap = new HashMap<>();
     *     hashMap.put(DemoData.class,new ArrayList<DemoData>());
     *     hashMap.put(WidthAndHeightData.class,new ArrayList<WidthAndHeightData>());
     * @param map  数据集合
     * @param fileName 导出文件名
     */
    public static  void export(Map<Class, List> map, String fileName) {
        Set<Map.Entry<Class, List>> entries = map.entrySet();
        /*
        检查传入数据泛型是否匹配
         */
        for (Map.Entry<Class, List> entry : entries) {
            Class entryKey = entry.getKey();
            List entryValue = entry.getValue();
            //数据非空
            if (!CollectionUtils.isEmpty(entryValue) && entryKey != entryValue.get(0).getClass() ){
                throw new RuntimeException("放入map中的class对象和list对象泛型不匹配");
            }
        }
        //输出对象
        ExcelWriter excelWriter = null ;
        try {
            fileName = fileName + ".xlsx";
            //火狐浏览器处理编码方式不太一样，用request区分
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                String fname = URLEncoder.encode(fileName, "UTF-8");
                //设置服务器端编码
                response.setCharacterEncoding("utf-8");
                //设置浏览器端解码
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-disposition", "attachment; filename=" + fname);
            }
            excelWriter = EasyExcel.write(response.getOutputStream()).build();
            int num = 0;//计数 sheet页码
            for (Map.Entry<Class, List> entry : entries) {
                Class entryKey = entry.getKey();
                List entryValue = entry.getValue();
                WriteSheet writeSheet = EasyExcel.writerSheet(num, "模板" + num).head(entryKey).build();
                excelWriter.write(entryValue,writeSheet);
                num++;
            }
        } catch (Exception e) {
            //记录日志
            System.out.println(e);
        }finally {
            // 千万别忘记finish 会帮忙关闭流 并将数据通过流写出去
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
