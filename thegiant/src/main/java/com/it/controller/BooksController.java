package com.it.controller;

/**
 * Created by xieyue on 2016/7/5.
 * BooksController
 */


import com.google.common.collect.Maps;
import com.it.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BooksController {
    Logger logger = LoggerFactory.getLogger(BooksController.class);

    @Inject
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getBooksList(Model model,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) String press,
                               @RequestParam(required = false) String author){
        Map<String,Object> map = Maps.newHashMap();
        map.put("title",title);
        map.put("page",page);
        map.put("press",press);
        map.put("author",author);
        model.addAttribute("page",bookService.findBook(map));
        return "books/list";
    }





}
