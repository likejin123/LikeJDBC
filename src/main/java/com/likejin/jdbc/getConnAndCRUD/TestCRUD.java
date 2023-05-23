package com.likejin.jdbc.getConnAndCRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestCRUD {

    public static void main(String[] args) {
        selectByStatement();

    }

    /*
     * @Description 测试sql注入问题
     * @param 
     * @return void
     **/
    public static void selectByStatement() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = MyConnection.getConnection();
            statement = connection.createStatement();
            //sql注入测试
            //preparement能解决是因为
            //SQL语句已经被数据库分析，编译和优化，对应的执行计划也会缓存下来并允许数据库以参数化的形式进行查询
            //即后面只需要传入参数即可 传入其他也只会被看做参数
            String id = "10" + " and name = 西游记";
            //String id = "10";
            String sql = "select * from t_book where id > " + id;
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getObject(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}

