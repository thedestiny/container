package com.it.service;

/**
 * Created by xieyue on 2016/7/5.
 * BookService
 */


import com.it.mapper.BookMapper;
import com.it.pojo.Book;
import com.it.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Inject
    private BookMapper bookMapper;
    // map 为搜索条件的集合 title press author start bars
    // 书名 出版社 作者 起始页

    public List<Book> findBooks(Map<String, Object> map) {
        logger.debug(" execute BookService findBooks");
        return bookMapper.findBooks(map);
    }

    public int queryCount(Map<String, Object> map) {
        long num = bookMapper.queryCount(map);
        return (int) num;
    }

    public Page findBook(Map<String, Object> map) {
        int totalBars = queryCount(map);
        int bars = 6;
        int pages = Integer.parseInt(map.get("page").toString());
        // page 总条数/每页几条/起始页
        Page page = new Page(totalBars, bars, pages);
        map.put("start", page.getStart());
        map.put("bars",bars);
        page.setBookList(bookMapper.findBooks(map));
        return page;
    }


    public boolean delBookById(Integer id){
        return bookMapper.delBookById(id) == 1 ;
    }


    public Boolean insertBook(Book book) {
        return bookMapper.insertBook(book) == 1;
    }

    public Boolean updateBook(Book book){
        return bookMapper.updateBook(book) == 1;
    }


    public Book findBookById(Integer id) {
        return bookMapper.findBookById(id);
    }

    public List<Book> findByDataTables(Map<String, Object> param) {
        return bookMapper.findByDatables(param);
    }

    public long tableCount() {
        return bookMapper.tableCount();
    }

    public long countByKeyWord(String keyword) {
        return bookMapper.countByKeyWord(keyword);
    }

    public Book findBookByCode(String code) {
        return bookMapper.findBookByCode(code);
    }
}
