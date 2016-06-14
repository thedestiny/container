package com.qteng.utils;

import com.qteng.exception.DataAccessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by xieyue on 2016/6/8.
 */
public class Dbhelper {

    public static void update(String sql, Object...  params) {
        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());

        try {
            queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new DataAccessException("execute " + sql + " encounter exception: ", e);
        }

    }

    public static <T> T query(String sql, ResultSetHandler<T> handler, Object... params) {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            return queryRunner.query(connection, sql, handler, params);
        } catch (Exception e) {
            throw new DataAccessException("execute " + sql + " encounter exception: ", e);
        } finally {
            ConnectionManager.closeConnection(connection);
        }

    }


}
