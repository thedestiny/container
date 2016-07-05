package com.it.utils;

import com.it.pojo.Book;

import java.util.List;

/**
 * Created by xieyue on 2016/7/5.
 */


public class Page {

    private Integer totalBars;
    private Integer bars;
    private Integer startPage;
    private Integer start;
    private Integer totalPages;
    private List<Book> bookList;

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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
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
