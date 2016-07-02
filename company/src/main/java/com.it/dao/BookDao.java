package com.it.dao;

/**
 * Created by xieyue on 2016/7/2.
 */


import com.it.mapper.BookMapper;
import com.it.pojo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookDao implements BookMapper {

    Logger logger = LoggerFactory.getLogger(BookDao.class);

    public String saveBook(Book book) {
        logger.debug("book title is {}",book.getTitle());
        return "123456";
    }

    public String updateBook(Book book) {
        logger.debug("book author is {}",book.getAuthor());
        return "789456";
    }

    public void init(){
        logger.debug("this is BookDao init");
    }

    public void destroy(){
        logger.debug("this is BookDao destory");
    }





}
