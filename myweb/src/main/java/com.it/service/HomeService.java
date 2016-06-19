package com.it.service;

import com.it.dao.MovieDao;
import com.it.entity.Movie;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by xieyue on 2016/6/17.
 * HomeService
 */
public class HomeService {
    private MovieDao movieDao = new MovieDao();

    public Page<Movie> showFilm(Integer startNum, String search) {
        // 每页展示数目
        int pageNum = 10;
        if (StringUtils.isNotEmpty(search)) {
//            try {
//               search = new String(search.getBytes("ISO8859-1"), "utf-8");
//            } catch (Exception e) {
//                throw new RuntimeException("转换异常", e);
//            }
        }
        int totalSize = movieDao.getMovieTotal(search).intValue();
        System.out.println(search);
        Page<Movie> page = new Page<>(startNum, pageNum, totalSize, search);
        List<Movie> movieList = movieDao.findMovie(page.getStartNum(), pageNum, search);
        page.setItems(movieList);
        return page;
    }


}
