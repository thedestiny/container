package com.it.dao;

import com.it.entity.Movie;
import com.it.utils.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by xieyue on 2016/6/17.
 *
 */
public class MovieDao {

    Logger logger = LoggerFactory.getLogger(MovieDao.class);

    public static Long getMovieTotal() {
        String sql = "select count(*) from movie";
        return Dbhelp.query(sql, new ScalarHandler<Long>());
    }

    public List<Movie> findMovie(int startNum, int limitNum) {
        logger.debug("execute findMovieById in MovieDao");
        String sql = "select * from movie limit ?, ? ";
        return Dbhelp.query(sql, new BeanListHandler<Movie>(Movie.class), startNum, limitNum);
    }


}
