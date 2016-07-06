package com.it.service;

/**
 * Created by xieyue on 2016/7/5.
 */


import com.google.common.collect.Maps;
import com.it.pojo.Book;
import com.it.utils.TransCode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class BookServiceTestCase {
    Logger logger = LoggerFactory.getLogger(BookServiceTestCase.class);

    @Inject
    private BookService bookService;

    @Test
    public void findBooksTest(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("start",10);
        map.put("bars",10);
        map.put("title", TransCode.toUTF8("红"));
        List<Book> bookList = bookService.findBooks(map);
        Assert.assertNotNull(bookList);
    }






}
