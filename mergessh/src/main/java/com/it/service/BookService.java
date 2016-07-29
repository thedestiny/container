package com.it.service;


import com.it.dao.BookDao;
import com.it.dao.PublisherDao;
import com.it.dao.TypeDao;
import com.it.pojo.Book;
import com.it.pojo.Publisher;
import com.it.pojo.Search;
import com.it.pojo.Type;
import com.it.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;


@Named
@Transactional
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookDao bookDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private PublisherDao publisherDao;

    public List<Book> findAllBooks(){
        return bookDao.queryAll();
    }

    public List<Type> findAllTypes(){
        return typeDao.queryAll();
    }

    public Page<Book> findByPageNo(Integer p, List<Search> searchList){
        // 每页5条数据
        if(searchList.size() == 0){
            return bookDao.findByPageNumber(p,5);
        }
        return bookDao.findByPageNumber(p,5,searchList);
    }

    public List<Publisher> findAllPress(){
        return publisherDao.queryAll();
    }

    public void saveBook(Book book){
        bookDao.save(book);
    }

    public void updateBook(Book book){
        bookDao.update(book);
    }
    public Book findBookById(Integer id){
        return bookDao.findById(id);
    }

    public void deleteBook(Book book){
        bookDao.delete(book);
    }

    public void deleteBook(Integer id){
        bookDao.delete(id);
    }





}
