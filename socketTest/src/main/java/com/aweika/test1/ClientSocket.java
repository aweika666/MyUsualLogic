package com.aweika.test1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author: Michael
 * @date: 2020/3/6
 * @description:
 */
public class ClientSocket {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String str = "啦啦啦啊";
            bufferedWriter.write(str);
            bufferedWriter.flush();
            //关闭socket的输出流
            //socket.close();
            while (true){
                Thread.sleep(1000);
                System.out.println("aa");
            }
        }    catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
