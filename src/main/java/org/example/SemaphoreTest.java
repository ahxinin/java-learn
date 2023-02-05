package org.example;

import java.util.concurrent.Semaphore;

/**
 * @description: 信号量
 * @date : 2023-02-05
 */
public class SemaphoreTest {

    static int count;

    //初始化信号量
    static final Semaphore s = new Semaphore(1);

    //用信号量保证互斥
    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count+=1;
        } finally {
            s.release();
        }
    }
}
