package com.aweika.dynamicDataSource;

/**
 * @author: Michael
 * @date: 2020/7/4
 * @description:
 */
public enum DynamicDataSourceEnum {

    MASTER("master"), SLAVE("slave");

    private String dataSourceName;

    DynamicDataSourceEnum(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public DynamicDataSourceEnum setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
        return this;
    }
}
