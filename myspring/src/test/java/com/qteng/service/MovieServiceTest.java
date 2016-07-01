package com.qteng.service;

/**
 * Created by xieyue on 2016/7/1.
 */


import com.qteng.pojo.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class MovieServiceTest {
    Logger logger = LoggerFactory.getLogger(MovieServiceTest.class);

    @Inject
    private MovieService movieService;

    @Test
    public void insertMovieTest(){
        logger.debug("execute MovieServiceTest insertMovieTest ");
        Movie movie = new Movie("flash",9.6F,"2016","2016","queen","the flash movie");
        movieService.insertMovie(movie);

    }
}
