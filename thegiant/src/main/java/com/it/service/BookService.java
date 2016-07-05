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
        int pages = 1;
        // page 总条数/每页几条/起始页
        Page page = new Page(totalBars, bars, pages);
        int start = (pages - 1) * bars;
        map.put("start", start);
        map.put("bars",bars);
        page.setBookList(bookMapper.findBooks(map));
        return page;
    }


}
