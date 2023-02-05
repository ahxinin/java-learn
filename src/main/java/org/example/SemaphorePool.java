package org.example;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @description: 信号量实现对象池
 * @date : 2023-02-05
 */
class SemaphorePool<T, R> {

    final List<T> pool;
    // 用信号量实现限流器
    final Semaphore sem;

    // 构造函数
    SemaphorePool(int size, T t){
        pool = new Vector<T>(){};
        for(int i=0; i<size; i++){
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    // 利用对象池的对象，调用func
    R exec(Function<T,R> func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }
}

