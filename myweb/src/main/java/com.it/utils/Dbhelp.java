package com.it.utils;

import com.it.exception.DataAccessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by xieyue on 2016/6/15.
 */
public class Dbhelp {

    private static Logger logger = LoggerFactory.getLogger(Dbhelp.class);
    /**
     * update 用于增删改
     * @param sql 查询语句
     * @param parameters 参数
     * @return 受影响行数
     */

    public static int update(String sql, Object... parameters) {
        // 可以直接传入DataSource,或者传入数据库连接
        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
        try {
            logger.debug("execute update in Dbhelp");
            return queryRunner.update(sql, parameters);
        } catch (SQLException e) {
            throw new DataAccessException("execute sql" + sql + "encounter exception", e);
        }
    }

    /**
     * query 用于查询返回结果
     * @param sql 查询语句
     * @param handler 结果集 BeanHandler BeanListHandler MapHandler MapListHandler
     *                ScalarHandler(一行一列) ColumnListHandler 多行一列
     * @param parameters 参数
     * @param <T> 返回类型
     * @return 返回结果
     */
    public static <T> T query(String sql, ResultSetHandler<T> handler, Object... parameters){
        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
       // Connection connection = null;
        try {
          //  connection = ConnectionManager.getConnection();
            logger.debug("execute query in Dbhelp");
            return  queryRunner.query(sql,handler,parameters);
        } catch (SQLException e) {
            throw new DataAccessException("execute sql" + sql + "encounter exception", e);
        } finally {
           // ConnectionManager.closeConnection(connection);
        }
    }
}
