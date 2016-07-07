package com.it.controller;

/**
 * Created by xieyue on 2016/7/6.
 */


import com.google.common.collect.Maps;
import com.it.pojo.Book;
import com.it.service.BookService;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/table")
public class TableController {
    Logger logger = LoggerFactory.getLogger(TableController.class);

    @Inject
    private BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getBookPage() {
        return "table/list";
    }


    @RequestMapping(value = "/data.json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBooks(HttpServletRequest request) {

        String draw = request.getParameter("draw");
        String start = request.getParameter("start"); //当前页偏移量
        String length = request.getParameter("length"); //每页显示多少条数据
        String keyword = request.getParameter("search[value]"); //搜索框中的值
        keyword = SmallUtils.transtoUTF8(keyword);
        length = length == null ? "10" : length;
        start = start == null ? "0" : start;
        String sortColumnIndex = request.getParameter("order[0][column]"); //获取排序列的索引
        String sortColumnName = request.getParameter("columns[" + sortColumnIndex + "][name]"); //根据排序列的索引获取列的名字
        String sortType = request.getParameter("order[0][dir]");//排序方式 asc | desc

        Map<String, Object> param = Maps.newHashMap();
        param.put("start", start);
        param.put("length", length);
        param.put("keyword", keyword);
        param.put("sortColumn", sortColumnName);
        param.put("sortType", sortType);

        List<Book> bookList = bookService.findByDataTables(param);
        Map<String, Object> result = Maps.newHashMap();
        result.put("draw", draw);
        result.put("recordsTotal", bookService.tableCount());
        result.put("recordsFiltered", bookService.countByKeyWord(keyword));
        result.put("data", bookList);
        logger.debug("result is {}", result);
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addBook(Book book) {
        Boolean flag = bookService.insertBook(book);
        return flag ? "success" : "failure";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book editBook(@PathVariable Integer id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editBook(Book book) {
        Boolean flag = bookService.updateBook(book);
        return flag ? "success" : "failure";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delBook(@PathVariable Integer id) {
        Boolean flag = bookService.delBookById(id);
        return flag ? "success" : "failure";
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    @ResponseBody
    public String findBook(String code) {
        Book book = bookService.findBookByCode(code);
        return book == null ? "true" : "false";
    }


}
