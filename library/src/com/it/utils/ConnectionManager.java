package com.it.utils;

import com.it.exception.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by xieyue on 2016/6/15.
 * ConnectionManager
 */
public class ConnectionManager {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName(Config.get("jdbc.driver"));
        dataSource.setUrl(Config.get("jdbc.url"));
        dataSource.setUsername(Config.get("jdbc.username"));
        dataSource.setPassword(Config.get("jdbc.password"));
        dataSource.setInitialSize(Integer.parseInt(Config.get("jdbc.initsize", "5")));
        dataSource.setMaxTotal(Integer.parseInt(Config.get("jdbc.maxsize", "20")));
        dataSource.setMaxWaitMillis(Integer.parseInt(Config.get("jdbc.maxwait", "5000")));
        dataSource.setMaxIdle(Integer.parseInt(Config.get("jdbc.maxidle", "10")));
        dataSource.setMinIdle(Integer.parseInt(Config.get("jdbc.minidle", "5")));

//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql:///library");
//        dataSource.setUsername("root");
//        dataSource.setPassword("lky86");
//        dataSource.setInitialSize(5);
//        dataSource.setMaxTotal(20);
//        dataSource.setMaxWaitMillis(10000);
//        dataSource.setMaxIdle(10);
//        dataSource.setMinIdle(5);
    }
    // import javax.sql package
    public static DataSource getDataSource(){
        return  dataSource;
    }


    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DataAccessException("数据库连接异常",e);
        }
    }

    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DataAccessException("数据库关闭异常",e);
        }
    }

}
