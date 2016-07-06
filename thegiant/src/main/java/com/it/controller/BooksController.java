package com.it.controller;

/**
 * Created by xieyue on 2016/7/5.
 * BooksController
 */


import com.google.common.collect.Maps;
import com.it.pojo.Book;
import com.it.service.BookService;
import com.it.utils.TransCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                               @RequestParam(required = false,defaultValue = "1") Integer page,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) String press,
                               @RequestParam(required = false) String author){
        Map<String,Object> map = Maps.newHashMap();
        map.put("title", TransCode.toUTF8(title));
        map.put("page",page);
        map.put("press",TransCode.toUTF8(press));
        map.put("author",TransCode.toUTF8(author));
        logger.debug("page is {}",page);
        model.addAttribute("page",bookService.findBook(map));
        model.addAttribute("rtitle", TransCode.toUTF8(title));
        model.addAttribute("rpress",TransCode.toUTF8(press));
        model.addAttribute("rauthor",TransCode.toUTF8(author));
        return "books/list";
    }

    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    public String deleteBook(@PathVariable Integer id,
                             RedirectAttributes redirectAttributes){
        Boolean flag = bookService.delBookById(id);
        redirectAttributes.addFlashAttribute("message",flag ? "删除成功":"删除失败");
        return "redirect:/books";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String insertBook(){
        return "books/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String insertBook(Book book,RedirectAttributes redirectAttributes){
        Boolean flag = bookService.insertBook(book);
        redirectAttributes.addFlashAttribute("message",flag ? "添加成功":"添加失败");
        return "redirect:/books";
    }

    @RequestMapping(value = "/update/{id:\\d+}",method = RequestMethod.GET)
    public String updateBook(@PathVariable Integer id,Model model){
        model.addAttribute("book", bookService.findBookById(id));
        return "books/edit";
    }

    @RequestMapping(value = "/update/{id:\\d+}",method = RequestMethod.POST)
    public String updateBook(@PathVariable Integer id,Book book,RedirectAttributes redirectAttributes){
        Boolean flag = bookService.updateBook(book);
        redirectAttributes.addFlashAttribute("message",flag ? "修改成功":"修改失败");
        return "redirect:/books";
    }






}
