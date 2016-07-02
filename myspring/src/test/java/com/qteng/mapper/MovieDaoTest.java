package com.qteng.mapper;

/**
 * Created by xieyue on 2016/7/1.
 * MovieDaoTest
 */


import com.qteng.pojo.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class MovieDaoTest {

    Logger logger = LoggerFactory.getLogger(MovieDaoTest.class);


    private MovieMapper movieMapper;

    @Test
    public void insertMovieTest(){

        Movie movie = new Movie("flash",9.6F,"2016","2016","queen","the flash movie");
        movieMapper.insertMovie(movie);
    }

    @Test
    public void deleteMovieTest(){
        movieMapper.deleteMovie(1);
    }

    @Test
    public void queryMovieByName(){
        Movie movie = movieMapper.queryMovieByName("flash");
        logger.debug("movie is {}",movie);
    }

    @Test
    public void  queryAllMovieTest(){
        List<Movie> movieList = movieMapper.queryAllMovie();
        logger.debug(" movieList num is {}",movieList.size());
    }

    @Test
    public void queryTotalTest(){
        Long num = movieMapper.queryTotal();
        logger.debug("num is {}",num);
    }




}
