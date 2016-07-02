package com.it.service;

/**
 * Created by xieyue on 2016/7/2.
 */



import com.it.mapper.BookMapper;
import com.it.pojo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }


    public void borrowBook(){
        logger.debug(" execute BookService borrowBook");
        Book book = new Book();
        book.setAuthor("jim");
        book.setTitle("平凡的世界");
        bookMapper.saveBook(book);
        bookMapper.updateBook(book);

    }

}
