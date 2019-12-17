package com.atguigu.test;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //ExecutorService threadpool= Executors.newFixedThreadPool(5);//固定线程，写几个线程就有几个线程
        //ExecutorService threadpool= Executors.newSingleThreadExecutor();//单一线程，只有一个线程
        //ExecutorService threadpool= Executors.newCachedThreadPool();//一池N线程

        ExecutorService threadPool=new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
                );

        try {
            for (int i = 1; i <=8 ; i++) {
                final int tempI=i;
               threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t受理业务"+"\t客户号"+tempI);});
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
