package com.aweika.test2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Michael
 * @date: 2020/3/6
 * @description:
 */
public class ServerSocketTest {
    public static void main(String[] args) {
        try {
            // 初始化服务端socket并且绑定9999端口
            ServerSocket serverSocket = new ServerSocket(8888);
            //等待客户端的连接
            Socket socket = serverSocket.accept();
            //获取输入流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //读取一行数据

            String str;
            //通过while循环不断读取信息
            while ((str = bufferedReader.readLine())!=null){
                //输出打印
                System.out.println(str);
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
