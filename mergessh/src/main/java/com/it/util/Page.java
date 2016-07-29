package com.it.util;

import java.util.List;

public class Page<T> {

    // 记录总条数、每页显示条数、起始页、总页数
    private Integer totalBars;
    private Integer bars;
    private Integer startPage;
    private Integer start;
    private Integer totalPages;
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }



    public Page(Integer totalBars, Integer bars, Integer startPage) {
        this.totalBars = totalBars;
        this.bars = bars;
        this.startPage = startPage;
        int num = totalBars / bars;
        this.totalPages = totalBars % bars == 0 ? num : num + 1;
        if (startPage > this.totalPages) {
            this.startPage = this.totalPages;
        }
        this.start = this.startPage * this.bars - this.bars;

    }

    public Page() {
    }




    public Integer getTotalBars() {
        return totalBars;
    }

    public void setTotalBars(Integer totalBars) {
        this.totalBars = totalBars;
    }

    public Integer getBars() {
        return bars;
    }

    public void setBars(Integer bars) {
        this.bars = bars;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }


}
