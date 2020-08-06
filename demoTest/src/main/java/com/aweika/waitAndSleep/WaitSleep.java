package com.aweika.waitAndSleep;

/**
 * @author: Michael
 * @date: 2020/7/27
 * @description:
 */
public class WaitSleep {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(()->m1()).start();
        }
    }

    public static void m1(){
        synchronized (Object.class){
            try {
                System.out.println("The Thread :"+ Thread.currentThread().getName() + "-begin");
                Object.class.wait();
                System.out.println("The Thread :"+ Thread.currentThread().getName() + "-end");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
