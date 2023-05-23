package com.likejin.jdbc.getConnAndCRUD;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.cj.jdbc.Driver;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

;
/**
 * @Description  获取数据库连接的三种不同方式
 * @Author  李柯锦
 * @Date
 */
public class MyConnection {

    public static void main(String[] args) {
        getConn1();
        getConn2();
        System.out.println(getConnection());
    }
    /*
     * @Description 利用数据库驱动获得数据库连接
     * @param
     * @return void
     **/
    public static void getConn1(){
        Driver driver = null;
        Connection connect = null;
        try {
            driver = new Driver();
            String url = "jdbc:mysql://localhost:3306/db_qby?serverTimezone=GMT%2B8&characterEncoding=utf-8";
            String user = "root";
            String password = "abc123";
            Properties properties = new Properties();
            properties.setProperty("user",user);
            properties.setProperty("password",password);
            connect = driver.connect(url, properties);
            System.out.println(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * @Description driverManger实现数据库连接
     * @param
     * @return Connection
     **/
    public static Connection getConn2(){
        InputStream is = null;
        Connection connection = null;
        try {
            is = MyConnection.class.getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(is);
            String user = properties.getProperty("username");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClassName");

            //new Driver()也可
            Class.forName(driverClass);

            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    /*
     * @Description druid实现数据库连接
     * @param
     * @return Connection
     **/
    public static Connection getConnection(){
        InputStream is = MyConnection.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        Connection connection = null;
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
