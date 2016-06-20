package com.it.dao;

import com.it.entity.Movie;
import com.it.utils.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by xieyue on 2016/6/17.
 * MovieDao
 */
public class MovieDao {

    Logger logger = LoggerFactory.getLogger(MovieDao.class);

    public Long getMovieTotal() {
        String sql = "select count(*) from movie";
        return Dbhelp.query(sql, new ScalarHandler<Long>());
    }
    public Long getMovieTotal(String search) {
        String sql = "select count(*) from movie ";
        if(StringUtils.isNotEmpty(search) ){
            String condition = "where film like '%" + search +
                    "%' or director like '%" + search +
                    "%' or rate like '%" + search + "%' ";
            sql = sql + condition;
        }
        long num = Dbhelp.query(sql, new ScalarHandler<Long>());
        if (num == 0) {
            return getMovieTotal();
        } else {
            return num;
        }
    }
    public List<Movie> findMovie(int startNum, int limitNum, String search) {
        logger.debug("execute findMovieById in MovieDao");
        String sql = "select * from  movie limit ?, ? ";
        if (StringUtils.isNotEmpty(search)) {
            String condition = "where film like '%" + search +
                    "%' or director like '%" + search +
                    "%' or rate like '%" + search + "%' ";
            sql = "select * from  movie " + condition + " limit ?, ? ";
        }
        List<Movie> list = Dbhelp.query(sql, new BeanListHandler<Movie>(Movie.class), startNum, limitNum);
        if (list != null) {
            return list;
        } else {
            return findMovie(startNum, limitNum);
        }
    }

    private List<Movie> findMovie(int startNum, int limitNum) {
        logger.debug("execute findMovieById in MovieDao");
        String sql = "select * from  movie limit ?, ? ";
        return Dbhelp.query(sql, new BeanListHandler<>(Movie.class), startNum, limitNum);
    }
}
