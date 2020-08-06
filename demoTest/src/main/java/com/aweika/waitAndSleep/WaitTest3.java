package com.aweika.waitAndSleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Michael
 * @date: 2020/7/27
 * @description: wait方法只能在synchronized代码块中使用
 */
public class WaitTest3 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ThreadB t1 = new ThreadB("t1",lock);
        lock.lock();
        try {
            // 启动“线程t1”
            System.out.println(Thread.currentThread().getName() + " start t1");
            t1.start();
            // 主线程等待t1通过notify()唤醒。
            System.out.println("挂起主线程");
            System.out.println("线程t1的状态是:" + t1.isAlive());
            lock.wait();  //
            // Exception in thread "main" java.lang.IllegalMonitorStateException
            //wait方法只能在synchronized代码块中使用
            System.out.println("挂起主线程后面的输出");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class ThreadB extends Thread {
    private Lock lock;

    public ThreadB(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            // Thread.sleep(1000); //  使当前线阻塞 1 s确保在主线程wait()之前t1没有执行完并退出
            System.out.println("ThreadA执行");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        //System.out.println(Thread.currentThread().getName()+" call notify()");
        // 唤醒当前的wait线程
        //this.notify();

    }
}

