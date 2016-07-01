package com.qteng.mapper;

/**
 * Created by xieyue on 2016/7/1.
 * MovieDao
 */


import com.qteng.pojo.Movie;
import com.qteng.util.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Named
public class MovieDao implements MovieMapper{
    Logger logger = LoggerFactory.getLogger(MovieDao.class);

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertMovie(Movie movie) {
        String sql = " insert into movie ( film, rate, issue, screen, director,summary) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, SmallUtils.helpGet(movie, sql));
    }

    @Override
    public void deleteMovie(Integer id) {
        String sql = " delete from movie where id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Movie queryMovieByName(String film) {
        return jdbcTemplate.queryForObject(sql, new RowMapper<Movie>() {
            @Override
            public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
                

                return movie;
            }
        },film);
    }

    @Override
    public List<Movie> queryAllMovie() {
        return null;
    }

    @Override
    public Long queryTotal() {
        return null;
    }


}
