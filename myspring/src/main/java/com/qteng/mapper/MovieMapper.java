package com.qteng.mapper;

import com.qteng.pojo.Movie;

import java.util.List;

/**
 * Created by xieyue on 2016/7/1.
 * MovieMapper
 */
public interface MovieMapper {

    void insertMovie(Movie movie);
    void deleteMovie(Integer id);
    Movie queryMovieByName(String film);
    List<Movie> queryAllMovie();
    Long queryTotal();


}
