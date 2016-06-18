package com.it.service;

import java.util.List;

/**
 * Created by xieyue on 2016/6/17.
 * Page
 */
public class Page<T> {
    /**
     * totalSize 记录总计
     * totalPage 页数总计
     * pageNum  当前页码
     * pageSize 每页记录显示条数
     * items    当前页数据
     * startNum 当前页其实行数
     */
    private Integer totalSize;
    private Integer totalPage;
    private Integer pageNum;
    private Integer pageSize;
    private List<T> items;
    private Integer startNum;
    private String search;

    public Page(Integer pageNum, Integer pageSize, Integer totalSize, String search) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        int temp = totalSize / pageSize;
        this.totalPage = totalSize % pageSize == 0 ? temp : temp + 1;
        this.pageNum = pageNum > totalPage ? totalPage : pageNum;
        this.pageNum = pageNum <= 0 ? 1 : pageNum;
        this.startNum = (this.pageNum - 1) * pageSize;
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
