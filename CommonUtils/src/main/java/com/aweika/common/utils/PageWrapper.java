package com.aweika.common.utils;


import com.github.pagehelper.PageInfo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 对分页的基本数据进行封装
 */
public class PageWrapper<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer pageNum = 1;//页码，默认是第一页
    @NotNull
    private Integer pageSize = 10;//每页显示的记录数，默认是10
    private Integer totalRecord;//总记录数
    private Integer totalPage;//总页数
    private List<T> results;//对应的当前页记录

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
        //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。

        if (totalRecord == 0) {
            this.setTotalPage(0);
        } else {
            Integer totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
            this.setTotalPage(totalPage);
        }
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
        if(null != results && results.size() == 0){
            return new ArrayList<T>();
        }
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.setPageSize(pageInfo.getPageSize());
        this.setPageNum(pageInfo.getPageNum());
        this.setResults(pageInfo.getList()== null ? new ArrayList<T>() : pageInfo.getList());
        this.setTotalRecord(new Long(pageInfo.getTotal()).intValue());
        this.setTotalPage(pageInfo.getPages());
    }

    public void setPageInfoForDto( PageInfo<T> pageInfo) {

        this.setResults(pageInfo.getList()== null ? new ArrayList<T>() : pageInfo.getList());
        this.setTotalRecord(new Long(pageInfo.getTotal()).intValue());
        this.setTotalPage(pageInfo.getPages());
    }

    public PageWrapper( PageInfo<T> pageInfo) {
        this.setPageSize(pageInfo.getPageSize());
        this.setPageNum(pageInfo.getPageNum());
        this.setResults(pageInfo.getList()== null ? new ArrayList<T>() : pageInfo.getList());
        this.setTotalRecord(new Long(pageInfo.getTotal()).intValue());
        this.setTotalPage(pageInfo.getPages());
    }

    public PageWrapper() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [pageNum=").append(pageNum).append(", pageSize=")
                .append(pageSize).append(", results=").append(results).append(
                ", totalPage=").append(totalPage).append(
                ", totalRecord=").append(totalRecord).append("]");
        return builder.toString();
    }
}