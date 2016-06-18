package com.it;

import com.it.dao.MovieDao;
import com.it.entity.Movie;
import org.junit.Test;

import java.util.List;

/**
 * Created by xieyue on 2016/6/17.
 *
 */
public class MovieDaoTest {
    private MovieDao movieDao = new MovieDao();

    @Test
    public void getMovieTotalTest() {
        MovieDao.getMovieTotal("123");
    }

    @Test
    public void findMovieTest() {
        List<Movie> movieList = movieDao.findMovie(10, 10,"10");
        for (Movie movie : movieList) {
            System.out.println(movie.getCount());
        }
    }

}
