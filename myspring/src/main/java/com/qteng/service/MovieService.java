package com.qteng.service;

/**
 * Created by xieyue on 2016/7/1.
 * MovieService
 */


import com.qteng.mapper.MovieMapper;
import com.qteng.pojo.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Transactional
public class MovieService {
    Logger logger = LoggerFactory.getLogger(MovieService.class);


    // Service调用mapper的是接口的实现类
    @Inject
    private MovieMapper movieMapper;
    // @Transactional(readOnly = true)
    @Transactional(rollbackFor = Exception.class) // default is RuntimeException
    public void insertMovie(Movie movie){
        logger.debug(" execute MovieServiceTest insertMovie");
        movieMapper.insertMovie(movie);
//        if(1==1){
//           throw new RuntimeException("12346456");
//        }


    }



}
