/**
 1.服务器端
    ①利用ServerSocket建立监听服务器端口8888
    ②ServerSocket的accept方法来阻塞监听8888端口的客户端请求
        当客户端发起socket套接字请求时
    ③accept方法会返回socket套接字（唯一标识客户端地址，客户端端口号，本地端口号）
    ④socket套接字可以获得输入输出流（传输数据和接受数据）
    ⑤通过输入输出流来传输数据（即监听端口的输入流，发送数据的输出流，此时与客户端的输入输出流无关）
    ⑥关闭资源

 */
 package com.likejin.jdbc.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * @Description  服务器端，模拟mysql服务器端
 * @Author  李柯锦
 * @Date
 */
public class Server {

    public static void main(String[] args) {
        //建立服务器端口
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try{
            serverSocket = new ServerSocket(8888);
            //等待连接。连接上后建立socket对象让服务器和客户端通信用
            socket = serverSocket.accept();
            //模拟mysql连接
            //根据socket获取的输入流和输出流
            //用于接受客户端数据和发送数据
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //模拟验证连接mysql的用户和密码
            String user = reader.readLine();
            String password = reader.readLine();
        if(user.equals("user") && password.equals("abc123")){
            writer.write("验证成功" + "\n");
            writer.flush();
            while(true){
                //模拟请求sql查询
                System.out.println("客户端的请求sql语句是：");
                //模拟查询结束
                String msg = reader.readLine();
                if(msg.equals("查询结束")){
                    break;
                }
                System.out.println(msg);
                //模拟结果的返回
                writer.write("查询结果返回" + "\n");
                writer.flush();

            }
        }else{
            writer.write("用户名密码错误");
            writer.flush();
            socket.close();
        }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                serverSocket.close();
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }


    }
}
