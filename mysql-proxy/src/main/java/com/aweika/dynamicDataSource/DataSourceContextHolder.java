package com.aweika.dynamicDataSource;

/**
 * @author: Michael
 * @date: 2020/7/10
 * @description: ThreadLocal保存数据源的信息到每个线程中，方便我们需要时获取
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> DYNAMIC_DATASOURCE_CONTEXT = new ThreadLocal<>();

    public static void set(String datasourceType) {
        DYNAMIC_DATASOURCE_CONTEXT.set(datasourceType);
    }
    public static String get() {
        return DYNAMIC_DATASOURCE_CONTEXT.get();
    }
    public static void clear() {
        DYNAMIC_DATASOURCE_CONTEXT.remove();
    }
}
