package com.likejin.jdbc.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 * @Description  客户端，模拟mysql客户端
 * @Author  李柯锦
 * @Date
 */
public class Client {
    public static void main(String[] args){
        Socket socket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try{
            //客户端请求连接后的socket对象,用于连接服务器，要指定服务器端的ip地址和端口号，而客户端油系统自动分配端口
            socket = new Socket("127.0.0.1", 8888);
            //用户客户端发送数据和接受服务器返回数据
             reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //模拟mysql的身份验证
            writer.write("user"+"\n");
            writer.flush();
            writer.write("abc123"+"\n");
            writer.flush();
            System.out.println("验证结果：" + reader.readLine());
            Scanner scanner = new Scanner(System.in);
            //模拟请求mysql
            while(true){
                System.out.println("请求的sql语句是");
                String str = scanner.next();
                if (str.equals("查询结束")) {
                    reader.close();
                    writer.close();
                    socket.close();
                }
                writer.write(str +"\n");
                writer.flush();
                System.out.println("返回的结果是");
                System.out.println(reader.readLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
