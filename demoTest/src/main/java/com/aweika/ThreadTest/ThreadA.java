package com.aweika.ThreadTest;

/**
 * @author: Michael
 * @date: 2020/2/26
 * @description:
 */
public class ThreadA extends Thread{

    /**
     * Causes this thread to begin execution; the Java Virtual Machine
     * calls the <code>run</code> method of this thread.
     * <p>
     * The result is that two threads are running concurrently: the
     * current thread (which returns from the call to the
     * <code>start</code> method) and the other thread (which executes its
     * <code>run</code> method).
     * <p>
     * It is never legal to start a thread more than once.
     * In particular, a thread may not be restarted once it has completed
     * execution.
     *
     * @throws IllegalThreadStateException if the thread was already
     *                                     started.
     * @see #run()
     * @see #stop()
     */
    @Override
    public synchronized void start() {
        super.start();
    }

    public static void main(String[] args) {
        Object o = new Object();


        ThreadB b = new ThreadB(o);
        b.start();// 主线程中启动另外一个线程
        b.start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("b is start....");
        // 括号里的b是什么意思,应该很好理解吧
        synchronized (b) {
            try {
                System.out.println("Waiting for b to complete...");
                b.wait();// 这一句是什么意思，究竟谁等待?
                System.out.println("ThreadB is Completed. Now back to main thread");
            } catch (InterruptedException e) {

            }

        }
        System.out.println("Total is :" + b.total);
    }
}

class ThreadB extends Thread {
    Object o ;

    public ThreadB(Object o) {
        this.o = o;
    }

    int total;

    public void run() {
        //synchronized (this) {
            System.out.println("ThreadB is running..");
            for (int i = 0; i <= 100; i++) {
                total += i;
            }
            System.out.println("total is" + total);
            //notify();
        //}
    }
}
