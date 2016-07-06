package com.it.mapper;

import com.it.pojo.Book;
import org.aspectj.util.LangUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/7/1.
 * BookMapper
 */
public interface BookMapper {

    List<Book> findBooks(Map<String, Object> map);

    long queryCount(Map<String, Object> map);

    Integer delBookById(Integer id);

    Integer insertBook(Book book);

    Integer updateBook(Book book);

    Book findBookById(Integer id);
}
