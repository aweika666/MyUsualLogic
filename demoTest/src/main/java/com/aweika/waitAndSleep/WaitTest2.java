package com.aweika.waitAndSleep;


/**
 * @author: Michael
 * @date: 2020/7/27
 * @description:
 */
public class WaitTest2 {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1", Thread.currentThread());
        synchronized (t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();
                // 主线程等待t1通过notify()唤醒。
                System.out.println("挂起主线程");
                System.out.println("线程t1的状态是:" + t1.isAlive());
                t1.wait();  //  不是使t1线程等待，而是让拥有t1这个对象的主线程等待
                System.out.println("挂起主线程后面的输出");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程被打断后的状态是:" + Thread.currentThread().isAlive());
            System.out.println("主线程执行完毕");
        }

    }
}

class ThreadA extends Thread {

    private Thread mainThread;

    public ThreadA(String name, Thread main) {
        super(name);
        mainThread = main;
    }

    @Override
    public void run() {
        synchronized (this) {

            // Thread.sleep(1000); //  使当前线阻塞 1 s确保在主线程wait()之前t1没有执行完并退出
            System.out.println("ThreadA执行");

            mainThread.interrupt();//打断主线程,如果主线程之前是wait状态,也不会直接抛出异常,须得拿到锁对象,才能抛出异常
            //System.out.println(Thread.currentThread().getName()+" call notify()");
            // 唤醒当前的wait线程
            //this.notify();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程被打断后的状态是:" + mainThread.isAlive());

        }
    }
}

