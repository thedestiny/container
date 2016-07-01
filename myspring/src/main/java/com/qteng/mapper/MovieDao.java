package com.qteng.mapper;

/**
 * Created by xieyue on 2016/7/1.
 * MovieDao
 */


import com.qteng.pojo.Movie;
import com.qteng.util.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;

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
        logger.debug("execute MovieDao insertMovie");
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
        String sql = " SELECT * from movie WHERE film = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Movie>() {
            @Override
            public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
                Movie movie = new Movie();
                movie.setFilm(resultSet.getString("film"));
                movie.setRate(resultSet.getFloat("rate"));
                movie.setIssue(resultSet.getString("issue"));
                movie.setScreen(resultSet.getString("director"));
                movie.setSummary(resultSet.getString("summary"));
                return movie;
            }
        },film);
    }


    // 不论是单个对象还是多个对象的集合，都用new BeanPropertyRowMapper
    // 单个对象用queryObject 多个对象用query
    @Override
    public List<Movie> queryAllMovie() {
        String sql = "select * from movie ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Movie.class));
    }

    @Override
    public Long queryTotal() {
        String sql = " select count(*) from movie";
        return jdbcTemplate.queryForObject(sql, new SingleColumnRowMapper<Long>());
    }


}
