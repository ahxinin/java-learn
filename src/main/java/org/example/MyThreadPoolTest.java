package org.example;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: TODO
 * @date : 2023-02-05
 */
public class MyThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        // 创建有界阻塞队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        // 创建线程池
        MyThreadPool pool = new MyThreadPool(1, workQueue);
        for (int i=0; i<20; i++){
            // 提交任务
            pool.execute(()->{
                System.out.println("hello "+new Date());
            });
        }
    }

}
