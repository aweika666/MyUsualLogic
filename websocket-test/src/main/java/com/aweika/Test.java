package com.aweika;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Aweika_Fang
 * @date: 2020/9/1
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        Date x = new Date(1599544296);
        System.out.println(x);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(x));
    }
}
