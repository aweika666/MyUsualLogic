package com.aweika.test2;

import java.io.*;
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
            //通过socket获取字符流

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //通过标准输入流获取字符流

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

            while (true) {

                String str = bufferedReader.readLine();

                bufferedWriter.write(str);

                bufferedWriter.write("\n");

                bufferedWriter.flush();
                //bufferedWriter.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
