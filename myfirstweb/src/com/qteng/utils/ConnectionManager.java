package com.qteng.utils;

import com.qteng.exception.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by xieyue on 2016/6/8.
 */
public class ConnectionManager {
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        Properties properties = new Properties();

        try {
            properties.load(ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initsize", "5")));
        dataSource.setMaxTotal(Integer.parseInt(properties.getProperty("jdbc.maxsize", "20")));
        dataSource.setMaxWaitMillis(Integer.parseInt(properties.getProperty("jdbc.maxwait", "5000")));
        dataSource.setMaxIdle(Integer.parseInt(properties.getProperty("jdbc.maxidle", "10")));
        dataSource.setMinIdle(Integer.parseInt(properties.getProperty("jdbc.minidle", "5")));
    }

    public static DataSource getDataSource(){
        return dataSource;
    }




    public static Connection getConnection() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            return DriverManager.getConnection("jdbc:mysql:///qteng", "root", "lky86");
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new DataAccessException("数据库连接异常", e);
        }
    }


    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DataAccessException("数据库关闭异常", e);
        }
    }
}
