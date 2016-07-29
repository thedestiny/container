package com.it.controller;


import com.it.pojo.Book;
import com.it.pojo.Publisher;
import com.it.pojo.Search;
import com.it.pojo.Type;
import com.it.service.BookService;
import com.it.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book",method = RequestMethod.GET)
    public String getAllBooks(@RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,
                              Model model,
                              HttpServletRequest request){
        List<Search> searchList = Search.getQueryParamList(request);
        Page<Book> page = bookService.findByPageNo(Integer.valueOf(pageNo),searchList);
        List<Type> typeList =bookService.findAllTypes();
        List<Publisher> publisherList = bookService.findAllPress();
        model.addAttribute("typeList",typeList);
        model.addAttribute("publisherList",publisherList);
        model.addAttribute("page",page);
        return "book/lists";
    }

    @RequestMapping(value = "/book/new",method = RequestMethod.POST)
    public String saveBook(Book book){
        bookService.saveBook(book);
        return "redirect:/book";
    }


    @RequestMapping(value = "/book/new",method = RequestMethod.GET)
    public String addNewBook(Model model){

        List<Type> typeList =bookService.findAllTypes();
        List<Publisher> publisherList = bookService.findAllPress();
        model.addAttribute("typeList",typeList);
        model.addAttribute("publisherList",publisherList);
        return "book/new";
    }

    @RequestMapping(value = "/book/{id:\\d+}/del",method = RequestMethod.GET)
    public String deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);
        return "redirect:/book";
    }
    @RequestMapping(value = "/book/{id:\\d+}/edit",method = RequestMethod.GET)
    public String updateBook(@PathVariable Integer id, Model model){

        Book book = bookService.findBookById(id);
        List<Type> typeList =bookService.findAllTypes();
        List<Publisher> publisherList = bookService.findAllPress();
        model.addAttribute("book",book);
        model.addAttribute("typeList",typeList);
        model.addAttribute("publisherList",publisherList);
        return "book/edit";
    }

    @RequestMapping(value = "/book/edit",method = RequestMethod.POST)
    public String updateBook(Book book){
        bookService.updateBook(book);
        return "redirect:/book";
    }




}
