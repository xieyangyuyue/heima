package com.itheima;

import org.junit.jupiter.api.Test;

public class TreadLocalTest {
    @Test
    public void testTreadLocalSetAndGet(){
        //提供一个ThreadLocal对象
        ThreadLocal tl=new ThreadLocal();

        //开启两个线程
        /**
         * 两个参数：线程任务，线程名字
         * 线程任务由lambda
         * Thread.currentThread() 方法返回当前正在执行的线程对象，
         * 而 getName() 方法则是 Thread 类的一个实例方法，用于获取线程的名称。
         */
        new Thread(()->{
            tl.set("梦生");
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
        },"蓝色").start();
        new Thread(()->{
            tl.set("斜阳");
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
            System.out.println(Thread.currentThread().getName()+": "+tl.get());
        },"绿色").start();
    }
}
