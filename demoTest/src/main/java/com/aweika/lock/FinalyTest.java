package com.aweika.lock;

/**
 * @author: Michael
 * @date: 2020/8/3
 * @description:
 */
public class FinalyTest {
    public static void main(String[] args) {
        if (0 == 1)
            throw new RuntimeException("人造异常");
        try {

        }finally {
            System.out.println("照常输出");
        }
    }
}
