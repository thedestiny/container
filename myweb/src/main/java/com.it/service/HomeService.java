package com.it.service;

import com.it.dao.MovieDao;
import com.it.entity.Movie;
import java.util.List;

/**
 * Created by xieyue on 2016/6/17.
 * HomeService
 */
public class HomeService {
    private MovieDao movieDao = new MovieDao();

    public List<Movie> showFilm(Integer startNum) {
        // 每页展示数目
        int pageNum = 10;
        int total = new Movie().getCount().intValue();
        int totalPage = total / pageNum + 1;
        if (total % pageNum == 0) {
            totalPage--;
        }
        if (startNum < 0) {
            startNum = 1;
        }
       return movieDao.findMovie(startNum, pageNum);
    }


}
