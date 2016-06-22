package com.it.dao;

import com.it.entity.Film;
import com.it.utils.Dbhelp;
import com.it.utils.SmallUtils;

/**
 * Created by xieyue on 2016/6/22.
 * FilmDao
 */
public class FilmDao {

    public Integer insert(Film film) {
        String sql = "insert into film ( title, director, players, type, " +
                "nation, screen, score ) values(?,?,?,?,?,?,?)";
        return Dbhelp.update(sql, SmallUtils.helpGet(film, sql));
    }

}
