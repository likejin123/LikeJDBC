package com.likejin.jdbc.getConnAndCRUD;

import com.likejin.jdbc.entity.Book;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    public static void main(String[] args) {
        try {
            List<Book> list = select(new Book(), "select * from t_book where id >?", 10);
            for(Book book : list){
                System.out.println(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * @Description 通用的查询操作
     * @param obj
     * @param sql
     * @param args
     * @return List
     **/
    public static List select(Object obj,String sql,Object ...args) throws SQLException {
        Connection connection = MyConnection.getConnection();
        ArrayList arrayList = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            arrayList = new ArrayList();
            int t = 1;
            while(t <= args.length){
                preparedStatement.setObject(t,args[t-1]);
                t = t + 1;
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            Class<?> clazz = obj.getClass();
            int columnCount = metaData.getColumnCount();
            while(resultSet.next()){
                t = 1;
                Object o = clazz.newInstance();
                while(t <= columnCount){
                    Object columnValue = resultSet.getObject(t);
                    String columnName = metaData.getColumnName(t);
                    Field declaredField = clazz.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(o,columnValue);
                    t = t + 1;
                }
                arrayList.add(o);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return arrayList;
    }
}
